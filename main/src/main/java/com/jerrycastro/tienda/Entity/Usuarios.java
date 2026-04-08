package com.jerrycastro.tienda.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Integer codigo_usuario;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "password")
    private String password;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "El campo no debe ir vacio")
    @Column(name = "rol")
    private String rol;

    @NotNull(message = "El campo no debe ir vacio")
    @Column(name = "estado")
    private Integer estado;

    public Integer getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(Integer codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
