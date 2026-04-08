package com.jerrycastro.tienda.Exception;

import com.jerrycastro.tienda.Entity.Clientes;
import com.jerrycastro.tienda.Repository.ClientesRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteValidation {

    private final ClientesRepository clientesRepository;

    public ClienteValidation(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    public void validarDpi(Clientes clientes){
        List<Clientes> clientesList = clientesRepository.findAll();
        for(Clientes c : clientesList){
            if(clientes.getDpi_cliente().equals(c.getDpi_cliente())){
                throw new PersonaliteException("El dpi ya pertenece a un cliente");
            }
        }
    }

    public void validarEstado(Integer estado){
        if(estado != 1 && estado != 0){
            throw new PersonaliteException("El estado no es valido");
        }
    }
}
