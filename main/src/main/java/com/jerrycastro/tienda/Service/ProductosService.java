package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Productos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductosService {
    List<Productos> getAllProductos();
    Productos getByIdProductos(Integer id);
    Productos saveProductos(Productos productos);
    Productos updateProductos(Integer id, Productos productos);
    void deleteProductos(Integer id);
}
