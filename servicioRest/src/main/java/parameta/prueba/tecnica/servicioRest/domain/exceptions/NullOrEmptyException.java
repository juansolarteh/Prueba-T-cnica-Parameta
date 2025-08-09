package parameta.prueba.tecnica.servicioRest.domain.exceptions;

import parameta.prueba.tecnica.servicioRest.domain.exceptions.generic.DomainPropertyException;

public class NullOrEmptyException extends DomainPropertyException {
  public NullOrEmptyException(String propertyName) {
    super("La propiedad '" + propertyName + "' no puede ser nula o vacía.");
  }
}
