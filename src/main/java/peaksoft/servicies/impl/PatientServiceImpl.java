package peaksoft.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Hospital;
import peaksoft.models.Patient;
import peaksoft.repositories.HospitalRepo;
import peaksoft.repositories.PatientRepo;
import peaksoft.servicies.PatientService;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 19/02/2023 - 22:13
 **/
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalRepo hospitalRepo;

    @Autowired
    public PatientServiceImpl(PatientRepo patientRepo, HospitalRepo hospitalRepo) {
        this.patientRepo = patientRepo;
        this.hospitalRepo = hospitalRepo;
    }

    @Override
    public void savePatient(Patient patient, Long hospitalId) {
        final Hospital hospital = hospitalRepo.findById(hospitalId);
        patient.setHospital(hospital);
        patientRepo.savePatient(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public Patient deleteById(Long id) {
        return patientRepo.deleteById(id);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepo.findById(id);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        return patientRepo.updatePatient(id, patient);
    }
}
