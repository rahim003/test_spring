package peaksoft.repositories;

import peaksoft.models.Hospital;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
public interface HospitalRepo {
    void saveHospital(Hospital hospital);

    List<Hospital> findAll();

    Hospital deleteById(Long id);

    Hospital findById(Long id);

    Hospital updateHospital(Long id, Hospital hospital);

    List<Hospital> searchByName(String name);
}
