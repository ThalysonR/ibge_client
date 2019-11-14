package br.com.evoluum.ibge.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.evoluum.ibge.clients.ClientURLs;
import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;

@FeignClient(name = "ws-ibge", url = ClientURLs.IBGE, fallback = IBGEFallback.class)
public interface IBGEClient {
    @GetMapping("/localidades/estados")
    List<Estado> getEstados();

    @GetMapping("/localidades/estados/${UF}/municipio")
    List<Municipio> getMunicipiosByUF(@PathVariable Integer idEstado);
}