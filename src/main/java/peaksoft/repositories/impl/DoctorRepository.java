package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Doctor;
import peaksoft.repositories.DoctorRepo;

import java.util.List;

/**
 * ~ @created 17/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Repository
@Transactional
public class DoctorRepository implements DoctorRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveDoctor(Doctor doctor) {
        entityManager.persist(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return entityManager.createQuery("select d from Doctor  d ", Doctor.class).getResultList();
    }

    @Override
    public Doctor deleteById(Long id) {
        final Doctor doctor = entityManager.find(Doctor.class, id);
        entityManager.remove(doctor);
        return doctor;
    }

    @Override
    public Doctor findById(Long id) {
        return entityManager.find(Doctor.class, id);
    }

    @Override
    public Doctor updateHospital(Long id, Doctor doctor) {
        final Doctor doctor1 = entityManager.find(Doctor.class, id);
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setEmail(doctor.getEmail());
        return entityManager.merge(doctor1);
    }
}
