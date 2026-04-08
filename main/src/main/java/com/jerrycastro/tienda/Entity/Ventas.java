package com.jerrycastro.tienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Ventas {
    @Id
    @NotNull(groups = OnCreate.class, message = "El campo no debe ir vacio")
    @Column(name = "codigo_venta")
    private Integer codigo_venta;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "fecha_venta")
    private LocalDate fecha_venta;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "total")
    private Double total;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "estado")
    private Integer estado;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "clientes_dpi_cliente")
    private Integer clientes_dpi_cliente;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "usuarios_codigo_usuario")
    private Integer usuarios_codigo_usuario;

    public Integer getCodigo_venta() {
        return codigo_venta;
    }

    public void setCodigo_venta(Integer codigo_venta) {
        this.codigo_venta = codigo_venta;
    }

    public LocalDate getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getClientes_dpi_cliente() {
        return clientes_dpi_cliente;
    }

    public void setClientes_dpi_cliente(Integer clientes_dpi_cliente) {
        this.clientes_dpi_cliente = clientes_dpi_cliente;
    }

    public Integer getUsuarios_codigo_usuario() {
        return usuarios_codigo_usuario;
    }

    public void setUsuarios_codigo_usuario(Integer usuarios_codigo_usuario) {
        this.usuarios_codigo_usuario = usuarios_codigo_usuario;
    }
}
