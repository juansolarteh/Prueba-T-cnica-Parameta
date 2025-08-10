package parameta.prueba.tecnica.common.DTOs;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import lombok.*;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SaveEmployeeRequest")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmployeeRequestDTO {
    private String name;
    private String lastName;
    private String documentType;
    private String documentNumber;
    @XmlSchemaType(name = "date")
    private Date birthDate;
    @XmlSchemaType(name = "date")
    private Date joiningDate;
    private String position;
    private double salary;
}
