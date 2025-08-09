package parameta.prueba.tecnica.servicioRest.infrastructure.httpController;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parameta.prueba.tecnica.servicioRest.domain.constants.EmployeeProperties;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;
import parameta.prueba.tecnica.servicioRest.application.ports.in.CreateEmployeeServicePort;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs.EmployeeResponseDTO;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs.HttpResponseDTO;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.mapper.EmployeeResponseDTOMapper;

import java.util.Date;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
    private final CreateEmployeeServicePort createEmployeeServicePort;
    private final EmployeeResponseDTOMapper employeeResponseDTOMapper;
    @GetMapping
    public ResponseEntity<HttpResponseDTO<EmployeeResponseDTO>> saveEmployee(@RequestParam(value = EmployeeProperties.NAMES) String name,
                                                                             @RequestParam(value = EmployeeProperties.LAST_NAMES) String lastName,
                                                                             @RequestParam(value = EmployeeProperties.DOCUMENT_TYPE) String documentType,
                                                                             @RequestParam(value = EmployeeProperties.DOCUMENT_NUMBER) String documentNumber,
                                                                             @RequestParam(value = EmployeeProperties.BIRTH_DATE) Date birthDate,
                                                                             @RequestParam(value = EmployeeProperties.JOINING_DATE) Date joiningDate,
                                                                             @RequestParam(value = EmployeeProperties.POSITION) String position,
                                                                             @RequestParam(value = EmployeeProperties.SALARY) Double salary) {
        Employee employee = createEmployeeServicePort.execute(
                name,
                lastName,
                documentType,
                documentNumber,
                birthDate,
                joiningDate,
                position,
                salary
        );
        EmployeeResponseDTO employeeResponseDTO = employeeResponseDTOMapper.toDTO(employee);
        HttpResponseDTO<EmployeeResponseDTO> response = new HttpResponseDTO<>(employeeResponseDTO, 201);
        return ResponseEntity.status(201).body(response);
    }
}
