package com.extrawest.persons_service.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperatorRequestDto {
    @NotBlank
    private String name;
    @NotNull
    @Past
    private LocalDateTime dateOfBirth;
    @NotBlank
    private String sellPointIdentifier;
    @NotBlank
    private String taxpayerNumber;
}
