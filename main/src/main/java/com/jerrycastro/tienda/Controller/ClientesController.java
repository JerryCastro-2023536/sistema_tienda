package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.Clientes;
import com.jerrycastro.tienda.Entity.OnCreate;
import com.jerrycastro.tienda.Entity.OnUpdate;
import com.jerrycastro.tienda.Service.ClientesService;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {
    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    List<Clientes> getAllClientes(){
        return clientesService.getAllClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdClientes(@PathVariable Integer id){
        try{
            Clientes buscarCliente = clientesService.getByIdClientes(id);
            return new ResponseEntity<>(buscarCliente, HttpStatus.FOUND);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveClientes(@Validated({OnCreate.class, Default.class}) @RequestBody Clientes clientes){
        try{
            Clientes guardarCliente = clientesService.saveClientes(clientes);
            return new ResponseEntity<>(guardarCliente, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClientes(@PathVariable Integer id,@Validated({OnUpdate.class, Default.class}) @RequestBody Clientes clientes){
        try{
            Clientes actualizarCliente = clientesService.updateClientes(id, clientes);
            return new ResponseEntity<>(actualizarCliente, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClientes(@PathVariable Integer id){
        try{
            clientesService.deleteClientes(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
