package com.jerrycastro.tienda.Exception;

import com.jerrycastro.tienda.Entity.Usuarios;
import com.jerrycastro.tienda.Repository.UsuariosRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class usuarioValidation {

    private final UsuariosRepository usuariosRepository;

    public usuarioValidation(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    public void validarUsername(String username){
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        for(Usuarios u : usuariosList){
            if(username.equals(u.getUsername())){
                throw new PersonaliteException("El nombre de usuario ya existe");
            }
        }
    }

    public void validarEstado(Integer estado){
        if(estado != 0 && estado != 1){
            throw new PersonaliteException("El estado no es valido");
        }
    }

    public void validarEmail(Usuarios usuarios){
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        for(Usuarios u : usuariosList){
            if(usuarios.getEmail().equals(u.getEmail())){
                throw new PersonaliteException("El email ya existe");
            }
        }
    }

    public void validarRol(String rol){
        if(!(rol.equals("USER") || rol.equals("ADMIN"))){
            throw new PersonaliteException("Rol no valido");
        }
    }

    public void formatoEmail(String email){
        if(!(email.contains("@gmail.com") || email.contains("@yahoo.com") || email.contains("@outlook.com"))){
            throw new PersonaliteException("El email no tiene el formato correcto");
        }
    }
}
