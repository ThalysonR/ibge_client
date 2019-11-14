package br.com.evoluum.ibge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.evoluum.ibge.clients.IBGEClient;
import br.com.evoluum.ibge.domain.Estado;

@RestController
@RequestMapping("ibge")
public class IBGEController {
    @Autowired
    IBGEClient client;

    @GetMapping("estados")
    public List<Estado> getEstados() {
        return client.getEstados();
    }
}