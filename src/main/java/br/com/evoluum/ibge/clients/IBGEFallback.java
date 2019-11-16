package br.com.evoluum.ibge.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class IBGEFallback implements IBGEClient {

    @Override
    public List<Estado> getEstados() {
        log.info("Falha ao consultar estados");
        return new ArrayList<>();
    }

    @Override
    public List<Municipio> getMunicipiosByUF(Integer idEstado) {
        log.info(String.format("Falha ao consultar municipios do estado com id: %d", idEstado));
        return new ArrayList<>();
    }
    
}