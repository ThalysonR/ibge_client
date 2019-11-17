package br.com.evoluum.ibge.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.google.common.net.HttpHeaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.evoluum.ibge.services.IBGEService;
import br.com.evoluum.ibge.services.IBGEDataMappers.IBGEDataTypeInput;

@RestController
@RequestMapping("ibge")
public class IBGEController {

    @Autowired
    IBGEService service;

    @GetMapping(value = "relatorio")
    public ResponseEntity<InputStreamResource> getAllData(@RequestParam("type") IBGEDataTypeInput inputType) {
        ByteArrayOutputStream stream = service.getAllData(inputType);
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment;filename=data.%s", inputType.name()))
            .body(new InputStreamResource(new ByteArrayInputStream(stream.toByteArray())));
    }

    @GetMapping("cidades")
    public Integer getCidadeIdByNome(@RequestParam("name") String nomeCidade) {
        return service.getCidadeIdByNome(nomeCidade.toLowerCase());
    }
}