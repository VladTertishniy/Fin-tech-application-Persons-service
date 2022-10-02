package com.extrawest.persons_service.service.impl;

import com.extrawest.persons_service.model.Operator;
import com.extrawest.persons_service.model.dto.request.OperatorRequestDto;
import com.extrawest.persons_service.model.dto.response.DeleteResponseDto;
import com.extrawest.persons_service.model.dto.response.OperatorResponseDto;
import com.extrawest.persons_service.model.mapper.OperatorMapper;
import com.extrawest.persons_service.repository.OperatorRepository;
import com.extrawest.persons_service.service.OperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

    private final OperatorRepository operatorRepository;
    private final OperatorMapper operatorMapper;

    @Override
    public OperatorResponseDto create(OperatorRequestDto operatorRequestDto) {
        Operator operator = operatorMapper.toModel(operatorRequestDto);
        return operatorMapper.toDto(operatorRepository.save(operator));
    }

    @Override
    public Page<OperatorResponseDto> getAll(Pageable pageable) {
        Page<OperatorResponseDto> all = operatorRepository.findAll(pageable).map(operatorMapper::toDto);
        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }

    @Override
    public OperatorResponseDto getById(Long operatorId) {
        return operatorMapper.toDto(operatorRepository.findById(operatorId)
                .orElseThrow(() -> new NoSuchElementException("Operator with id: " + operatorId + " not found")));
    }

    @Override
    public DeleteResponseDto delete(Long operatorId) {
        operatorRepository.deleteById(operatorId);
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
        return operatorMapper.toDto(operatorRepository.save(operator));
    }
}
