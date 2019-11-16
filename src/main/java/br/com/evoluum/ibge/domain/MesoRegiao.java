package br.com.evoluum.ibge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MesoRegiao {
    Integer id;
    String nome;
    @JsonProperty("UF")
    Estado UF;
}