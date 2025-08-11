package parameta.prueba.tecnica.servicioRest.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.EmployeeIsMinorException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.NullException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.NullOrEmptyException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.NullOrNegativeNumberException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    private Date birthDate;
    private Date joiningDate;
    private String position;
    private Double salary;

    @BeforeEach
    public void setUp() {
        name = "John";
        lastName = "Doe";
        documentType = "CC";
        documentNumber = "123456789";
        birthDate = new Date(946684800000L);
        joiningDate = new Date(946684800000L);
        position = "Developer";
        salary = 5000.0;
    }

    @Test
    public void shouldThrowExceptionWhenNameIsBlankOnCreate() {
        name = "   ";
        assertThrows(NullOrEmptyException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenLastNameIsBlankOnCreate() {
        lastName = "   ";
        assertThrows(NullOrEmptyException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenDocumentTypeIsBlankOnCreate() {
        documentType = "   ";
        assertThrows(NullOrEmptyException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenDocumentNumberIsBlankOnCreate() {
        documentNumber = "   ";
        assertThrows(NullOrEmptyException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenBirthDateIsNullOnCreate() {
        birthDate = null;
        assertThrows(NullException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenJoiningDateIsNullOnCreate() {
        joiningDate = null;
        assertThrows(NullException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenPositionIsBlankOnCreate() {
        position = "   ";
        assertThrows(NullOrEmptyException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenSalaryIsZeroOnCreate() {
        salary = 0.0;
        assertThrows(NullOrNegativeNumberException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldThrowExceptionWhenSalaryEmployeeIsMinorOnCreate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date birthDate = cal.getTime();
        assertThrows(EmployeeIsMinorException.class, () -> Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary));
    }

    @Test
    public void shouldReturnEmployeeWhenAllFieldsAreValidOnCreate() {
        Employee employee = Employee.create(name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary);
        assertNull(employee.getId());
        assertEquals(name, employee.getName());
        assertEquals(lastName, employee.getLastName());
        assertEquals(documentType, employee.getDocumentType());
        assertEquals(documentNumber, employee.getDocumentNumber());
        assertEquals(birthDate, employee.getBirthDate());
        assertEquals(joiningDate, employee.getJoiningDate());
        assertEquals(position, employee.getPosition());
        assertEquals(salary, employee.getSalary());

        Period age = Period.between(
                birthDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate(),
                LocalDate.now()
        );
        assertEquals(age, employee.getAge());
    }

    @Test
    public void shouldReturnZeroEmploymentDurationWhenJoiningDateIsNull() {
        Employee employee = new Employee(null, name, lastName, documentType, documentNumber, birthDate, null, position, salary, null);

        Period expectedDuration = Period.ZERO;
        Period actualDuration = employee.getEmploymentDuration();

        assertEquals(expectedDuration, actualDuration);
    }

    @Test
    public void shouldReturnEmploymentDurationWhenJoiningDateIsNotNull() {
        Employee employee = new Employee(null, name, lastName, documentType, documentNumber, birthDate, joiningDate, position, salary, null);

        Period expectedDuration = Period.between(
                joiningDate.toInstant().atZone(Calendar.getInstance().getTimeZone().toZoneId()).toLocalDate(),
                LocalDate.now()
        );
        Period actualDuration = employee.getEmploymentDuration();

        assertEquals(expectedDuration, actualDuration);
    }
}