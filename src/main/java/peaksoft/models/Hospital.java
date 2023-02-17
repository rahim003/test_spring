package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Entity
@Table(name = "hospitals")
@NoArgsConstructor
@Getter
@Setter
public class Hospital {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "hospital_generator")
    @SequenceGenerator(name = "hospital_sequence", sequenceName = "hospital_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REFRESH,CascadeType.DETACH})
    private List<Department> department;

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
