package br.com.evoluum.ibge.services.IBGEDataMappers;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;
import br.com.evoluum.ibge.dto.IBGEResponseDTO;

public class JsonMapper implements IBGEDataMapper {

    @Override
    public void writeStart(OutputStream outputStream) {
        try {
            outputStream.write("[".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeItem(OutputStream oStream, Municipio municipio) {
        ObjectMapper jsonMapper = new ObjectMapper();
        try {
            Estado estado = municipio.getMicrorregiao().getMesorregiao().getUF();
            jsonMapper.writeValue(
                    oStream,
                    new IBGEResponseDTO(
                        estado.getId(),
                        estado.getSigla(),
                        estado.getRegiao().getNome(),
                        municipio.getNome(),
                        municipio.getMicrorregiao().getMesorregiao().getNome(),
                        String.format("%s/%s", municipio.getNome(), estado.getNome())
                        )
                );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void writeEnd(OutputStream outputStream) {
        try {
            outputStream.write("]".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}