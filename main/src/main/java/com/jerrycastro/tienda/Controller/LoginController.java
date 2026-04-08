package com.jerrycastro.tienda.Controller;

import org.springframework.stereotype.Controller;
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
                        @RequestParam("password") String password
                        ){
        String userCheck = "admin";
        String passCheck = "123";

        if(userCheck.equals(username) && passCheck.equals(password)){
            return "redirect:/principal";
        }else{
            return "login";
        }
    }
}
