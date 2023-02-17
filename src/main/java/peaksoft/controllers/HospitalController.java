package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.models.Hospital;
import peaksoft.servicies.HospitalService;

import java.util.List;

/**
 * ~ @created 15/02/2023
 * ~ @project_name final_mvc
 * ~ @author kurbanov
 **/
@Controller
@RequestMapping("/")
public class HospitalController {

    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }


    //Бул метод главный страничкага кирип берет
    @GetMapping()
    public String welcome() {
        return "index";
    }

    //Баардык ооруканарды чыгарып берет
    @GetMapping("/hospitals")
    public String findAll(Model model) {
        final List<Hospital> all = hospitalService.findAll();
        model.addAttribute("hospitals", all);
        return "hospital/hospitals";
    }

    //html файлдан келет аны биз пост кылып
    @GetMapping("/add")
    public String createHospital(Model model) {
        model.addAttribute("newHospital", new Hospital());
        return "hospital/saveHospital";

    }

    //дата базага сактайбыз
    @PostMapping("/save")
    public String saveHospital(@ModelAttribute("newHospital") Hospital hospital) {
        hospitalService.saveHospital(hospital);
        return "redirect:/hospitals";
    }

    //delete by id
    @PostMapping("/delete/{id}")
    public String deleteHospital(@PathVariable("id") Long id) {
        hospitalService.deleteById(id);
        return "redirect:/hospitals";
    }

    @GetMapping("/update/{id}")
    public String updateCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("hospital", hospitalService.findById(id));
        return "hospital/updateHospital";
    }


    @PostMapping("{id}/updateHospital")
    public String saveUpdateCompany(@PathVariable("id") Long id, @ModelAttribute("company") Hospital company) {
        hospitalService.updateHospital(id, company);
        return "redirect:/hospitals";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam("name") String name, Model model) {
        List<Hospital> hospitals = hospitalService.searchByName(name);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("name", name);
        return "hospital/hospitals";
    }

}
