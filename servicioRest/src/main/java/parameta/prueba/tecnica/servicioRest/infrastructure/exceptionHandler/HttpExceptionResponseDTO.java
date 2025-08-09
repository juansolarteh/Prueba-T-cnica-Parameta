package parameta.prueba.tecnica.servicioRest.infrastructure.exceptionHandler;

import lombok.Getter;

@Getter
public class HttpExceptionResponseDTO{
    private final boolean success = false;
    private final int statusCode;
    private final String message;

    public HttpExceptionResponseDTO(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
