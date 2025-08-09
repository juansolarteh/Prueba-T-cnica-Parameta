package parameta.prueba.tecnica.servicioRest.application.ports.in;

import parameta.prueba.tecnica.servicioRest.domain.entity.Employee;

import java.util.Date;

public interface CreateEmployeeServicePort {
    /**
     * Crea un nuevo empleado.
     * @param name             Nombre del empleado.
     * @param lastName         Apellido del empleado.
     * @param documentType     Tipo de documento del empleado.
     * @param documentNumber   Número de documento del empleado.
     * @param birthDate        Fecha de nacimiento del empleado.
     * @param joiningDate      Fecha de vinculación a la compañía.
     * @param position         Cargo del empleado.
     * @param salary           Salario del empleado.
     * @return Un objeto Employee con los datos proporcionados.
     */
    Employee execute(String name,
                     String lastName,
                     String documentType,
                     String documentNumber,
                     Date birthDate,
                     Date joiningDate,
                     String position,
                     Double salary);
}
