package peaksoft.servicies.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Department;
import peaksoft.models.Doctor;
import peaksoft.models.Hospital;
import peaksoft.repositories.DepartmentRepo;
import peaksoft.repositories.DoctorRepo;
import peaksoft.repositories.HospitalRepo;
import peaksoft.servicies.DoctorService;

import java.util.List;

/**
 * ~ @created 17/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;
    private final DepartmentRepo departmentRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, HospitalRepo hospitalRepo, DepartmentRepo departmentRepo) {
        this.doctorRepo = doctorRepo;

        this.hospitalRepo = hospitalRepo;
        this.departmentRepo = departmentRepo;
    }

    @Override
    @Transactional
    public void saveDoctor(Doctor doctor, Long hospitalId) {
        final Hospital hospital = hospitalRepo.findById(hospitalId);
        doctor.setHospital(hospital);
        doctorRepo.saveDoctor(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepo.findAll();
    }

    @Override
    public Doctor deleteById(Long id) {
        return doctorRepo.deleteById(id);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorRepo.findById(id);
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        return doctorRepo.updateHospital(id, doctor);
    }

    @Override
    @Transactional
    public void assignDoctorToDepartment(Long departmentId, Long doctorId) {
        final Department department = departmentRepo.findById(departmentId);
        final Doctor doctor = doctorRepo.findById(doctorId);
        doctor.setDepartment(department);
        department.setDoctor(doctor);
    }
}
