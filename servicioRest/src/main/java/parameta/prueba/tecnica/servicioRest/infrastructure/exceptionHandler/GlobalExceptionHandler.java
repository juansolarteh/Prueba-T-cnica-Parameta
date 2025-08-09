package parameta.prueba.tecnica.servicioRest.infrastructure.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import parameta.prueba.tecnica.servicioRest.domain.exceptions.generic.DomainPropertyException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HttpExceptionResponseDTO> handleMissingParams(MissingServletRequestParameterException ex) {
        HttpExceptionResponseDTO response = new HttpExceptionResponseDTO(
                "Error: Faltan parámetros requeridos - " + ex.getParameterName(),
                400
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<HttpExceptionResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = ex.getRequiredType() != null
                ? "Error: Tipo de dato incorrecto para el parámetro - " + ex.getName() + ". Se esperaba: " + ex.getRequiredType().getSimpleName()
                : "Error: Tipo de dato incorrecto para el parámetro - " + ex.getName();

        HttpExceptionResponseDTO response = new HttpExceptionResponseDTO(
                message,
                400
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DomainPropertyException.class)
    public ResponseEntity<HttpExceptionResponseDTO> handleDomainException(DomainPropertyException ex) {
        HttpExceptionResponseDTO response = new HttpExceptionResponseDTO(
                ex.getMessage(),
                400
        );
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpExceptionResponseDTO> handleRuntimeException(RuntimeException ex) {
        logger.error(ex.getMessage(), ex);
        HttpExceptionResponseDTO response = new HttpExceptionResponseDTO(
                "Error interno del servidor",
                500
        );
        return ResponseEntity.internalServerError().body(response);
    }
}
