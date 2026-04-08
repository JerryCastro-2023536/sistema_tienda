package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Usuarios;
import com.jerrycastro.tienda.Exception.NotFoundException;
import com.jerrycastro.tienda.Exception.usuarioValidation;
import com.jerrycastro.tienda.Repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImplements implements UsuariosService{

    private final UsuariosRepository usuariosRepository;
    private final usuarioValidation usuarioValidation;

    public UsuariosServiceImplements(UsuariosRepository usuariosRepository, usuarioValidation validarDpi) {
        this.usuariosRepository = usuariosRepository;
        this.usuarioValidation = validarDpi;
    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuariosRepository.findAll();
    }

    @Override
    public Usuarios getByIdUsuarios(Integer id) {
        return usuariosRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));
    }

    @Override
    public Usuarios saveUsuarios(Usuarios usuarios) {
        usuarioValidation.validarUsername(usuarios.getUsername());
        usuarioValidation.formatoEmail(usuarios.getEmail());
        usuarioValidation.validarEmail(usuarios);
        usuarioValidation.validarEstado(usuarios.getEstado());
        usuarioValidation.validarRol(usuarios.getRol());

        return usuariosRepository.save(usuarios);
    }

    @Override
    public Usuarios updateUsuarios(Integer id, Usuarios usuarios) {
        Usuarios usuarios1 = usuariosRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));

        usuarios1.setUsername(usuarios.getUsername());
        usuarios1.setPassword(usuarios.getPassword());
        usuarios1.setEmail(usuarios.getEmail());
        usuarios1.setRol(usuarios.getRol());
        usuarios1.setEstado(usuarios.getEstado());

        usuarioValidation.formatoEmail(usuarios1.getEmail());
        usuarioValidation.validarEstado(usuarios1.getEstado());
        usuarioValidation.validarRol(usuarios1.getRol());

        return usuariosRepository.save(usuarios1);
    }

    @Override
    public void deleteUsuarios(Integer id) {
        Usuarios usuarios = usuariosRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));
        usuariosRepository.delete(usuarios);
    }
}
