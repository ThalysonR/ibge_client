package br.com.evoluum.ibge.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MesoRegiao {
    Integer id;
    String nome;
    Estado uf;
}