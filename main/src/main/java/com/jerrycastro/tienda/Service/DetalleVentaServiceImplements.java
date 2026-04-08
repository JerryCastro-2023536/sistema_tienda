package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.DetalleVenta;
import com.jerrycastro.tienda.Exception.DetalleValidation;
import com.jerrycastro.tienda.Exception.NotFoundException;
import com.jerrycastro.tienda.Repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaServiceImplements implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleValidation detalleValidation;

    public DetalleVentaServiceImplements(DetalleVentaRepository detalleVentaRepository, DetalleValidation detalleValidation) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.detalleValidation = detalleValidation;
    }

    @Override
    public List<DetalleVenta> getAllDetalleVenta() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta getByIdDetalleVenta(Integer id) {
        return detalleVentaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El codigo buscado no existe"));
    }

    @Override
    public DetalleVenta saveDetalleVenta(DetalleVenta detalleVenta) {
        detalleValidation.validarCodigo(detalleVenta);
        detalleValidation.validarCantidad(detalleVenta.getCantidad());
        detalleValidation.validarPrecio(detalleVenta.getPrecio_unitario());
        detalleValidation.validarTotal(detalleVenta.getSubtotal());
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta updateDetalleVenta(Integer id, DetalleVenta detalleVenta) {
        DetalleVenta detalleVenta1 = detalleVentaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));

        detalleVenta1.setCantidad(detalleVenta.getCantidad());
        detalleVenta1.setPrecio_unitario(detalleVenta.getPrecio_unitario());
        detalleVenta1.setSubtotal(detalleVenta.getSubtotal());
        detalleVenta1.setProductos_codigo_producto(detalleVenta.getProductos_codigo_producto());
        detalleVenta1.setVentas_codigo_venta(detalleVenta.getVentas_codigo_venta());

        detalleValidation.validarCantidad(detalleVenta1.getCantidad());
        detalleValidation.validarPrecio(detalleVenta1.getPrecio_unitario());
        detalleValidation.validarTotal(detalleVenta1.getSubtotal());

        return detalleVentaRepository.save(detalleVenta1);
    }

    @Override
    public void deleteDetalleVenta(Integer id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));
        detalleVentaRepository.delete(detalleVenta);
    }
}
