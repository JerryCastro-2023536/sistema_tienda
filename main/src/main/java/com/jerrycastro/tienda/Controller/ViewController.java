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

import java.time.LocalDate;
import java.util.List;

@Controller
public class ViewController {

    private final ProductosService productosService;
    private final VentasService ventasService;
    private final DetalleVentaService detalleVentaService;
    private final ClientesService clientesService;
    private final UsuariosService usuariosService;
    private final UsuariosRepository usuariosRepository;

    public ViewController(ProductosService productosService, VentasService ventasService, DetalleVentaService detalleVentaService, ClientesService clientesService, UsuariosService usuariosService1, UsuariosRepository usuariosRepository1) {
        this.productosService = productosService;
        this.ventasService = ventasService;
        this.detalleVentaService = detalleVentaService;
        this.clientesService = clientesService;
        this.usuariosService = usuariosService1;
        this.usuariosRepository = usuariosRepository1;
    }

    // ---------------------CRUD PRODUCTOS ---------------------------
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

    @PostMapping("/updateProduct")
    public String updateProductos(@RequestParam("codigo") String codigo,
                                   @RequestParam("nombre") String nombre,
                                   @RequestParam("precio") String precio,
                                   @RequestParam("stock") String stock,
                                   @RequestParam("estado") String estado){
        int estadoNum;
        Productos p = new Productos();

        p.setNombre_producto(nombre);
        p.setPrecio(Double.parseDouble(precio));
        p.setStock(Integer.parseInt(stock));
        if(estado.equals("Activo")){
            estadoNum = 1;
        }else{
            estadoNum = 0;
        }
        p.setEstado(estadoNum);

        productosService.updateProductos(Integer.parseInt(codigo), p);
        return "redirect:/productos";
    }


    @PostMapping("/buscarProduct")
    public String buscarProducto(@RequestParam("id") Integer id, Model model){
        Productos p = productosService.getByIdProductos(id);
        model.addAttribute("productos", List.of(p));
        return "productos";
    }

    @GetMapping("/eliminarProduct/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id){
        productosService.deleteProductos(id);
        return "redirect:/productos";
    }

    // -------- CONTROLADOR VISTA DEL SISTEMA ----------
    @GetMapping("/sistema")
    public String mostrarSistema(Model model, HttpSession session ){
        Usuarios usuarioActivo = (Usuarios) session.getAttribute("usuarioLogueado");

        if (usuarioActivo == null) {
            return "redirect:/acceder";
        }

        model.addAttribute("usuario", usuarioActivo);
        return "sistema";
    }

    // ------------- CRUD DE CLIENTES -----------
    @GetMapping("/clientes")
    public String mostrarClientes(Model model){
        List<Clientes> lista = clientesService.getAllClientes();
        model.addAttribute("clientes", lista);
        return "clientes";
    }

    @PostMapping("/saveCliente")
    public String saveCliente(@RequestParam("dpi") String dpi,
                              @RequestParam("nombre") String nombre,
                              @RequestParam("apellido") String apellido,
                              @RequestParam("direccion") String direccion,
                              @RequestParam("estado") String estado){
        Clientes c = new Clientes();

        c.setDpi_cliente(Integer.parseInt(dpi));
        c.setNombre_cliente(nombre);
        c.setApellido_cliente(apellido);
        c.setDireccion(direccion);
        c.setEstado(estado.equals("Activo") ? 1 : 0);

        clientesService.saveClientes(c);
        return "redirect:/clientes";
    }

    @PostMapping("/updateCliente")
    public String updateCliente(@RequestParam("dpi") String dpi_cliente,
                              @RequestParam("nombre") String nombre_cliente,
                              @RequestParam("apellido") String apellido_cliente,
                              @RequestParam("direccion") String direccion,
                              @RequestParam("estado") String estado){
        Clientes c = new Clientes();

        c.setNombre_cliente(nombre_cliente);
        c.setApellido_cliente(apellido_cliente);
        c.setDireccion(direccion);
        c.setEstado(estado.equals("Activo") ? 1 : 0);

        clientesService.updateClientes(Integer.parseInt(dpi_cliente), c);
        return "redirect:/clientes";
    }

    @PostMapping("/buscarCliente")
    public String buscarCliente(@RequestParam("id") Integer id, Model model){
        Clientes c = clientesService.getByIdClientes(id);
        model.addAttribute("clientes", List.of(c));
        return "clientes";
    }

    @GetMapping("/deleteClientes/{id}")
    public String deleteClientes(@PathVariable("id") Integer id){
        clientesService.deleteClientes(id);
        return "redirect:/clientes";
    }

    // ------------ CRUD DETALLE VENTA -------------
    @GetMapping("/detalleventa")
    public String mostrarDetalle(Model model){
        List<DetalleVenta> lista = detalleVentaService.getAllDetalleVenta();
        model.addAttribute("detalles", lista);
        return "detalleventa";
    }

    @PostMapping("/saveDetalle")
    public String saveDetalle(@RequestParam("codigo") String codigo,
                              @RequestParam("cantidad") String cantidad,
                              @RequestParam("precio") String precio,
                              @RequestParam("subtotal") String subtotal,
                              @RequestParam("productosCod") String productosCod,
                              @RequestParam("ventasCod") String ventasCod){

        DetalleVenta d = new DetalleVenta();

        d.setCodigo_detalle_venta(Integer.parseInt(codigo));
        d.setCantidad(Integer.parseInt(cantidad));
        d.setPrecio_unitario(Double.parseDouble(precio));
        d.setSubtotal(Double.parseDouble(subtotal));
        d.setProductos_codigo_producto(Integer.parseInt(productosCod));
        d.setVentas_codigo_venta(Integer.parseInt(ventasCod));

        detalleVentaService.saveDetalleVenta(d);
        return "redirect:/detalleventa";
    }

    @PostMapping("/updateDetalle")
    public String updateDetalle(@RequestParam("codigo") String codigo,
                                @RequestParam("cantidad") String cantidad,
                                @RequestParam("precio") String precio,
                                @RequestParam("subtotal") String subtotal,
                                @RequestParam("productosCod") String productosCod,
                                @RequestParam("ventasCod") String ventasCod){

        DetalleVenta d = new DetalleVenta();

        d.setCantidad(Integer.parseInt(cantidad));
        d.setPrecio_unitario(Double.parseDouble(precio));
        d.setSubtotal(Double.parseDouble(subtotal));
        d.setProductos_codigo_producto(Integer.parseInt(productosCod));
        d.setVentas_codigo_venta(Integer.parseInt(ventasCod));

        detalleVentaService.updateDetalleVenta(Integer.parseInt(codigo), d);
        return "redirect:/detalleventa";
    }

    @PostMapping("/buscarDetalle")
    public String buscarDetalle(@RequestParam("id") Integer id, Model model){
        DetalleVenta d = detalleVentaService.getByIdDetalleVenta(id);
        model.addAttribute("detalles", List.of(d));
        return "detalleventa";
    }

    @GetMapping("/eliminarDetalle/{id}")
    public String eliminarDetalle(@PathVariable("id") Integer id){
        detalleVentaService.deleteDetalleVenta(id);
        return "redirect:/detalleventa";
    }

    // -------- CRUD VENTAS ---------
    @GetMapping("/ventas")
    public String mostrarVentas(Model model){
        List<Ventas> lista = ventasService.getAllVentas();
        model.addAttribute("ventas", lista);
        return "ventas";
    }

    @PostMapping("/saveVenta")
    public String saveVenta(@RequestParam("codigo") String codigo,
                            @RequestParam("fecha") LocalDate fecha,
                            @RequestParam("total") String total,
                            @RequestParam("estado") String estado,
                            @RequestParam("cliente") String cliente,
                            @RequestParam("usuario") String usuario){

        Ventas v = new Ventas();

        v.setCodigo_venta(Integer.parseInt(codigo));
        v.setFecha_venta(fecha);
        v.setTotal(Double.parseDouble(total));
        v.setEstado(estado.equals("Activo") ? 1 : 0);
        v.setClientes_dpi_cliente(Integer.parseInt(cliente));
        v.setUsuarios_codigo_usuario(Integer.parseInt(usuario));

        ventasService.saveVentas(v);
        return "redirect:/ventas";
    }

    @PostMapping("/updateVenta")
    public String updateVenta(@RequestParam("codigo") String codigo,
                              @RequestParam("fecha") LocalDate fecha,
                              @RequestParam("total") String total,
                              @RequestParam("estado") String estado,
                              @RequestParam("cliente") String cliente,
                              @RequestParam("usuario") String usuario){

        Ventas v = new Ventas();

        v.setFecha_venta(fecha);
        v.setTotal(Double.parseDouble(total));
        v.setEstado(estado.equals("Activo") ? 1 : 0);
        v.setClientes_dpi_cliente(Integer.parseInt(cliente));
        v.setUsuarios_codigo_usuario(Integer.parseInt(usuario));

        ventasService.updateVentas(Integer.parseInt(codigo), v);
        return "redirect:/ventas";
    }

    @PostMapping("/buscarVenta")
    public String buscarVenta(@RequestParam("id") Integer id, Model model){
        Ventas v = ventasService.getByIdVentas(id);
        model.addAttribute("ventas", List.of(v));
        return "ventas";
    }

    @GetMapping("/eliminarVenta/{id}")
    public String eliminarVenta(@PathVariable("id") Integer id){
        ventasService.deleteVentas(id);
        return "redirect:/ventas";
    }

    // -------CONTROLADOR DE LA VISTA DE CONTACTO
    @GetMapping("/contacto")
    public String mostrarContacto(){
        return "contactanos";
    }

    // --------- CONTROLADOR PARA LA FOTO DEL USUARIO ----------
    @GetMapping("/usuarios/foto/{id}")
    public ResponseEntity<byte[]> obtenerFoto(@PathVariable("id") int id) {
        return usuariosRepository.findById(id)
                .map(u -> ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(u.getFoto()))
                .orElse(ResponseEntity.notFound().build());
    }

}
