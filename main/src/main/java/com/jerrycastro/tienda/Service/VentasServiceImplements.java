package com.jerrycastro.tienda.Service;

import com.jerrycastro.tienda.Entity.Ventas;
import com.jerrycastro.tienda.Exception.NotFoundException;
import com.jerrycastro.tienda.Exception.VentaValidation;
import com.jerrycastro.tienda.Repository.VentasRepostory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentasServiceImplements implements VentasService{

    private final VentasRepostory ventasRepostory;
    private final VentaValidation ventaValidation;

    public VentasServiceImplements(VentasRepostory ventasRepostory, VentaValidation ventaValidation) {
        this.ventasRepostory = ventasRepostory;
        this.ventaValidation = ventaValidation;
    }

    @Override
    public List<Ventas> getAllVentas() {
        return ventasRepostory.findAll();
    }

    @Override
    public Ventas getByIdVentas(Integer id) {
        return ventasRepostory.findById(id).orElseThrow(() ->
                new NotFoundException("El id buscado no existe"));
    }

    @Override
    public Ventas saveVentas(Ventas ventas) {
        ventaValidation.codigoValidar(ventas);
        ventaValidation.totalValidar(ventas.getTotal());
        ventaValidation.fechaValidar(ventas.getFecha_venta());
        ventaValidation.validarEstado(ventas.getEstado());
        return ventasRepostory.save(ventas);
    }

    @Override
    public Ventas updateVentas(Integer id, Ventas ventas) {
        Ventas ventas1 = ventasRepostory.findById(id).orElseThrow(() ->
                new NotFoundException("El codigo buscado no existe"));

        ventas1.setFecha_venta(ventas.getFecha_venta());
        ventas1.setTotal(ventas.getTotal());
        ventas1.setEstado(ventas.getEstado());
        ventas1.setClientes_dpi_cliente(ventas.getClientes_dpi_cliente());
        ventas1.setUsuarios_codigo_usuario(ventas.getUsuarios_codigo_usuario());

        ventaValidation.totalValidar(ventas1.getTotal());
        ventaValidation.fechaValidar(ventas1.getFecha_venta());
        ventaValidation.validarEstado(ventas1.getEstado());

        return ventasRepostory.save(ventas1);
    }

    @Override
    public void deleteVentas(Integer id) {
        Ventas ventas = ventasRepostory.findById(id).orElseThrow(() ->
                new NotFoundException("El codigo buscado no existe"));
        ventasRepostory.delete(ventas);
    }
}
