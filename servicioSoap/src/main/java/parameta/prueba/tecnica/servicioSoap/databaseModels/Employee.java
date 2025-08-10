package parameta.prueba.tecnica.servicioSoap.databaseModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "Empleado")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String name;
    @Column(name = "apellido", nullable = false)
    private String lastName;
    @Column(name = "tipo_documento", nullable = false)
    private String documentType;
    @Column(name = "numero_documento", nullable = false)
    private String documentNumber;
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date birthDate;
    @Column(name = "fecha_vinculacion", nullable = false)
    private Date joiningDate;
    @Column(name = "cargo", nullable = false)
    private String position;
    @Column(name = "salario", nullable = false)
    private double salary;
}
