package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.Usuarios;
import com.jerrycastro.tienda.Repository.UsuariosRepository;
import com.jerrycastro.tienda.Service.UsuariosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class LoginController {

    private final UsuariosService usuariosService;
    private final UsuariosRepository usuariosRepository;

    public LoginController(UsuariosService usuariosService, UsuariosRepository usuariosRepository) {
        this.usuariosService = usuariosService;
        this.usuariosRepository = usuariosRepository;
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
                          Model model,
                          HttpSession session) {

        Usuarios u = usuariosService.login(username, password);

        if (u != null) {
            session.setAttribute("usuarioLogueado", u);
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
                          @RequestParam("email") String email,
                          @RequestParam("archivo") MultipartFile archivo,
                          Model model) throws IOException {
        if (archivo.isEmpty()) {
            return "Por favor, selecciona un archivo.";
        }
        Usuarios u = usuariosService.registrar(username, password, email, archivo);
        model.addAttribute("usuarioLogueado", usuariosService.getAllUsuarios());
        if (u == null) {
            model.addAttribute("error", "Usuario ya existe");
            return "register";
        }

        return "redirect:/acceder";
    }

    // LISTA
    @GetMapping("/usuarios")
    public String listarUsuario(Model model) {
        List<Usuarios> lista = usuariosService.getAllUsuarios();
        model.addAttribute("usuarios", lista);
        return "usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") int id) {
        usuariosService.deleteUsuarios(id);
        return "redirect:/usuarios";
    }


}
