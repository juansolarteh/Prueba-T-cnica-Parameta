package parameta.prueba.tecnica.servicioSoap.services;

import parameta.prueba.tecnica.servicioSoap.databaseModels.Employee;

public interface SaveEmployeeServiceInt {
    Employee execute(Employee employee);
}
