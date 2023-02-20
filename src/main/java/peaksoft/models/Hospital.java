package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @author Mukhammed Asantegin
 */
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
    @Column(length = 10000)
    private String image;
    @Column(name = "phone_number")
    private String phoneNumber;
    private LocalDate established_date;
    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "hospital")
    private Set<Doctor> doctors;
    @OneToMany(cascade = ALL, fetch = EAGER, mappedBy = "hospital")
    private Set<Patient> patients = new HashSet<>();
    @OneToMany(cascade = ALL, fetch = LAZY, mappedBy = "hospital")
    private Set<Department> departments;
    @OneToMany(cascade = ALL)
    private Set<Appointment> appointments;


}
