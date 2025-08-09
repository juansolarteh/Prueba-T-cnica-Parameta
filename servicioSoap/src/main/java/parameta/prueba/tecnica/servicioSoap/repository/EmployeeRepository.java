package parameta.prueba.tecnica.servicioSoap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parameta.prueba.tecnica.servicioSoap.databaseModels.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
