package parameta.prueba.tecnica.servicioRest.infrastructure.soapClient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeResponseDTO;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;
import parameta.prueba.tecnica.servicioRest.application.ports.out.SaveEmployeePort;
import parameta.prueba.tecnica.servicioRest.infrastructure.soapClient.mapper.SaveEmployeeRequestMapper;

@Component
@AllArgsConstructor
public class SaveEmployeeSoapClientImpl implements SaveEmployeePort {
    private final SaveEmployeeRequestMapper saveEmployeeRequestMapper;
    private final WebServiceTemplate webServiceTemplate;
    @Override
    public Employee execute(Employee employee) {
        SaveEmployeeRequestDTO request = saveEmployeeRequestMapper.toDTO(employee);
        SaveEmployeeResponseDTO response = (SaveEmployeeResponseDTO) webServiceTemplate.marshalSendAndReceive(request);
        employee.setId(response.getEmployeeId());
        return employee;
    }
}
