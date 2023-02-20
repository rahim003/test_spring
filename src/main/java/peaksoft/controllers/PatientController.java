package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Hospital;
import peaksoft.models.Patient;
import peaksoft.servicies.HospitalService;
import peaksoft.servicies.PatientService;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 19/02/2023 - 22:21
 **/
@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    private final HospitalService hospitalService;

    @Autowired
    public PatientController(PatientService patientService, HospitalService hospitalService) {
        this.patientService = patientService;
        this.hospitalService = hospitalService;
    }

    @GetMapping
    public String showPatientList(Model model) {
        List<Patient> patients = patientService.findAll();
        model.addAttribute("patients", patients);
        return "patient/patient_list";
    }

    @GetMapping("/new")
    public String showNewPatientForm(Model model) {
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("patient", new Patient());
        return "patient/new_patient";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient, patient.getHospital().getId());
        return "redirect:/patients";
    }

    @GetMapping("/patients/{id}/edit")
    public String showEditPatientForm(@PathVariable("id") Long id, Model model) {
        Patient patient = patientService.findById(id);
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("patient", patient);
        return "patient/edit_patient";
    }

    @PostMapping("/patients/{id}")
    public String updatePatient(@PathVariable("id") Long id, @ModelAttribute("patient") Patient patient) {
        patientService.updatePatient(id, patient);
        return "redirect:/patients";
    }

    @GetMapping("/patients/{id}/delete")
    public String deletePatient(@PathVariable("id") Long id) {
        patientService.deleteById(id);
        return "redirect:/patients";
    }


}

