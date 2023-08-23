package com.example.proyecto.Entidad;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;


import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name="Sanciones")
public class Sancion {
    @Id
    @Column(nullable = false, length = 11)
    private String San_Pres_Id;

    @Column(nullable = false)
    private LocalDate San_Fecha;

    @Column(nullable = false)
    private Time San_Hora;

    @Column(nullable = false, length = 11)
    private int San_tiempo;

    @Column(nullable = false, length = 1000)
    private String San_Descripcion;

    @OneToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "pres_id")
    private Prestamo prestamo;


    public Sancion() {
    }

    public Sancion(String san_Pres_Id, LocalDate san_Fecha, Time san_Hora, int san_tiempo, String san_Descripcion, Prestamo prestamo) {
        San_Pres_Id = san_Pres_Id;
        San_Fecha = san_Fecha;
        San_Hora = san_Hora;
        San_tiempo = san_tiempo;
        San_Descripcion = san_Descripcion;
        this.prestamo = prestamo;
    }

    public String getSan_Pres_Id() {
        return San_Pres_Id;
    }

    public void setSan_Pres_Id(String san_Pres_Id) {
        San_Pres_Id = san_Pres_Id;
    }

    public LocalDate getSan_Fecha() {
        return San_Fecha;
    }

    public void setSan_Fecha(LocalDate san_Fecha) {
        San_Fecha = san_Fecha;
    }

    public Time getSan_Hora() {
        return San_Hora;
    }

    public void setSan_Hora(Time san_Hora) {
        San_Hora = san_Hora;
    }

    public int getSan_tiempo() {
        return San_tiempo;
    }

    public void setSan_tiempo(int san_tiempo) {
        San_tiempo = san_tiempo;
    }

    public String getSan_Descripcion() {
        return San_Descripcion;
    }

    public void setSan_Descripcion(String san_Descripcion) {
        San_Descripcion = san_Descripcion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "Sancion{" +
                "San_Pres_Id='" + San_Pres_Id + '\'' +
                ", San_Fecha=" + San_Fecha +
                ", San_Hora=" + San_Hora +
                ", San_tiempo=" + San_tiempo +
                ", San_Descripcion='" + San_Descripcion + '\'' +
                ", prestamo=" + prestamo +
                '}';
    }
}

