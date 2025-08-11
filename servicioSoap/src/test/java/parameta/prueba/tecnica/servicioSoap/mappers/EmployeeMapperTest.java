package parameta.prueba.tecnica.servicioSoap.mappers;

import org.junit.jupiter.api.Test;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.servicioSoap.databaseModels.Employee;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    @Test
    void shouldReturnNullWhenRequestDTOIsNull() {
        assertNull(employeeMapper.fromRequestDTO(null));
    }
    @Test
    void shouldReturnEmployeeWhenRequestDTOIsNotNull() {
        Date date = new Date();
        SaveEmployeeRequestDTO requestDTO = new SaveEmployeeRequestDTO(
            "John",
            "Doe",
            "CC",
            "123456789",
            date,
                date,
            "Developer",
            5000.0
        );
        Employee employee = employeeMapper.fromRequestDTO(requestDTO);
        assertNull(employee.getId());
        assertNotNull(employee);
        assertEquals("John", employee.getName());
        assertEquals("Doe", employee.getLastName());
        assertEquals("CC", employee.getDocumentType());
        assertEquals("123456789", employee.getDocumentNumber());
        assertEquals(date, employee.getBirthDate());
        assertEquals(date, employee.getJoiningDate());
        assertEquals("Developer", employee.getPosition());
        assertEquals(5000.0, employee.getSalary());
    }
}