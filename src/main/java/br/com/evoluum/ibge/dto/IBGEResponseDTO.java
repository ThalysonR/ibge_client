package br.com.evoluum.ibge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IBGEResponseDTO {
    Integer idEstado;
    String siglaEstado;
    String regiaoNome;
    String nomeCidade;
    String nomeMesorregiao;
    String nomeFormatado;
}