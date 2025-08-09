package parameta.prueba.tecnica.servicioRest.domain.exceptions;

import parameta.prueba.tecnica.servicioRest.domain.exceptions.generic.DomainPropertyException;

public class EmployeeIsMinorException extends DomainPropertyException {
  public EmployeeIsMinorException(String propertyName) {
    super("Error en la propiedad '" + propertyName + "': El empleado no puede ser menor de edad.");
  }
}
