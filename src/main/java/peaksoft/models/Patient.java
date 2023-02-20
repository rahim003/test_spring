package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.Set;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.SEQUENCE;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "patient_generator")
    @SequenceGenerator(name = "patients_sequence", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    @ManyToOne(cascade = {PERSIST, DETACH, MERGE,REFRESH})
    private Hospital hospital;
    @OneToMany(cascade = ALL)
    private Set<Appointment> appointments;

    public Patient(String firstName, String lastName, String phoneNumber, Gender gender, String email, Hospital hospital) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.hospital = hospital;
    }
}
