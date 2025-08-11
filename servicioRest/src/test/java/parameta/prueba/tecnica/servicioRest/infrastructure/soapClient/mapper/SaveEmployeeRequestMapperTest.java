package parameta.prueba.tecnica.servicioRest.infrastructure.soapClient.mapper;

import org.junit.jupiter.api.Test;
import parameta.prueba.tecnica.common.DTOs.SaveEmployeeRequestDTO;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

class SaveEmployeeRequestMapperTest {
    private final SaveEmployeeRequestMapper saveEmployeeRequestMapper = new SaveEmployeeRequestMapper();
    @Test
    void shouldReturnNullWhenEmployeeIsNull() {
        assertNull(saveEmployeeRequestMapper.toDTO(null));
    }
    @Test
    void shouldReturnSaveEmployeeRequestDTOWhenEmployeeIsNotNull() {
        Employee employee = mock(Employee.class);
        SaveEmployeeRequestDTO dto = saveEmployeeRequestMapper.toDTO(employee);
        assertNotNull(dto);
    }
}