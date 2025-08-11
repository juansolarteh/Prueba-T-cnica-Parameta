package parameta.prueba.tecnica.servicioSoap.endpoints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeResponseDTO;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeEndpointTest {

    private WebServiceTemplate webServiceTemplate;

    @BeforeEach
    void setUp() throws Exception {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(SaveEmployeeRequestDTO.class, SaveEmployeeResponseDTO.class);
        marshaller.afterPropertiesSet();
        webServiceTemplate = new WebServiceTemplate(marshaller);
    }

    @LocalServerPort
    private int port;

    @Test
    void shouldSaveEmployeesAndReturnIds() {
        SaveEmployeeRequestDTO request = new SaveEmployeeRequestDTO();
        request.setName("Juan");
        request.setLastName("Solarte");
        request.setDocumentType("CC");
        request.setDocumentNumber("12345678119");
        request.setBirthDate(new Date());
        request.setJoiningDate(new Date());
        request.setPosition("Desarrollador");
        request.setSalary(500.0);

        String uri = "http://localhost:" + port + "/ws";

        SaveEmployeeResponseDTO response = (SaveEmployeeResponseDTO) webServiceTemplate.marshalSendAndReceive(
                uri,
                request
        );

        assertNotNull(response);
        assertEquals("OK", response.getMessage());
        assertEquals(1, response.getEmployeeId());

        response = (SaveEmployeeResponseDTO) webServiceTemplate.marshalSendAndReceive(
                uri,
                request
        );

        assertNotNull(response);
        assertEquals("OK", response.getMessage());
        assertEquals(2, response.getEmployeeId());
    }
}