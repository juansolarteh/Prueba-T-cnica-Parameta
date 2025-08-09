package parameta.prueba.tecnica.servicioSoap.mappers;

import org.springframework.stereotype.Component;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.servicioSoap.databaseModels.Employee;

@Component
public class EmployeeMapper {
    public Employee fromRequestDTO(SaveEmployeeRequestDTO saveEmployeeRequestDTO) {
        if (saveEmployeeRequestDTO == null) return null;
        return new Employee(
            null,
            saveEmployeeRequestDTO.getName(),
            saveEmployeeRequestDTO.getLastName(),
            saveEmployeeRequestDTO.getDocumentType(),
            saveEmployeeRequestDTO.getDocumentNumber(),
            saveEmployeeRequestDTO.getBirthDate(),
            saveEmployeeRequestDTO.getJoiningDate(),
            saveEmployeeRequestDTO.getPosition(),
            saveEmployeeRequestDTO.getSalary()
        );
    }
}
