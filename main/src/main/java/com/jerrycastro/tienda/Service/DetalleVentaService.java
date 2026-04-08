package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.DetalleVenta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetalleVentaService {
    List<DetalleVenta> getAllDetalleVenta();
    DetalleVenta getByIdDetalleVenta(Integer id);
    DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta);
    DetalleVenta updateDetalleVenta(Integer id, DetalleVenta detalleVenta);
    void deleteDetalleVenta(Integer id);
}
