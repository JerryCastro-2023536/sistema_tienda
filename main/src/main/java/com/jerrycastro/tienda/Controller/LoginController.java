package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.Usuarios;
import com.jerrycastro.tienda.Service.UsuariosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    private final UsuariosService usuariosService;

    public LoginController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping("/")
    public String inicio(){
        return "redirect:/acceder";
    }

    @GetMapping("/acceder")
    public String mostrarLogin(){
        return "login";
    }


    @PostMapping("/login")
    public String validar(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model) {

        Usuarios u = usuariosService.login(username, password);

        if (u != null) {
            return "redirect:/sistema";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    // REGISTRO
    @GetMapping("/register")
    public String registro() {
        return "register";
    }

    @PostMapping("/registro")
    public String guardar(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          Model model) {

        Usuarios u = usuariosService.registrar(username, password);

        if (u == null) {
            model.addAttribute("error", "Usuario ya existe");
            return "register";
        }

        return "redirect:/acceder";
    }

    // LISTA
    @GetMapping("/usuarios")
    public String listar(Model model) {
        List<Usuarios> lista = usuariosService.getAllUsuarios();
        model.addAttribute("usuarios", lista);
        return "usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        usuariosService.deleteUsuarios(id);
        return "redirect:/lista";
    }

}
