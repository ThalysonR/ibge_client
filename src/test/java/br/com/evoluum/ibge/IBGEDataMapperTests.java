package br.com.evoluum.ibge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang3.builder.EqualsBuilder;
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
public class IBGEDataMapperTests {
    @MockBean
    IBGEClient mockClient;

    @Autowired
    IBGEService service;

    @Test
    public void jsonMapperTest() {
        ObjectMapper mapper = new ObjectMapper();
        Estado[] mockEstados = {};
        Municipio[] municipiosMock = {};
        List<IBGEResponseDTO> esperado = new ArrayList<>();
        String filePath = new File("").getAbsolutePath();
        try {
            mockEstados = mapper.readValue(new FileReader(filePath.concat("/src/test/java/br/com/evoluum/ibge/mocks/estados.json")), Estado[].class);
            municipiosMock = mapper.readValue(new FileReader(filePath.concat("/src/test/java/br/com/evoluum/ibge/mocks/municipios.json")), Municipio[].class);
            IBGEResponseDTO[] arExpected = mapper.readValue(new FileReader(filePath.concat("/src/test/java/br/com/evoluum/ibge/mocks/esperado.json")), IBGEResponseDTO[].class);
            esperado = new ArrayList<IBGEResponseDTO>(Arrays.asList(arExpected));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Falha ao carregar mocks");
        }
        Mockito.when(mockClient.getEstados()).thenReturn(new ArrayList<Estado>(Arrays.asList(mockEstados)));
        Mockito.when(mockClient.getMunicipiosByUF(11)).thenReturn(new ArrayList<Municipio>(Arrays.asList(municipiosMock)));

        List<IBGEResponseDTO> resultado = new ArrayList<>();
        try {
            String resString = service.getAllData(IBGEDataTypeInput.json).toString();
            System.out.println(resString);
            IBGEResponseDTO[] res = mapper.readValue(resString, IBGEResponseDTO[].class);
            resultado = new ArrayList<>(Arrays.asList(res));
        } catch (Exception e) {
            Assertions.fail("Falha ao ler resultado");
        }
        assertTrue(EqualsBuilder.reflectionEquals(resultado, esperado));
    }
}