package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Usuarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuariosService {
    List<Usuarios> getAllUsuarios();
    Usuarios getByIdUsuarios(Integer id);
    Usuarios saveUsuarios(Usuarios usuarios);
    Usuarios updateUsuarios(Integer id, Usuarios usuarios);
    void deleteUsuarios(Integer id);
}
