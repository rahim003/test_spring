package peaksoft.servicies;

import peaksoft.models.Department;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
public interface DepartmentService {
    void save(Department department, Long hospitalId);

    List<Department> findAll();

    Department findById(Long id);

    void deleteById(Long id);

    Department update(Long id, Department department);
}
