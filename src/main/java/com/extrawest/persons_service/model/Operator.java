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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sellPointIdentifier;
    private String taxpayerNumber;
}
