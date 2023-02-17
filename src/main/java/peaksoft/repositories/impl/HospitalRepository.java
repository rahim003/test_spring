package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Hospital;
import peaksoft.repositories.HospitalRepo;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Repository
@Transactional
public class HospitalRepository implements HospitalRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void saveHospital(Hospital hospital) {
        entityManager.merge(hospital);
    }

    @Override
    public List<Hospital> findAll() {
        return entityManager.createQuery("select h from Hospital  h", Hospital.class).getResultList();
    }

    @Override
    public Hospital deleteById(Long id) {
        final Hospital hospital = entityManager.find(Hospital.class, id);
        entityManager.remove(hospital);
        return hospital;
    }

    @Override
    public Hospital findById(Long id) {
        return entityManager.find(Hospital.class, id);
    }

    @Override
    public Hospital updateHospital(Long id, Hospital hospital) {
        final Hospital hospital1 = entityManager.find(Hospital.class, id);
        hospital1.setName(hospital.getName());
        hospital1.setAddress(hospital.getAddress());
        return entityManager.merge(hospital1);

    }

    @Override
    public List<Hospital> searchByName(String name) {
        return entityManager.createQuery("select h from Hospital h where h.name=:name",Hospital.class).getResultList();
    }


}
