package peaksoft.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Hospital;
import peaksoft.repositories.HospitalRepo;
import peaksoft.servicies.HospitalService;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Service
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepo hospitalRepo;

    @Autowired
    public HospitalServiceImpl(HospitalRepo hospitalRepo) {
        this.hospitalRepo = hospitalRepo;
    }

    @Override
    public Hospital saveHospital(Hospital hospital) {
        hospitalRepo.saveHospital(hospital);
        return hospital;
    }

    @Override
    public List<Hospital> findAll() {
        return hospitalRepo.findAll();
    }

    @Override
    public Hospital deleteById(Long id) {
        return hospitalRepo.deleteById(id);
    }

    @Override
    public Hospital findById(Long id) {
        return hospitalRepo.findById(id);
    }

    @Override
    public Hospital updateHospital(Long id, Hospital hospital) {
        return hospitalRepo.updateHospital(id, hospital);
    }

    @Override
    public List<Hospital> searchByName(String name) {
        return hospitalRepo.searchByName(name);
    }
}
