package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.Productos;
import com.jerrycastro.tienda.Entity.Usuarios;
import com.jerrycastro.tienda.Service.ProductosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PrincipalController {

    private final ProductosService productosService;

    public PrincipalController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping("/principal")
    public String mostrarPrincipal(Model model, HttpSession session){
        List<Productos> lista = productosService.getAllProductos();
        model.addAttribute("productos", lista);

        Usuarios usuarioActivo = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuarioActivo == null) {
            return "redirect:/acceder";
        }

        model.addAttribute("usuario", usuarioActivo);
        return "principal";
    }

    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model, HttpSession session) {
        Usuarios usuarioActivo = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuarioActivo == null) {
            return "redirect:/acceder";
        }

        model.addAttribute("usuario", usuarioActivo);

        model.addAttribute("productos", productosService.getAllProductos());
        return "catalogo";
    }
}
