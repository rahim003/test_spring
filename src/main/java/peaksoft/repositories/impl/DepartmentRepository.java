package peaksoft.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.models.Department;
import peaksoft.repositories.DepartmentRepo;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Repository
@Transactional
public class DepartmentRepository implements DepartmentRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Department save(Department department) {
        entityManager.merge(department);
        return department;
    }

    @Override
    public Department findById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public List<Department> findAll() {
        return entityManager.createQuery("select d from Department d", Department.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
         entityManager.createQuery("delete from Department d where d.id=:id").executeUpdate();
    }

    @Override
    public Department update(Long id, Department department) {
        final Department department1 = entityManager.find(Department.class, id);
        department1.setName(department.getName());
        return entityManager.merge(department1);
    }
}
