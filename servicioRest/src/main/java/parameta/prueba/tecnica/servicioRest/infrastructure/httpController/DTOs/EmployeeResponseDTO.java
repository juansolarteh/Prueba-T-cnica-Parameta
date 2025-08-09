package parameta.prueba.tecnica.servicioRest.infrastructure.httpController.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import parameta.prueba.tecnica.servicioRest.domain.constants.EmployeeProperties;

import java.util.Date;

public record EmployeeResponseDTO(
        @JsonProperty("Id") Long id,
        @JsonProperty(EmployeeProperties.NAMES) String name,
        @JsonProperty(EmployeeProperties.LAST_NAMES) String lastName,
        @JsonProperty(EmployeeProperties.DOCUMENT_TYPE) String documentType,
        @JsonProperty(EmployeeProperties.DOCUMENT_NUMBER) String documentNumber,
        @JsonProperty(EmployeeProperties.BIRTH_DATE) Date birthDate,
        @JsonProperty(EmployeeProperties.JOINING_DATE) Date joiningDate,
        @JsonProperty(EmployeeProperties.POSITION) String position,
        @JsonProperty(EmployeeProperties.SALARY) Double salary,
        @JsonProperty("Tiempo de Vinculación a la compañía") TimeResponseDTO employmentDuration,
        @JsonProperty("Edad actual del empleado") TimeResponseDTO age
) {}
