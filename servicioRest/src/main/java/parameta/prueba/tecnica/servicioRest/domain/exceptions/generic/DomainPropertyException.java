package parameta.prueba.tecnica.servicioRest.domain.exceptions.generic;

public abstract class DomainPropertyException extends RuntimeException {
  public DomainPropertyException(String message) {
    super(message);
  }
}
