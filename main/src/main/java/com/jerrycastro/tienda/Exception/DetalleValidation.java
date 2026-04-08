package com.jerrycastro.tienda.Exception;

import com.jerrycastro.tienda.Entity.DetalleVenta;
import com.jerrycastro.tienda.Repository.DetalleVentaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DetalleValidation {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleValidation(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public void validarCodigo(DetalleVenta detalleVenta){
        List<DetalleVenta> detalleVentaList = detalleVentaRepository.findAll();
        for (DetalleVenta dt : detalleVentaList){
            if(detalleVenta.getCodigo_detalle_venta().equals(dt.getCodigo_detalle_venta())){
                throw new PersonaliteException("El codigo ya existe");
            }
        }
    }

    public void validarCantidad(Integer cantidad){
        if(cantidad <= 0){
            throw new PersonaliteException("La cantidad no puede ser menor o igual a 0");
        }
    }

    public void validarPrecio(Double precio){
        if(precio <= 0){
            throw new PersonaliteException("EL precio no puede ser menor o igual a 0");
        }
    }

    public void validarTotal(Double total){
        if(total <= 0){
            throw new PersonaliteException("El total no puede ser menor o igual a 0");
        }
    }
}
