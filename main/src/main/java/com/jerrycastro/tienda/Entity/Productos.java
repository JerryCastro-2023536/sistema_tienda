package com.jerrycastro.tienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Productos")
public class Productos {
    @Id
    @NotNull(groups = OnCreate.class, message = "El campo no debe ir vacio")
    @Column(name = "codigo_producto")
    private Integer codigo_producto;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "nombre_producto")
    private String nombre_producto;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "precio")
    private Double precio;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "stock")
    private Integer stock;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "estado")
    private Integer estado;

    public Integer getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(Integer codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
