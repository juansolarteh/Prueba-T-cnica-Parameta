package parameta.prueba.tecnica.common.DTOs;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SaveEmployeeResponse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveEmployeeResponseDTO {
    private long employeeId;
    private String message;
}
