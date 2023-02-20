package peaksoft.servicies;

import peaksoft.models.Doctor;

import java.util.List;

/**
 * ~ @created 17/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
public interface DoctorService {
    void saveDoctor(Doctor doctor, Long hospitalId);

    List<Doctor> findAll();

    Doctor deleteById(Long id);

    Doctor findById(Long id);

    Doctor updateDoctor(Long id, Doctor doctor);

    void assignDoctorToDepartment(Long departmentId, Long doctorId);
}
