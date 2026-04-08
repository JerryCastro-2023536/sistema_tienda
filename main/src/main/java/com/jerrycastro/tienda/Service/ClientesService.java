package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Clientes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientesService {
    List<Clientes> getAllClientes();
    Clientes getByIdClientes(Integer id);
    Clientes saveClientes(Clientes clientes);
    Clientes updateClientes(Integer id, Clientes clientes);
    void deleteClientes(Integer id);
}
