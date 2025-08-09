package parameta.prueba.tecnica.servicioRest.infrastructure.soapClient.mapper;

import org.springframework.stereotype.Component;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;

@Component
public class SaveEmployeeRequestMapper {
    public SaveEmployeeRequestDTO toDTO(Employee employee) {
        if (employee == null) return null;
        SaveEmployeeRequestDTO dto = new SaveEmployeeRequestDTO();
        dto.setBirthDate(employee.getBirthDate());
        dto.setDocumentNumber(employee.getDocumentNumber());
        dto.setName(employee.getName());
        dto.setLastName(employee.getLastName());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        dto.setDocumentType(employee.getDocumentType());
        dto.setJoiningDate(employee.getJoiningDate());
        return dto;
    }
}
