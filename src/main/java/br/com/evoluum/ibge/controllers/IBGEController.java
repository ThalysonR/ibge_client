package br.com.evoluum.ibge.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.evoluum.ibge.clients.IBGEClient;
import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.services.IBGEService;
import br.com.evoluum.ibge.services.IBGEDataMappers.IBGEDataTypeInput;

@RestController
@RequestMapping("ibge")
public class IBGEController {
    @Autowired
    IBGEClient client;

    @Autowired
    IBGEService service;

    @GetMapping("estados")
    public List<Estado> getEstados() {
        return client.getEstados();
    }

    @GetMapping(value = "relatorio")
    public byte[] getAllData(@RequestParam("type") IBGEDataTypeInput inputType) {
        ByteArrayOutputStream stream = service.getAllData(inputType);
        return stream.toByteArray();
        // return ResponseEntity
        //     .ok()
        //     .body(new InputStreamResource(new ByteArrayInputStream(stream.toByteArray())));
    }
}