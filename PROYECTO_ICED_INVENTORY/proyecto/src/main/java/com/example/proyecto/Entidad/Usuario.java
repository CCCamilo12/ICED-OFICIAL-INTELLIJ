package com.example.proyecto.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import jakarta.persistence.Entity;

import java.util.Set;


@Entity
@Table(name="Usuario")
public class Usuario {
    @Id
    @Column(length = 11)
    private String Usu_Documento;

    @Column(nullable = false, length = 100)
    private String Usu_Nombre;

    @Column(nullable = false, length = 100)
    private String Usu_Apellido;

    @Column(nullable = false, length = 100)
    private String Usu_Tipo;

    @Column(nullable = false, length = 100)
    private String Usu_Celular;

    @Column(nullable = false, length = 100, unique = true)
    private String Usu_Correo;

    @Column(nullable = false, length = 100)
    private String Usu_Ficha;

    @OneToMany(mappedBy = "Usu_Documento_usurios", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Prestamo> prestamos;

    public Usuario() {
    }
    public Usuario(String usu_Documento, String usu_Nombre, String usu_Apellido, String usu_Tipo, String usu_Celular, String usu_Correo, String usu_Ficha, Set<Prestamo> prestamos) {
        this.Usu_Documento = usu_Documento;
        this.Usu_Nombre = usu_Nombre;
        this.Usu_Apellido = usu_Apellido;
        this.Usu_Tipo = usu_Tipo;
        this.Usu_Celular = usu_Celular;
        this.Usu_Correo = usu_Correo;
        this.Usu_Ficha = usu_Ficha;
        this.prestamos = prestamos;
    }



    public String getUsu_Documento() {
        return Usu_Documento;
    }

    public void setUsu_Documento(String usu_Documento) {
        Usu_Documento = usu_Documento;
    }

    public String getUsu_Nombre() {
        return Usu_Nombre;
    }

    public void setUsu_Nombre(String usu_Nombre) {
        Usu_Nombre = usu_Nombre;
    }

    public String getUsu_Apellido() {
        return Usu_Apellido;
    }

    public void setUsu_Apellido(String usu_Apellido) {
        Usu_Apellido = usu_Apellido;
    }

    public String getUsu_Tipo() {
        return Usu_Tipo;
    }

    public void setUsu_Tipo(String usu_Tipo) {
        Usu_Tipo = usu_Tipo;
    }

    public String getUsu_Celular() {
        return Usu_Celular;
    }

    public void setUsu_Celular(String usu_Celular) {
        Usu_Celular = usu_Celular;
    }

    public String getUsu_Correo() {
        return Usu_Correo;
    }

    public void setUsu_Correo(String usu_Correo) {
        Usu_Correo = usu_Correo;
    }

    public String getUsu_Ficha() {
        return Usu_Ficha;
    }

    public void setUsu_Ficha(String usu_Ficha) {
        Usu_Ficha = usu_Ficha;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Usu_Documento=" + Usu_Documento +
                ", Usu_Nombre='" + Usu_Nombre + '\'' +
                ", Usu_Apellido='" + Usu_Apellido + '\'' +
                ", Usu_Tipo='" + Usu_Tipo + '\'' +
                ", Usu_Celular='" + Usu_Celular + '\'' +
                ", Usu_Correo='" + Usu_Correo + '\'' +
                ", Usu_Ficha='" + Usu_Ficha + '\'' +
                '}';
    }
}
