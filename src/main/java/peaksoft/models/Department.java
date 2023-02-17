package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Entity
@Table(name = "departments")
@Setter
@Getter
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_generator")
    @SequenceGenerator(name = "department_sequence", sequenceName = "department_seq", allocationSize = 1)
    private Long id;
    private String name;
    @ManyToOne(cascade = {MERGE, PERSIST,DETACH,REFRESH},fetch = FetchType.EAGER)
    private Hospital hospital;
    @ManyToMany(cascade = {DETACH, REFRESH, REMOVE}, fetch = FetchType.EAGER, mappedBy = "departments")
    private Set<Doctor> doctors = new HashSet<>();

    public Department(Long id, String name, Hospital hospital) {
        this.id = id;
        this.name = name;
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name +"";

    }
}
