package parameta.prueba.tecnica.servicioSoap.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import parameta.prueba.tecnica.servicioSoap.databaseModels.Employee;
import parameta.prueba.tecnica.servicioSoap.repository.EmployeeRepository;

@AllArgsConstructor
@Service
public class SaveEmployeeServiceImpl implements SaveEmployeeServiceInt{
    private final EmployeeRepository employeeRepository;
    @Override
    public Employee execute(Employee employee) {
        return employeeRepository.save(employee);
    }
}
