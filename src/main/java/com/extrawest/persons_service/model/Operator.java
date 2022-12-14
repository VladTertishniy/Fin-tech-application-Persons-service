package com.extrawest.persons_service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "operators")
@PrimaryKeyJoinColumn(name = "persona_id")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Operator extends Persona {
    private Long sellPointIdentifier;
    private String taxpayerNumber;
}
