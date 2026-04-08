package com.jerrycastro.tienda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {
    @GetMapping("/principal")
    public String mostrarPrincipal(){
        return "principal";
    }
}
