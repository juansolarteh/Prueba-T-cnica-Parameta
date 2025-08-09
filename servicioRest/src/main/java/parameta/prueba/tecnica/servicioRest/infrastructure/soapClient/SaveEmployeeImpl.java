package parameta.prueba.tecnica.servicioRest.infrastructure.soapClient;

import org.springframework.stereotype.Component;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;
import parameta.prueba.tecnica.servicioRest.domain.ports.out.SaveEmployeePort;

@Component
public class SaveEmployeeImpl implements SaveEmployeePort {
    @Override
    public Employee execute(Employee employee) {
        employee.setId(1L);
        return employee;
    }
}
