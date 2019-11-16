package br.com.evoluum.ibge.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evoluum.ibge.clients.IBGEClient;
import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.services.IBGEDataMappers.IBGEDataTypeInput;

@Service
public class IBGEService {
    @Autowired
    private IBGEClient client;

    public ByteArrayOutputStream getAllData(IBGEDataTypeInput input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        input.mapper.writeStart(outputStream);
        List<Estado> estados = client.getEstados();
        estados.stream()
            .flatMap(estado -> {

                return client.getMunicipiosByUF(estado.getId()).stream();
            })
            .forEach(municipio -> input.mapper.writeItem(outputStream, municipio));
        input.mapper.writeEnd(outputStream);
        return outputStream;
    }
}