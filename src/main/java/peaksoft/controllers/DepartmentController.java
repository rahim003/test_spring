package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Department;
import peaksoft.models.Hospital;
import peaksoft.servicies.DepartmentService;
import peaksoft.servicies.HospitalService;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 17/02/2023 - 18:19
 **/
@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping()
    public String list(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/department_list";
    }

    @GetMapping("/new")
    public String newDepartment(Model model) {
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("department", new Department());
        return "department/new_department";
    }

    @PostMapping("/departments/save")
    public String save(@ModelAttribute("department") Department department) {
        departmentService.save(department, department.getHospital().getId());
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String editHospital(@PathVariable("id") Long id, Model model) {
        final Department department = departmentService.findById(id);
        model.addAttribute("department", department);
        return "department/edit_department";
    }

    @PostMapping("/department/{id}")
    public String updateHospital(@PathVariable("id") Long id, @ModelAttribute("department") Department department) {
        departmentService.update(id, department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteById(id);
        return "redirect:/departments";
    }
}

