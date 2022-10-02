package com.extrawest.persons_service.service;

import com.extrawest.persons_service.model.dto.request.OperatorRequestDto;
import com.extrawest.persons_service.model.dto.response.DeleteResponseDto;
import com.extrawest.persons_service.model.dto.response.OperatorResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperatorService {
    OperatorResponseDto create(OperatorRequestDto operatorRequestDto);
    Page<OperatorResponseDto> getAll(Pageable pageable);
    OperatorResponseDto getById(Long operatorId);
    DeleteResponseDto delete(Long operatorId);
    OperatorResponseDto update(Long operatorId, OperatorRequestDto operatorRequestDto);
}
