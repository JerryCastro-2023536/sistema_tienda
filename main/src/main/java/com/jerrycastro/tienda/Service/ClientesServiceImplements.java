package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Clientes;
import com.jerrycastro.tienda.Exception.ClienteValidation;
import com.jerrycastro.tienda.Exception.NotFoundException;
import com.jerrycastro.tienda.Repository.ClientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesServiceImplements implements ClientesService {

    private final ClientesRepository clientesRepository;
    private final ClienteValidation clienteValidation;

    public ClientesServiceImplements(ClientesRepository clientesRepository, ClienteValidation clienteValidation) {
        this.clientesRepository = clientesRepository;
        this.clienteValidation = clienteValidation;
    }

    @Override
    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes getByIdClientes(Integer id) {
        return clientesRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));
    }

    @Override
    public Clientes saveClientes(Clientes clientes) {
        clienteValidation.validarDpi(clientes);
        clienteValidation.validarEstado(clientes.getEstado());
        return clientesRepository.save(clientes);
    }

    @Override
    public Clientes updateClientes(Integer id, Clientes clientes) {
        Clientes clientes1 = clientesRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));

        clientes1.setNombre_cliente(clientes.getNombre_cliente());
        clientes1.setApellido_cliente(clientes.getApellido_cliente());
        clientes1.setDireccion(clientes.getDireccion());
        clientes1.setEstado(clientes.getEstado());

        clienteValidation.validarEstado(clientes.getEstado());

        return clientesRepository.save(clientes1);
    }

    @Override
    public void deleteClientes(Integer id) {
        Clientes clientes = clientesRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));
        clientesRepository.delete(clientes);
    }
}
