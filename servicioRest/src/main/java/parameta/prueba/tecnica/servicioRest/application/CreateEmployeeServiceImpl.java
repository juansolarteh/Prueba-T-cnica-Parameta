package parameta.prueba.tecnica.servicioRest.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;
import parameta.prueba.tecnica.servicioRest.domain.ports.in.CreateEmployeeServicePort;
import parameta.prueba.tecnica.servicioRest.domain.ports.out.SaveEmployeePort;

import java.util.Date;

@Service
@AllArgsConstructor
public class CreateEmployeeServiceImpl implements CreateEmployeeServicePort {

    private final SaveEmployeePort saveEmployeePort;

    @Override
    public Employee execute(String name, String lastName, String documentType, String documentNumber, Date birthDate, Date joiningDate, String position, Double salary) {
        Employee employee = Employee.create(
                name,
                lastName,
                documentType,
                documentNumber,
                birthDate,
                joiningDate,
                position,
                salary);
        return saveEmployeePort.execute(employee);
    }
}
