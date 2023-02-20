package peaksoft.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.CascadeType.*;

/**
 * @author Mukhammed Asantegin
 */
@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_generator")
    @SequenceGenerator(name = "appointment_sequence", sequenceName = "appointment_seq", allocationSize = 1)
    private Long id;
    private LocalDate date;
    @ManyToOne(cascade = CascadeType.ALL)
    private Patient patient;
    @ManyToOne(cascade = {PERSIST, MERGE})
    private Doctor doctor;
    @ManyToOne(cascade = ALL)
    private Department department;

}
