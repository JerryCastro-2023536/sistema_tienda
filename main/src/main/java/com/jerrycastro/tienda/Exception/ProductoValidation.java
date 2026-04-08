package com.jerrycastro.tienda.Exception;

import com.jerrycastro.tienda.Entity.Productos;
import com.jerrycastro.tienda.Repository.ProductosRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductoValidation {

    private final ProductosRepository productosRepository;

    public ProductoValidation(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    public void codigoValidar(Productos productos){
        List<Productos> productosList = productosRepository.findAll();
        for(Productos p : productosList){
            if(productos.getCodigo_producto().equals(p.getCodigo_producto())){
                throw new PersonaliteException("El codigo del producto ya existe");
            }
        }
    }

    public void precioValidar(Double precio){
        if(precio <= 0){
            throw new PersonaliteException("El precio no es menor o igual a 0");
        }
    }

    public void stockValidar(Integer stock){
        if(stock <= 0){
            throw new PersonaliteException("El stock no puede ser negativo o igual a 0");
        }
    }

    public void estadoValidar(Integer estado){
        if(estado != 0 && estado !=1){
            throw new PersonaliteException("El estado no es valido");
        }
    }
}
