package peaksoft.repositories;

import peaksoft.models.Patient;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 19/02/2023 - 22:01
 **/

public interface PatientRepo {
    void savePatient(Patient patient);

    List<Patient> findAll();

    Patient deleteById(Long id);

    Patient findById(Long id);

    Patient updatePatient(Long id, Patient patient);
}
