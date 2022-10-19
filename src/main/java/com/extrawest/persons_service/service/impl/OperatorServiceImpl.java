package com.extrawest.persons_service.service.impl;

import com.extrawest.persons_service.exception.ApiRequestException;
import com.extrawest.persons_service.exception.ExceptionMessage;
import com.extrawest.persons_service.feignClient.SellPointFeignClient;
import com.extrawest.persons_service.handler.CustomWebSocketHandler;
import com.extrawest.persons_service.model.AggregationSendingContainer;
import com.extrawest.persons_service.model.AggregationSendingOperation;
import com.extrawest.persons_service.model.Operator;
import com.extrawest.persons_service.model.dto.request.OperatorRequestDto;
import com.extrawest.persons_service.model.dto.response.DeleteResponseDto;
import com.extrawest.persons_service.model.dto.response.OperatorResponseDto;
import com.extrawest.persons_service.model.mapper.OperatorMapper;
import com.extrawest.persons_service.repository.OperatorRepository;
import com.extrawest.persons_service.service.OperatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;
    private final SellPointFeignClient sellPointFeignClient;
    private WebSocketSession webSocketSession;
    @Value("${aggregateservice.uri}")
    private String aggregateServiceUri;

    @Override
    public OperatorResponseDto create(OperatorRequestDto operatorRequestDto) {
        Boolean isSellPointExist = sellPointFeignClient.isSellPointExist(operatorRequestDto.getSellPointIdentifier()).getBody();
        if (isSellPointExist == null) {
            throw new ApiRequestException(ExceptionMessage.EMPTY_FEIGN_CLIENT_RESULT);
        } else if (!isSellPointExist) {
            throw new ApiRequestException(ExceptionMessage.WRONG_SELL_POINT_ID);
        }
        Operator operator = operatorMapper.toModel(operatorRequestDto);
        Operator operatorSaved = operatorRepository.save(operator);
        sendMessageToAggregateService(operatorSaved, AggregationSendingOperation.OPERATOR_CREATED);
        return operatorMapper.toDto(operatorSaved);
    }

    @Override
    public DeleteResponseDto delete(Long operatorId) {
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new NoSuchElementException("Operator with id: " + operatorId + " not found"));
        operatorRepository.deleteById(operatorId);
        sendMessageToAggregateService(operator, AggregationSendingOperation.OPERATOR_DELETED);
        return new DeleteResponseDto("Operator with id: " + operatorId + " deleted", operatorId);
    }

    @Override
    public OperatorResponseDto update(Long operatorId, OperatorRequestDto operatorRequestDto) {
        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new NoSuchElementException("Operator with id: " + operatorId + " not found"));
        operator.setTaxpayerNumber(operatorRequestDto.getTaxpayerNumber());
        operator.setSellPointIdentifier(operatorRequestDto.getSellPointIdentifier());
        operator.setDateOfBirth(operatorRequestDto.getDateOfBirth());
        operator.setName(operatorRequestDto.getName());
        Operator operatorUpdated = operatorRepository.save(operator);
        sendMessageToAggregateService(operatorUpdated, AggregationSendingOperation.OPERATOR_UPDATED);
        return operatorMapper.toDto(operatorUpdated);
    }

    @Override
    public Page<OperatorResponseDto> getAll(Pageable pageable) {
        Page<OperatorResponseDto> all = operatorRepository.findAll(pageable).map(operatorMapper::toDto);
        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }

    @Override
    public List<OperatorResponseDto> getAll() {
        return operatorRepository.findAll().stream().map(operatorMapper::toDto).toList();
    }

    @Override
    public OperatorResponseDto getById(Long operatorId) {
        return operatorMapper.toDto(operatorRepository.findById(operatorId)
                .orElseThrow(() -> new NoSuchElementException("Operator with id: " + operatorId + " not found")));
    }

    private void sendMessageToAggregateService(Operator operator, AggregationSendingOperation aggregationSendingOperation) {
        try {
            AggregationSendingContainer aggregationSendingContainer = new AggregationSendingContainer();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
            aggregationSendingContainer.setAggregationSendingOperation(aggregationSendingOperation);
            aggregationSendingContainer.setObject(operator);
            if (webSocketSession == null) setClientSession();
            if (!webSocketSession.isOpen()) setClientSession();
            webSocketSession.sendMessage(new TextMessage(ow.writeValueAsString(aggregationSendingContainer)));
        } catch (ExecutionException | InterruptedException | IOException e) {
            throw new ApiRequestException(ExceptionMessage.WEB_SOCKET_SENDING_ERROR);
        }
    }

    private void setClientSession() throws ExecutionException, InterruptedException {
        this.webSocketSession = new StandardWebSocketClient().doHandshake(
                new CustomWebSocketHandler(), new WebSocketHttpHeaders(), URI.create(aggregateServiceUri)
        ).get();
    }
}
