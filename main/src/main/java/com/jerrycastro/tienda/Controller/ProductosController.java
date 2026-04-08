package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.OnCreate;
import com.jerrycastro.tienda.Entity.OnUpdate;
import com.jerrycastro.tienda.Entity.Productos;
import com.jerrycastro.tienda.Service.ProductosService;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    private final ProductosService productosService;

    public ProductosController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    List<Productos> getAllProductos(){
        return productosService.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdProductos(@PathVariable Integer id){
        try{
            Productos buscarProducto = productosService.getByIdProductos(id);
            return new ResponseEntity<>(buscarProducto, HttpStatus.FOUND);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveProductos(@Validated({OnCreate.class, Default.class}) @RequestBody Productos productos){
        try{
            Productos guardarProducto = productosService.saveProductos(productos);
            return new ResponseEntity<>(guardarProducto, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProductos(@PathVariable Integer id, @Validated({OnUpdate.class, Default.class}) @RequestBody Productos productos){
        try{
            Productos actualizarProducto = productosService.updateProductos(id, productos);
            return new ResponseEntity<>(actualizarProducto, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductos(@PathVariable Integer id){
        try{
            productosService.deleteProductos(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
