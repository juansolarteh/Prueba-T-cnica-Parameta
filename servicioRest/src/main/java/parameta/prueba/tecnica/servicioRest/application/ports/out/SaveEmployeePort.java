package parameta.prueba.tecnica.servicioRest.application.ports.out;

import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;

public interface SaveEmployeePort {
    /**
     * Guarda un empleado.
     * @param employee Empleado a guardar.
     * @return id del empleado guardado.
     */
    Employee execute(Employee employee);
}
