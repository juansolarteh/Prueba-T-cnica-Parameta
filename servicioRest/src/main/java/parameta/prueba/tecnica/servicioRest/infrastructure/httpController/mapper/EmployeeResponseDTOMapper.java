package parameta.prueba.tecnica.servicioRest.infrastructure.httpController.mapper;

import org.springframework.stereotype.Component;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs.EmployeeResponseDTO;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs.TimeResponseDTO;

import java.time.Period;

@Component
public class EmployeeResponseDTOMapper {
    public EmployeeResponseDTO toDTO(Employee employee) {
        if (employee == null) return null;
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getLastName(),
                employee.getDocumentType(),
                employee.getDocumentNumber(),
                employee.getBirthDate(),
                employee.getJoiningDate(),
                employee.getPosition(),
                employee.getSalary(),
                toTimeResponseDTO(employee.getEmploymentDuration()),
                toTimeResponseDTO(employee.getAge())
        );
    }

    private TimeResponseDTO toTimeResponseDTO(Period time) {
        if (time == null) return null;
        return new TimeResponseDTO(
                time.getYears(),
                time.getMonths(),
                time.getDays()
        );
    }
}
