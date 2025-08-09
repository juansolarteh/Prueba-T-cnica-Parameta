package parameta.prueba.tecnica.servicioRest.domain.exceptions;

import parameta.prueba.tecnica.servicioRest.domain.exceptions.generic.DomainPropertyException;

public class NullException extends DomainPropertyException {
  public NullException(String propertyName) {
    super("La propiedad '" + propertyName + "' no puede ser nula.");
  }
}
