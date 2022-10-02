package com.extrawest.persons_service.model.mapper;

import com.extrawest.persons_service.model.Operator;
import com.extrawest.persons_service.model.dto.request.OperatorRequestDto;
import com.extrawest.persons_service.model.dto.response.OperatorResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperatorMapper {
    OperatorResponseDto toDto (Operator operator);
    Operator toModel (OperatorRequestDto operatorRequestDto);
}
