package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.DetalleVenta;
import com.jerrycastro.tienda.Entity.OnCreate;
import com.jerrycastro.tienda.Entity.OnUpdate;
import com.jerrycastro.tienda.Service.DetalleVentaService;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleventa")
public class DetalleVentaController {

    private final DetalleVentaService detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    List<DetalleVenta> getAllDetalleVenta(){
        return detalleVentaService.getAllDetalleVenta();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdDetalleVenta(@PathVariable Integer id){
        try{
            DetalleVenta buscarDetalleVenta = detalleVentaService.getByIdDetalleVenta(id);
            return new ResponseEntity<>(buscarDetalleVenta, HttpStatus.FOUND);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveDetalleVenta(@Validated({OnCreate.class, Default.class}) @RequestBody DetalleVenta detalleVenta){
        try{
            DetalleVenta guardarDetalleVenta = detalleVentaService.saveDetalleVenta(detalleVenta);
            return new ResponseEntity<>(guardarDetalleVenta, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDetalleVenta(@PathVariable Integer id, @Validated({OnUpdate.class, Default.class}) @RequestBody DetalleVenta detalleVenta){
        try{
            DetalleVenta actualizarDetalleVenta = detalleVentaService.updateDetalleVenta(id , detalleVenta);
            return new ResponseEntity<>(actualizarDetalleVenta, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDetalleVenta(@PathVariable Integer id){
        try{
            detalleVentaService.deleteDetalleVenta(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
