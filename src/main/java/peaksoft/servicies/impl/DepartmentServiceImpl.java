package peaksoft.servicies.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.models.Department;
import peaksoft.models.Hospital;
import peaksoft.repositories.DepartmentRepo;
import peaksoft.repositories.HospitalRepo;
import peaksoft.servicies.DepartmentService;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final HospitalRepo hospitalRepo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, HospitalRepo hospitalRepo) {
        this.departmentRepo = departmentRepo;
        this.hospitalRepo = hospitalRepo;
    }

    @Override
    public void save(Department department, Long hospitalId) {
        final Hospital byId = hospitalRepo.findById(hospitalId);
        department.setHospital(byId);
        departmentRepo.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepo.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        departmentRepo.deleteById(id);
    }

    @Override
    public Department update(Long id, Department department) {
        return departmentRepo.update(id, department);
    }
}
