package com.jerrycastro.tienda.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/productos")
    public String mostrarProductos(){
        return "productos";
    }

    @GetMapping("/sistema")
    public String mostrarSistema(){
        return "sistema";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(){
        return "clientes";
    }

    @GetMapping("/detalleventas")
    public String mostrarDetalle(){
        return "detalleventa";
    }

    @GetMapping("/usuarios")
    public String mostrarUsuario(){
        return "usuarios";
    }

    @GetMapping("/ventas")
    public String mostrarVentas(){
        return "ventas";
    }

    @GetMapping("/contacto")
    public String mostrarContacto(){
        return "contactanos";
    }
}
