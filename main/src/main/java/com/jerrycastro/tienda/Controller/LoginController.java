package com.jerrycastro.tienda.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/")
    public String inicio(){
        return "redirect:/acceder";
    }

    @GetMapping("/acceder")
    public String mostrarLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model
                        ){
        String userCheck = "admin";
        String passCheck = "123";

        if(userCheck.equals(username) && passCheck.equals(password)){
            session.setAttribute("usuarioLogueado", username);
            return "redirect:/principal";
        }else{
            model.addAttribute("loginError", "Credenciales incorrectas");
            return "login";
        }
    }
}
