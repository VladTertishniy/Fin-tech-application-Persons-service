package com.extrawest.persons_service.controller;

import com.extrawest.persons_service.model.dto.request.OperatorRequestDto;
import com.extrawest.persons_service.model.dto.response.DeleteResponseDto;
import com.extrawest.persons_service.model.dto.response.OperatorResponseDto;
import com.extrawest.persons_service.service.OperatorService;
import com.extrawest.persons_service.util.PathUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(PathUtil.OPERATORS_ROOT_PATH)
@AllArgsConstructor
public class OperatorController {
    private final OperatorService operatorService;

    @PostMapping(PathUtil.CREATE_PATH)
    public ResponseEntity<OperatorResponseDto> create(@RequestBody @Valid OperatorRequestDto operatorRequestDto) {
        return ResponseEntity.ok(operatorService.create(operatorRequestDto));
    }

    @GetMapping(PathUtil.GET_BY_ID_PATH)
    public ResponseEntity<OperatorResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(operatorService.getById(id));
    }

    @GetMapping(PathUtil.GET_ALL_PATH)
    public ResponseEntity<Page<OperatorResponseDto>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(operatorService.getAll(pageable));
    }

    @GetMapping(PathUtil.DELETE_PATH)
    public ResponseEntity<DeleteResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(operatorService.delete(id));
    }

    @PostMapping(PathUtil.UPDATE_PATH)
    public ResponseEntity<OperatorResponseDto> update(@PathVariable Long id,
                                                      @RequestBody @Valid OperatorRequestDto operatorRequestDto) {
        return ResponseEntity.ok(operatorService.update(id, operatorRequestDto));
    }

}
