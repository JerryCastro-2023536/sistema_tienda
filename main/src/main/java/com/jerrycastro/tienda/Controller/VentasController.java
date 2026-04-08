package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.OnCreate;
import com.jerrycastro.tienda.Entity.OnUpdate;
import com.jerrycastro.tienda.Entity.Ventas;
import com.jerrycastro.tienda.Service.VentasService;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private final VentasService ventasService;

    public VentasController(VentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    List<Ventas> getAllVentas(){
        return ventasService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdVentas(@PathVariable Integer id){
        try{
            Ventas buscarVenta = ventasService.getByIdVentas(id);
            return new ResponseEntity<>(buscarVenta, HttpStatus.FOUND);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveVentas(@Validated({OnCreate.class, Default.class}) @RequestBody Ventas ventas){
        try{
            Ventas guardarVenta = ventasService.saveVentas(ventas);
            return new ResponseEntity<>(guardarVenta, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVentas(@PathVariable Integer id, @Validated({OnUpdate.class, Default.class}) @RequestBody Ventas ventas){
        try{
            Ventas actualizarVenta = ventasService.updateVentas(id, ventas);
            return new ResponseEntity<>(actualizarVenta, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVentas(@PathVariable Integer id){
        try{
            ventasService.deleteVentas(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
