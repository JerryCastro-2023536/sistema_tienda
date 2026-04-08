package com.jerrycastro.tienda.Exception;

import com.jerrycastro.tienda.Entity.Ventas;
import com.jerrycastro.tienda.Repository.VentasRepostory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class VentaValidation {

    private final VentasRepostory ventasRepostory;

    public VentaValidation(VentasRepostory ventasRepostory) {
        this.ventasRepostory = ventasRepostory;
    }

    public void codigoValidar(Ventas ventas){
        List<Ventas> ventasList = ventasRepostory.findAll();
        for(Ventas v : ventasList){
            if(ventas.getCodigo_venta().equals(v.getCodigo_venta())){
                throw new PersonaliteException("El codigo de la vanta ya existe");
            }
        }
    }

    public void fechaValidar(LocalDate fecha){
        if(fecha.isAfter(LocalDate.now())){
            throw new PersonaliteException("La fecha de venta es despues de hoy");
        }
    }

    public void totalValidar(Double total){
        if(total <= 0){
            throw new PersonaliteException("El total es menor o igual a 0");
        }
    }
    public void validarEstado(Integer estado){
        if(estado != 0 && estado != 1){
            throw new PersonaliteException("El estado no es valido");
        }
    }

}
