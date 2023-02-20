package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_generator")
    @SequenceGenerator(name = "department_sequence", sequenceName = "department_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String image;
    @ManyToMany(cascade = {DETACH, REFRESH, REMOVE}, fetch = FetchType.EAGER, mappedBy = "departments")
    private Set<Doctor> doctors = new HashSet<>();
    @ManyToOne(cascade = {REFRESH, MERGE, PERSIST, DETACH})
    private Hospital hospital;


    public void setDoctor(Doctor doctor) {
        if (doctors == null) {
            doctors = new HashSet<>();
        }
        doctors.add(doctor);
    }
}
