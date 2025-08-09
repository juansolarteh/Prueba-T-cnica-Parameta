package parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TimeResponseDTO(
        @JsonProperty("años") int years,
        @JsonProperty("meses") int months,
        @JsonProperty("días") int days
) {}
