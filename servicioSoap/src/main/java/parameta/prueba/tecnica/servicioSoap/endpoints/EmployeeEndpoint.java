package parameta.prueba.tecnica.servicioSoap.endpoints;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeResponseDTO;
import parameta.prueba.tecnica.servicioSoap.databaseModels.Employee;
import parameta.prueba.tecnica.servicioSoap.mappers.EmployeeMapper;
import parameta.prueba.tecnica.servicioSoap.services.SaveEmployeeServiceInt;

@AllArgsConstructor
@Endpoint
@Component
public class EmployeeEndpoint {
    private static final String NAMESPACE = "http://parameta.prueba.tecnica/employee";
    private final SaveEmployeeServiceInt saveEmployeeServiceInt;
    private final EmployeeMapper employeeMapper;

    @PayloadRoot(namespace = NAMESPACE, localPart = "SaveEmployeeRequest")
    @ResponsePayload
    public SaveEmployeeResponseDTO addEmployee(@RequestPayload SaveEmployeeRequestDTO saveEmployeeRequestDTO) {
        Employee employee = employeeMapper.fromRequestDTO(saveEmployeeRequestDTO);
        Employee savedEmployee = saveEmployeeServiceInt.execute(employee);
        return new SaveEmployeeResponseDTO(savedEmployee.getId(), "OK");
    }
}
