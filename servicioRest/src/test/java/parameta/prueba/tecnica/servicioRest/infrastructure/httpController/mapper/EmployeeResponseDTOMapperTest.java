package parameta.prueba.tecnica.servicioRest.infrastructure.httpController.mapper;

import org.junit.jupiter.api.Test;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs.EmployeeResponseDTO;
import parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs.TimeResponseDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EmployeeResponseDTOMapperTest {
    private final EmployeeResponseDTOMapper employeeRequestMapper = new EmployeeResponseDTOMapper();
    @Test
    void shouldReturnNullWhenEmployeeIsNull() {
        assertNull(employeeRequestMapper.toDTO(null));
    }
    @Test
    void shouldReturnEmployeeResponseDTO() {
        Employee employee = mock(Employee.class);
        when(employee.getAge()).thenReturn(null);
        EmployeeResponseDTO dto = employeeRequestMapper.toDTO(employee);
        assertNotNull(dto);
        TimeResponseDTO timeResponseDTO = dto.employmentDuration();
        assertNotNull(timeResponseDTO);
        assertEquals(0, timeResponseDTO.years());
        assertEquals(0, timeResponseDTO.months());
        assertEquals(0, timeResponseDTO.days());
        assertNull(dto.age());
    }
}