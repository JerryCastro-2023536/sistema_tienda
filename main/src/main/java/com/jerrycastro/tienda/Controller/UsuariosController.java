package com.jerrycastro.tienda.Controller;

import com.jerrycastro.tienda.Entity.Usuarios;
import com.jerrycastro.tienda.Service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @GetMapping
    List<Usuarios> getAllClientes(){
        return usuariosService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByIdUsuarios(@PathVariable Integer id){
        try{
            Usuarios buscarUsuario = usuariosService.getByIdUsuarios(id);
            return new ResponseEntity<>(buscarUsuario, HttpStatus.FOUND);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> saveUsuarios(@Valid @RequestBody Usuarios usuarios){
        try{
            Usuarios guardarUsuario = usuariosService.saveUsuarios(usuarios);
            return new ResponseEntity<>(guardarUsuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuarios(@PathVariable Integer id, @Valid @RequestBody Usuarios usuarios){
        try{
            Usuarios actualizarUsario = usuariosService.updateUsuarios(id, usuarios);
            return new ResponseEntity<>(actualizarUsario, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUsuarios(@PathVariable Integer id){
        try{
            usuariosService.deleteUsuarios(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
