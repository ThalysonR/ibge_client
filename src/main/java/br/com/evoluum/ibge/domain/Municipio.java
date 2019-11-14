package br.com.evoluum.ibge.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Municipio {
    Integer id;
    String nome;
    MicroRegiao microrregiao;
}