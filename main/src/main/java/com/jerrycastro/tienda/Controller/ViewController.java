package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.*;
import com.jerrycastro.tienda.Repository.UsuariosRepository;
import com.jerrycastro.tienda.Service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {

    private final ProductosService productosService;
    private final VentasService ventasService;
    private final DetalleVentaService detalleVentaService;
    private final ClientesService clientesService;
    private final UsuariosService usuariosService;
    private final UsuariosRepository usuariosRepository;

    public ViewController(ProductosService productosService, VentasService ventasService, DetalleVentaService detalleVentaService, ClientesService clientesService, UsuariosService usuariosService, UsuariosService usuariosService1, UsuariosService usuariosRepository, UsuariosRepository usuariosRepository1) {
        this.productosService = productosService;
        this.ventasService = ventasService;
        this.detalleVentaService = detalleVentaService;
        this.clientesService = clientesService;
        this.usuariosService = usuariosService1;
        this.usuariosRepository = usuariosRepository1;
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model){
        List<Productos> lista = productosService.getAllProductos();
        model.addAttribute("productos", lista);
        return "productos";
    }

    @PostMapping("/saveProduct")
    public String guardarProductos(@RequestParam("codigo") String codigo,
                                   @RequestParam("nombre") String nombre,
                                   @RequestParam("precio") String precio,
                                   @RequestParam("stock") String stock,
                                   @RequestParam("estado") String estado){
        int estadoNum;

        Productos p = new Productos();

        p.setCodigo_producto(Integer.parseInt(codigo));
        p.setNombre_producto(nombre);
        p.setPrecio(Double.parseDouble(precio));
        p.setStock(Integer.parseInt(stock));
        if(estado.equals("Activo")){
            estadoNum = 1;
        }else{
            estadoNum = 0;
        }
        p.setEstado(estadoNum);

        productosService.saveProductos(p);
        return "redirect:/productos";
    }

    @GetMapping("/eliminarProduct/{id}")
    public String eliminarProducto(@PathVariable Integer id){
        productosService.deleteProductos(id);
        return "redirect:/productos";
    }

    @GetMapping("/sistema")
    public String mostrarSistema(Model model, HttpSession session ){
        Usuarios usuarioActivo = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuarioActivo == null) {
            return "redirect:/acceder";
        }

        model.addAttribute("usuario", usuarioActivo);
        return "sistema";
    }

    @GetMapping("/clientes")
    public String mostrarClientes(Model model){
        List<Clientes> lista = clientesService.getAllClientes();
        model.addAttribute("clientes", lista);
        return "clientes";
    }

    @GetMapping("/deleteClientes/{id}")
    public String deleteClientes(@PathVariable Integer id){
        clientesService.deleteClientes(id);
        return "redirect:/clientes";
    }

    @GetMapping("/detalleventa")
    public String mostrarDetalle(Model model){
        List<DetalleVenta> lista = detalleVentaService.getAllDetalleVenta();
        model.addAttribute("detalles", lista);
        return "detalleventa";
    }

    @GetMapping("/eliminarDetalle/{id}")
    public String eliminarDetalle(@PathVariable Integer id){
        detalleVentaService.deleteDetalleVenta(id);
        return "redirect:/detalleventa";
    }

    @GetMapping("/ventas")
    public String mostrarVentas(Model model){
        List<Ventas> lista = ventasService.getAllVentas();
        model.addAttribute("ventas", lista);
        return "ventas";
    }

    @GetMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable Integer id){
        ventasService.deleteVentas(id);
        return "redirect:/ventas";
    }

    @GetMapping("/contacto")
    public String mostrarContacto(){
        return "contactanos";
    }

    @GetMapping("/usuarios/foto/{id}")
    public ResponseEntity<byte[]> obtenerFoto(@PathVariable int id) {
        // Usamos el repositorio para traer los datos binarios
        return usuariosRepository.findById(id)
                .map(u -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(u.getFoto()))
                .orElse(ResponseEntity.notFound().build());
    }

}
