package br.com.evoluum.ibge.dto;

import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class IBGEResponseDTO {
    Integer idEstado;
    String siglaEstado;
    String regiaoNome;
    String nomeCidade;
    String nomeMesorregiao;
    String nomeFormatado;

    public static IBGEResponseDTO fromMunicipio(Municipio municipio) {
        IBGEResponseDTO dto = new IBGEResponseDTO();
        Estado estado = municipio.getMicrorregiao().getMesorregiao().getUF();
        dto.setIdEstado(estado.getId());
        dto.setSiglaEstado(estado.getSigla());
        dto.setRegiaoNome(estado.getRegiao().getNome());
        dto.setNomeCidade(municipio.getNome());
        dto.setNomeMesorregiao(municipio.getMicrorregiao().getMesorregiao().getNome());
        dto.setNomeFormatado(String.format("%s/%s", municipio.getNome(), estado.getSigla()));
        return dto;
    }
}