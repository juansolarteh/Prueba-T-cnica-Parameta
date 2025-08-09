package parameta.prueba.tecnica.servicioRest.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import parameta.prueba.tecnica.servicioRest.domain.constants.EmployeeProperties;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.EmployeeIsMinorException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.NullException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.NullOrEmptyException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.NullOrNegativeNumberException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@Getter
public class Employee {
    @Setter
    private Long id;
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private Date birthDate;
    private Date joiningDate;
    private String position;
    private Double salary;
    private Period age;

    /**
     * Crea un nuevo empleado.
     * Válida los parámetros de entrada y calcula la edad del empleado.
     * @param name Nombre del empleado
     * @param lastName Apellido del empleado
     * @param documentType Tipo de documento del empleado
     * @param documentNumber Número de documento del empleado
     * @param birthDate Fecha de nacimiento del empleado
     * @param joiningDate Fecha de vinculación a la compañía
     * @param position Cargo del empleado
     * @param salary Salario del empleado
     * @return Un nuevo objeto Employee con los datos proporcionados.
     * @throws NullOrEmptyException Si alguno de los campos de tipo String es nulo o está vacío.
     * @throws NullException Si alguno de los campos de tipo Date es nulo.
     * @throws NullOrNegativeNumberException Si el salario es nulo o negativo.
     * @throws EmployeeIsMinorException Si el empleado es menor de edad.
     */
    public static Employee create(String name,
                           String lastName,
                           String documentType,
                           String documentNumber,
                           Date birthDate,
                           Date joiningDate,
                           String position,
                           Double salary) {
        if (name == null || name.isBlank()) throw  new NullOrEmptyException(EmployeeProperties.NAMES);
        if (lastName == null || lastName.isBlank()) throw new NullOrEmptyException(EmployeeProperties.LAST_NAMES);
        if (documentType == null || documentType.isBlank()) throw new NullOrEmptyException(EmployeeProperties.DOCUMENT_TYPE);
        if (documentNumber == null || documentNumber.isBlank()) throw new NullOrEmptyException(EmployeeProperties.DOCUMENT_NUMBER);
        if (birthDate == null) throw new NullException(EmployeeProperties.BIRTH_DATE);
        if (joiningDate == null) throw new NullException(EmployeeProperties.JOINING_DATE);
        if (position == null || position.isBlank()) throw new NullOrEmptyException(EmployeeProperties.POSITION);
        if (salary == null || salary <= 0) throw new NullOrNegativeNumberException(EmployeeProperties.SALARY);

        Period age = calculateCurrentPeriod(birthDate);
        if (age.getYears() < EmployeeProperties.MIN_AGE) throw new EmployeeIsMinorException(EmployeeProperties.BIRTH_DATE);

        return new Employee(null, name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary, age);
    }

    public Period getEmploymentDuration() {
        return calculateCurrentPeriod(joiningDate);
    }

    private static Period calculateCurrentPeriod(Date startDate) {
        LocalDate startLocalDate = startDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(startLocalDate, LocalDate.now());
    }
}
