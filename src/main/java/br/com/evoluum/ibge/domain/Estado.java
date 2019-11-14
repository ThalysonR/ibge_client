package br.com.evoluum.ibge.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Estado {
    Integer id;
    String sigla;
    Regiao regiao;
}