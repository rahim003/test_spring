package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Patient;
import peaksoft.repositories.PatientRepo;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 19/02/2023 - 22:02
 **/
@Repository
@Transactional
public class PatientRepository implements PatientRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void savePatient(Patient patient) {
        entityManager.merge(patient);
    }

    @Override
    public List<Patient> findAll() {
        return entityManager.createQuery("select  p from Patient  p", Patient.class).getResultList();
    }

    @Override
    public Patient deleteById(Long id) {
        final Patient patient = entityManager.find(Patient.class, id);
        entityManager.createQuery("delete from Patient p where p.id=:id").setParameter("id",id).executeUpdate();
        return patient;
    }

    @Override
    public Patient findById(Long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {
        final Patient patient1 = entityManager.find(Patient.class, id);
        patient1.setFirstName(patient.getFirstName());
        patient1.setLastName(patient.getLastName());
        patient1.setPhoneNumber(patient.getPhoneNumber());
        patient1.setGender(patient.getGender());
        return entityManager.merge(patient1);
    }
}
