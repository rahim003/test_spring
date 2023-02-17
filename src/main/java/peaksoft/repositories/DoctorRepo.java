package peaksoft.repositories;

import peaksoft.models.Doctor;

import java.util.List;

/**
 * ~ @created 17/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
public interface DoctorRepo {
    void saveDoctor(Doctor doctor);

    List<Doctor> findAll();

    Doctor deleteById(Long id);

    Doctor findById(Long id);

    Doctor updateHospital(Long id, Doctor doctor);
}
