package parameta.prueba.tecnica.servicioRest.infrastructure.httpController;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;
import parameta.prueba.tecnica.servicioRest.domain.constants.EmployeeProperties;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.ws.test.client.RequestMatchers.anything;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

//Test de integración
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class EmployeeControllerTest {
    private final String URL = "/employee";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebServiceTemplate webServiceTemplate;
    private MockWebServiceServer mockServer;

    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private String birthDate;
    private String joiningDate;
    private String position;
    private Double salary;

    @BeforeEach
    void setup() {
        name = "John";
        lastName = "Doe";
        documentType = "CC";
        documentNumber = "123456789";
        birthDate = "2000/08/08";
        joiningDate = "2025/07/07";
        position = "Developer";
        salary = 5000.0;
        mockServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Test
    void shouldReturnBadRequestWhenRequiredParamsAreMissing() throws Exception {
        String message = "Error: Faltan parámetros requeridos - " + EmployeeProperties.NAMES;
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void shouldReturnBadRequestWhenTypeMismatchOccurs() throws Exception {
        String message = "Error: Tipo de dato incorrecto para el parámetro - " + EmployeeProperties.SALARY + ". Se esperaba: Double";
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param(EmployeeProperties.NAMES, name)
                .param(EmployeeProperties.LAST_NAMES, lastName)
                .param(EmployeeProperties.DOCUMENT_TYPE, documentType)
                .param(EmployeeProperties.DOCUMENT_NUMBER, documentNumber)
                .param(EmployeeProperties.BIRTH_DATE, birthDate)
                .param(EmployeeProperties.JOINING_DATE, joiningDate)
                .param(EmployeeProperties.POSITION, position)
                .param(EmployeeProperties.SALARY, "invalid"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void shouldReturnBadRequestWhenThrowDomainException() throws Exception {
        String message = "La propiedad '" + EmployeeProperties.NAMES + "' no puede ser nula o vacía.";
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param(EmployeeProperties.NAMES, "")
                .param(EmployeeProperties.LAST_NAMES, lastName)
                .param(EmployeeProperties.DOCUMENT_TYPE, documentType)
                .param(EmployeeProperties.DOCUMENT_NUMBER, documentNumber)
                .param(EmployeeProperties.BIRTH_DATE, birthDate)
                .param(EmployeeProperties.JOINING_DATE, joiningDate)
                .param(EmployeeProperties.POSITION, position)
                .param(EmployeeProperties.SALARY, salary.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.statusCode").value(400))
                .andExpect(jsonPath("$.message").value(message));
    }

    @Test
    void shouldReturnInternalServerErrorWhenRuntimeExceptionOccurs() throws Exception {
        mockServer.expect(anything())
                        .andRespond((uri, request, messageFactory) -> {
                            throw new RuntimeException("Simulated server error");
                        });
        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param(EmployeeProperties.NAMES, name)
                .param(EmployeeProperties.LAST_NAMES, lastName)
                .param(EmployeeProperties.DOCUMENT_TYPE, documentType)
                .param(EmployeeProperties.DOCUMENT_NUMBER, documentNumber)
                .param(EmployeeProperties.BIRTH_DATE, birthDate)
                .param(EmployeeProperties.JOINING_DATE, joiningDate)
                .param(EmployeeProperties.POSITION, position)
                .param(EmployeeProperties.SALARY, salary.toString()))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.statusCode").value(500))
                .andExpect(jsonPath("$.message").value("Error interno del servidor"));
    }

    @Test
    void shouldCreateEmployeeSuccessfully() throws Exception {
        String birthDateResponse = birthDate.replace("/", "-");
        String joiningDateResponse = joiningDate.replace("/", "-");

        String mockResponseXml =
                """
                <ns2:SaveEmployeeResponse xmlns:ns2="http://parameta.prueba.tecnica/employee">
                    <ns2:employeeId>1</ns2:employeeId>
                    <ns2:message>OK</ns2:message>
                </ns2:SaveEmployeeResponse>
                """;

        mockServer.expect(anything())
                .andRespond(withPayload(new StringSource(mockResponseXml)));

        mockMvc.perform(MockMvcRequestBuilders.get(URL)
                .param(EmployeeProperties.NAMES, name)
                .param(EmployeeProperties.LAST_NAMES, lastName)
                .param(EmployeeProperties.DOCUMENT_TYPE, documentType)
                .param(EmployeeProperties.DOCUMENT_NUMBER, documentNumber)
                .param(EmployeeProperties.BIRTH_DATE, birthDate)
                .param(EmployeeProperties.JOINING_DATE, joiningDate)
                .param(EmployeeProperties.POSITION, position)
                .param(EmployeeProperties.SALARY, salary.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.result").isNotEmpty())
                .andExpect(jsonPath("$.result.Id").value(1))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.NAMES + "']").value(name))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.LAST_NAMES + "']").value(lastName))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.DOCUMENT_TYPE + "']").value(documentType))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.DOCUMENT_NUMBER + "']").value(documentNumber))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.BIRTH_DATE + "']", containsString(birthDateResponse)))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.JOINING_DATE + "']", containsString(joiningDateResponse)))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.POSITION + "']").value(position))
                .andExpect(jsonPath("$.result['" + EmployeeProperties.SALARY + "']").value(salary))
                .andExpect(jsonPath("$.result['Tiempo de Vinculación a la compañía']").isNotEmpty())
                .andExpect(jsonPath("$.result['Tiempo de Vinculación a la compañía'].años").value(Matchers.greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.result['Tiempo de Vinculación a la compañía'].meses").value(Matchers.greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.result['Tiempo de Vinculación a la compañía'].días").value(Matchers.greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.result['Edad actual del empleado']").isNotEmpty())
                .andExpect(jsonPath("$.result['Edad actual del empleado'].años").value(Matchers.greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.result['Edad actual del empleado'].meses").value(Matchers.greaterThanOrEqualTo(0)))
                .andExpect(jsonPath("$.result['Edad actual del empleado'].días").value(Matchers.greaterThanOrEqualTo(0)));
    }
}