package peaksoft.servicies;

import peaksoft.models.Patient;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 19/02/2023 - 22:12
 **/
public interface PatientService {
    void savePatient(Patient patient,Long hospitalId);

    List<Patient> findAll();

    Patient deleteById(Long id);

    Patient findById(Long id);

    Patient updatePatient(Long id, Patient patient);
}
