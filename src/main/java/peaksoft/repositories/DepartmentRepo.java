package peaksoft.repositories;

import peaksoft.models.Department;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
public interface DepartmentRepo {
    Department save(Department department);

    Department findById(Long id);

    List<Department> findAll();

    void deleteById(Long id);

    Department update(Long id, Department department);

    public List<Department> findAll(Long hospitalId);
}
