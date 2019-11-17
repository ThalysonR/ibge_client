package br.com.evoluum.ibge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.evoluum.ibge.clients.IBGEClient;
import br.com.evoluum.ibge.domain.Estado;
import br.com.evoluum.ibge.domain.Municipio;
import br.com.evoluum.ibge.dto.IBGEResponseDTO;
import br.com.evoluum.ibge.services.IBGEService;
import br.com.evoluum.ibge.services.IBGEDataMappers.IBGEDataTypeInput;

@SpringBootTest
public class IBGEServiceTests {
    private final String MOCK_PATH = "/src/test/java/br/com/evoluum/ibge/mocks/";
    private String filePath = "";

    @MockBean
    IBGEClient mockClient;

    @Autowired
    IBGEService service;

    public void setupMocks() {
        ObjectMapper mapper = new ObjectMapper();
        Estado[] mockEstados = {};
        Municipio[] municipiosMock = {};
        filePath = new File("").getAbsolutePath();
        try {
            mockEstados = mapper.readValue(new FileReader(filePath.concat(MOCK_PATH + "estados.json")), Estado[].class);
            municipiosMock = mapper.readValue(new FileReader(filePath.concat(MOCK_PATH + "municipios.json")), Municipio[].class);
        } catch (Exception e) {
            Assertions.fail("Falha ao carregar mocks");
        }

        Mockito.when(mockClient.getEstados()).thenReturn(new ArrayList<Estado>(Arrays.asList(mockEstados)));
        Mockito.when(mockClient.getMunicipiosByUF(11)).thenReturn(new ArrayList<Municipio>(Arrays.asList(municipiosMock)));
    }

    @Test
    public void jsonMapperTest() {
        setupMocks();
        ObjectMapper mapper = new ObjectMapper();
        List<IBGEResponseDTO> esperado = new ArrayList<>();
        try {
            IBGEResponseDTO[] arExpected = mapper.readValue(new FileReader(filePath.concat(MOCK_PATH + "esperado.json")), IBGEResponseDTO[].class);
            esperado = new ArrayList<IBGEResponseDTO>(Arrays.asList(arExpected));
        } catch (Exception e) {
            Assertions.fail("Falha ao ler json esperado");
        }

        List<IBGEResponseDTO> resultado = new ArrayList<>();
        try {
            String resString = service.getAllData(IBGEDataTypeInput.json).toString();
            IBGEResponseDTO[] res = mapper.readValue(resString, IBGEResponseDTO[].class);
            resultado = new ArrayList<>(Arrays.asList(res));
        } catch (Exception e) {
            Assertions.fail("Falha ao ler resultado");
        }
        assertEquals(esperado, resultado);
    }

    @Test
    public void csvMapperTest() {
        setupMocks();
        List<String> esperado = new ArrayList<>();
        try {
            BufferedReader eReader = new BufferedReader(new FileReader(filePath.concat(MOCK_PATH + "esperado.csv")));
            eReader.lines().forEach(line -> esperado.add(line));
            eReader.close();
        } catch (Exception e) {
            Assertions.fail("Falha ao ler csv esperado");
        }

        String resString = service.getAllData(IBGEDataTypeInput.csv).toString();
        List<String> resultado = new ArrayList<>(Arrays.asList(resString.split("\n")));
        
        assertEquals(esperado, resultado);
    }

    @Test
    public void buscaCidadePorNome() {
        setupMocks();
        Integer resultado = service.getCidadeIdByNome("Ariquemes");
        Integer esperado = 1100023;

        assertEquals(esperado, resultado);
    }

    @Test
    public void falhaAoNaoEncontrarCidade() {
        setupMocks();
        assertThrows(RuntimeException.class, () -> {
            service.getCidadeIdByNome("Nova Iorque");
        });
    }
}