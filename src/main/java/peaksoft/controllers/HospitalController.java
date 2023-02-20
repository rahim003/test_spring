package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Hospital;
import peaksoft.servicies.HospitalService;

import java.util.List;

/**
 * @author krasa kurbanov
 * @created 17/02/2023 - 17:18
 **/
@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping()
    public String getHospitals(Model model) {
        List<Hospital> hospitals = hospitalService.findAll();
        model.addAttribute("hospitals", hospitals);
        return "hospital/hospitalDepartments";
    }

    @GetMapping("/new")
    public String newHospital(Model model) {
        model.addAttribute("hospital", new Hospital());
        return "hospital/new_hospital";
    }

    @PostMapping("/save")
    public String createHospital(@ModelAttribute("hospital") Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/hospitals/{id}/edit")
    public String editHospital(@PathVariable("id") Long id, Model model) {
        Hospital hospital = hospitalService.findById(id);
        model.addAttribute("hospital", hospital);
        return "hospital/edit_hospital";
    }

    @PostMapping("/hospitals/{id}")
    public String updateHospital(@PathVariable("id") Long id, @ModelAttribute("hospital") Hospital hospital) {
        hospitalService.updateHospital(id, hospital);
        return "redirect:/hospitals";
    }

    @GetMapping("/hospitals/{id}/delete")
    public String deleteHospital(@PathVariable("id") Long id) {
        hospitalService.deleteById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/hospitals/search")
    public String searchHospital(@RequestParam("name") String name, Model model) {
        List<Hospital> hospitals = hospitalService.searchByName(name);
        model.addAttribute("hospitals", hospitals);
        return "hospital/hospitals";
    }
    @GetMapping("/departments/doctors/{hospitalId}")
    public String findDepartmentsAndDoctorsByHospitalId(Model model, @PathVariable Long hospitalId) {
        final Hospital hospital = hospitalService.findById(hospitalId);
        model.addAttribute("departments", hospital.getDepartments());
        model.addAttribute("doctors", hospital.getDoctors());
        return "/index";
    }
}

