package br.com.evoluum.ibge.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MicroRegiao {
    Integer id;
    String nome;
    MesoRegiao mesorregiao;
}