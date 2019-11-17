package br.com.evoluum.ibge.services;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.evoluum.ibge.clients.IBGEClient;
import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;
import br.com.evoluum.ibge.services.IBGEDataMappers.IBGEDataTypeInput;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IBGEService {
    @Autowired
    private IBGEClient client;

    public ByteArrayOutputStream getAllData(IBGEDataTypeInput input) {
        log.info("Consulta de todos os dados");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        input.mapper.writeStart(outputStream);
        client.getEstados().forEach(estado -> {
            List<Municipio> municipios = client.getMunicipiosByUF(estado.getId());
            IntStream.range(0, municipios.size()).forEach(index -> {
                input.mapper.writeItem(outputStream, municipios.get(index), index == (municipios.size() - 1));
            });
        });
        input.mapper.writeEnd(outputStream);
        return outputStream;
    }

    @Cacheable("cidade")
    public Integer getCidadeIdByNome(String nomeCidade) {
        log.info("Consulta de cidade por nome");
        List<Estado> estados = client.getEstados();
        for (Estado estado : estados) {
            List<Municipio> municipios = client.getMunicipiosByUF(estado.getId());
            for (Municipio municipio : municipios) {
                if (municipio.getNome().toLowerCase().equals(nomeCidade.toLowerCase())) {
                    return municipio.getId();
                }
            }
        }
        throw new RuntimeException("Cidade não encontrada");
    }
}