package br.com.evoluum.ibge.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;

@Component
public class IBGEFallback implements IBGEClient {

    @Override
    public List<Estado> getEstados() {
        return new ArrayList<>();
    }

    @Override
    public List<Municipio> getMunicipiosByUF(Integer idEstado) {
        return new ArrayList<>();
    }
    
}