package br.com.evoluum.ibge.services.IBGEDataMappers;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.evoluum.ibge.domain.Municipio;
import br.com.evoluum.ibge.dto.IBGEResponseDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper implements IBGEDataMapper {

    @Override
    public void writeStart(OutputStream outputStream) {
        try {
            outputStream.write("[".getBytes());
        } catch (Exception e) {
            log.error("Falha ao escrever colchete da abertura da lista json", e);
        }
    }

    @Override
    public void writeItem(OutputStream oStream, Municipio municipio, Boolean ultimo) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            jsonMapper.writeValue(oStream, IBGEResponseDTO.fromMunicipio(municipio));
            if (!ultimo) {
                oStream.write(",".getBytes());
            }
        } catch (IOException e) {
            log.error("Falha ao escrever objeto do json", e);
        }
    }
    
    @Override
    public void writeEnd(OutputStream outputStream) {
        try {
            outputStream.write("]".getBytes());
        } catch (Exception e) {
            log.error("Falha ao escrever colchete de encerramento da lista json", e);
        }
    }
}