package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Ventas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VentasService {
    List<Ventas> getAllVentas();
    Ventas getByIdVentas(Integer id);
    Ventas saveVentas(Ventas ventas);
    Ventas updateVentas(Integer id, Ventas ventas);
    void deleteVentas(Integer id);
}
