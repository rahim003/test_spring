package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.EAGER;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Entity
@Table(name = "doctors")
@Setter
@Getter
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_generator")
    @SequenceGenerator(name = "doctor_sequence", sequenceName = "doctor_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private String email;
    @ManyToOne(cascade = {PERSIST, DETACH, REFRESH}, fetch = EAGER)
    private Hospital hospital;
    @ManyToMany(cascade = {PERSIST, DETACH, REFRESH, MERGE}, fetch = EAGER)
    private Set<Department> departments;

    public Doctor(String firstName, String lastName, String position, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
    }

    public void setDepartment(Department department) {
        if (departments == null) {
            departments = new HashSet<>();
        }
        departments.add(department);
    }
    public Department getDepartment(){
        return departments.stream().findAny().get();
    }
}
