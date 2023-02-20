package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Doctor;
import peaksoft.models.Hospital;
import peaksoft.servicies.DoctorService;
import peaksoft.servicies.HospitalService;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 19/02/2023 - 21:06
 **/
@Controller
@RequestMapping("/doctors")
public class DoctorController {


    private final DoctorService doctorService;

    private final HospitalService hospitalService;

    @Autowired
    public DoctorController(DoctorService doctorService, HospitalService hospitalService) {
        this.doctorService = doctorService;
        this.hospitalService = hospitalService;
    }

    @GetMapping()
    public String getAllDoctors(Model model) {
        List<Doctor> doctors = doctorService.findAll();
        model.addAttribute("doctors", doctors);
        return "doctor/doctor_list";
    }

    @GetMapping("/doctors/new")
    public String showDoctorForm(Model model) {
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("doctor", new Doctor());
        return "doctor/new_doctor";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        doctorService.saveDoctor(doctor, doctor.getHospital().getId());
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/{id}/edit")
    public String showEditDoctorForm(@PathVariable("id") Long id, Model model) {
        Doctor doctor = doctorService.findById(id);
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("doctor", doctor);
        return "doctor/edit_doctor";
    }

    @PostMapping("/doctors/{id}")
    public String updateDoctor(@PathVariable("id") Long id, @ModelAttribute("doctor") Doctor doctor) {
        doctorService.updateDoctor(id, doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/doctors/{id}/delete")
    public String deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }
}

