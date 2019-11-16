package br.com.evoluum.ibge.services;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evoluum.ibge.clients.IBGEClient;
import br.com.evoluum.ibge.domain.Municipio;
import br.com.evoluum.ibge.services.IBGEDataMappers.IBGEDataTypeInput;

@Service
public class IBGEService {
    @Autowired
    private IBGEClient client;

    public ByteArrayOutputStream getAllData(IBGEDataTypeInput input) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        input.mapper.writeStart(outputStream);
        List<Municipio> municipios = client.getEstados().stream()
            .flatMap(estado -> client.getMunicipiosByUF(estado.getId()).stream())
            .collect(Collectors.toList());
            IntStream.range(0, municipios.size())
                .forEach(index -> input.mapper.writeItem(outputStream, municipios.get(index), index == municipios.size() - 1));
        input.mapper.writeEnd(outputStream);
        return outputStream;
    }
}