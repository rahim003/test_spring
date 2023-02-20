package peaksoft.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author krasa kurbanov
 * @created 17/02/2023 - 17:36
 **/
@Controller
@RequestMapping("/")
public class Main {
    @GetMapping
    public String main(){
        return "index";
    }
}
