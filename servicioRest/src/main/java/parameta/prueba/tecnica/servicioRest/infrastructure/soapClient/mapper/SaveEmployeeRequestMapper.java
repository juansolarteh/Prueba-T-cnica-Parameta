package parameta.prueba.tecnica.servicioRest.infrastructure.soapClient.mapper;

import org.springframework.stereotype.Component;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;

@Component
public class SaveEmployeeRequestMapper {
    public SaveEmployeeRequestDTO toDTO(Employee employee) {
        if (employee == null) return null;
        return new SaveEmployeeRequestDTO(
                employee.getName(),
                employee.getLastName(),
                employee.getDocumentType(),
                employee.getDocumentNumber(),
                employee.getBirthDate(),
                employee.getJoiningDate(),
                employee.getPosition(),
                employee.getSalary()
        );
    }
}
