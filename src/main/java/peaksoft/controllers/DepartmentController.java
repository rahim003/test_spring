package peaksoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Department;
import peaksoft.models.Hospital;
import peaksoft.servicies.DepartmentService;
import peaksoft.servicies.HospitalService;

import java.util.List;

@Controller
public class DepartmentController {
    private final DepartmentService departmentService;
    private final HospitalService hospitalService;

    public DepartmentController(DepartmentService departmentService, HospitalService hospitalService) {
        this.departmentService = departmentService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/departments")
    public String getAllDepartments(Model model) {
        List<Department> departments = departmentService.findAll();
        model.addAttribute("departments", departments);
        return "department/departments";
    }

    @GetMapping("/departments/new")
    public String showNewDepartmentForm(Model model) {
        Department department = new Department();
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("department", department);
        model.addAttribute("hospitals", hospitals);
        return "department/new-department";
    }

    @PostMapping("/departments/new")
    public String addNewDepartment(@ModelAttribute("department") Department department, @RequestParam("hospitalId") Long hospitalId) {
        departmentService.save(department, hospitalId);
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String showEditDepartmentForm(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.findById(id);
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("department", department);
        model.addAttribute("hospitals", hospitals);
        return "department/edit-department";
    }

    @PostMapping("/departments/{id}/edit")
    public String updateDepartment(@PathVariable("id") Long id, @ModelAttribute("department") Department department) {
        departmentService.update(id, department);
        return "redirect:/departments";
    }

    @DeleteMapping("/delete/{id}we")
    public String deleteDepartment(@PathVariable("id") Long id) {
        departmentService.deleteById(id);
        return "redirect:/departments";
    }
}
