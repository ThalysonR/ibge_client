package br.com.evoluum.ibge.services.IBGEDataMappers;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.stream.IntStream;

import br.com.evoluum.ibge.domain.Municipio;
import br.com.evoluum.ibge.dto.IBGEResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvMapper implements IBGEDataMapper {
    private Field[] fields = IBGEResponseDTO.class.getDeclaredFields();

    @Override
    public void writeStart(OutputStream outputStream) {
        IntStream.range(0, fields.length).forEach(index -> {
            try {
                outputStream.write(fields[index].getName().getBytes());
                if (index != fields.length - 1) {
                    outputStream.write(",".getBytes());
                } else {
                    outputStream.write("\n".getBytes());
                }
            } catch (Exception e) {
                log.error("Falha ao escrever cabeçalho do csv", e);
            }
        });
    }

    @Override
    public void writeEnd(OutputStream outputStream) {
        return;
    }

    @Override
    public void writeItem(OutputStream oStream, Municipio municipio, Boolean ultimo) {
        IBGEResponseDTO dto = IBGEResponseDTO.fromMunicipio(municipio);
        IntStream.range(0, fields.length).forEach(index -> {
            try {
                Object val = fields[index].get(dto);
                oStream.write(String.valueOf(val).getBytes());
                if (index != fields.length - 1) {
                    oStream.write(",".getBytes());
                } else {
                    oStream.write("\n".getBytes());
                }
            } catch (Exception e) {
                log.error("Falha ao escrever item do csv", e);
            }
        });
    }
}