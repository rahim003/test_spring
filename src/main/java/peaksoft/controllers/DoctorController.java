package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Department;
import peaksoft.models.Doctor;
import peaksoft.servicies.DepartmentService;
import peaksoft.servicies.DoctorService;

import java.util.List;

/**
 * ~ @created 17/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Controller
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @Autowired
    public DoctorController(DoctorService doctorService, DepartmentService departmentService) {
        this.doctorService = doctorService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("doctors", doctorService.findAll());
        return "doctor/doctors";
    }

    @GetMapping("/new")
    public String create(Model model) {
        Doctor doctor = new Doctor();
        final List<Department> departments = departmentService.findAll();
        model.addAttribute("doctor", doctor);
        model.addAttribute("departments", departments);
        return "doctor/new-doctor";
    }

    @PostMapping("/save")
    public String addNewDoctor(@ModelAttribute("doctor") Doctor doctor, @RequestParam("departmentId") Long departmentId) {
        doctorService.saveDoctor(doctor, departmentId);
        return "redirect:/doctors";
    }


}
