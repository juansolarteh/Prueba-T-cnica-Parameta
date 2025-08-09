package parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs;

import lombok.Getter;

@Getter
public class HttpResponseDTO<T> {
    private final boolean success = true;
    private final int statusCode;
    private final T result;

    public HttpResponseDTO(T result, int statusCode) {
        this.result = result;
        this.statusCode = statusCode;
    }
}
