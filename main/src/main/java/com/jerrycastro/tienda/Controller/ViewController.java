package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.Productos;
import com.jerrycastro.tienda.Entity.Ventas;
import com.jerrycastro.tienda.Service.ProductosService;
import com.jerrycastro.tienda.Service.VentasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private final ProductosService productosService;
    private final VentasService ventasService;

    public ViewController(ProductosService productosService, VentasService ventasService) {
        this.productosService = productosService;
        this.ventasService = ventasService;
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model){
        List<Productos> lista = productosService.getAllProductos();
        model.addAttribute("productos", lista);
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



    @GetMapping("/ventas")
    public String mostrarVentas(Model model){
        List<Ventas> lista = ventasService.getAllVentas();
        model.addAttribute("ventas", lista);
        return "ventas";
    }

    @GetMapping("/contacto")
    public String mostrarContacto(){
        return "contactanos";
    }
}
