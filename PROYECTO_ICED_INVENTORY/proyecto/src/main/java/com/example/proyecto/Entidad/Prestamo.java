package com.example.proyecto.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.sql.Time;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.Date;


@Entity
@Table(name = "Prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int presId;
    @Column(name = "Pres_Fec_Entrega",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date Pres_Fec_Entrega;

    @Column(name = "Pres_Hora_Entrega",nullable = false)
    private Time Pres_Hora_Entrega;

    @Column(name = "Pres_Tiempo_Limite",nullable = false)
    private int Pres_Tiempo_Limite;

    @Column(name = "Pres_Observaciones_Entrega",nullable = false)
    private String Pres_Observaciones_Entrega;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Equ_id", referencedColumnName = "Equ_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    public Equipo Equ_id_equipos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Usu_Documento", referencedColumnName = "Usu_Documento", nullable = false)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    public Usuario Usu_Documento_usurios;


    @PrePersist
    public void fechaActual() {

        this.Pres_Fec_Entrega = new Date();
    }


    public Prestamo(int presId, Date pres_Fec_Entrega, Time pres_Hora_Entrega, int pres_Tiempo_Limite, String pres_Observaciones_Entrega, Equipo equ_id_equipos, Usuario usu_Documento_usurios) {
        this.presId = presId;
        Pres_Fec_Entrega = pres_Fec_Entrega;
        Pres_Hora_Entrega = pres_Hora_Entrega;
        Pres_Tiempo_Limite = pres_Tiempo_Limite;
        Pres_Observaciones_Entrega = pres_Observaciones_Entrega;
        Equ_id_equipos = equ_id_equipos;
        Usu_Documento_usurios = usu_Documento_usurios;
    }

    public Prestamo() {
    }


    public int getPresId() {
        return presId;
    }

    public void setPresId(int presId) {
        this.presId = presId;
    }

    public Date getPres_Fec_Entrega() {
        return Pres_Fec_Entrega;
    }

    public void setPres_Fec_Entrega(Date pres_Fec_Entrega) {
        Pres_Fec_Entrega = pres_Fec_Entrega;
    }

    public Time getPres_Hora_Entrega() {
        return Pres_Hora_Entrega;
    }

    public void setPres_Hora_Entrega(Time pres_Hora_Entrega) {
        Pres_Hora_Entrega = pres_Hora_Entrega;
    }

    public int getPres_Tiempo_Limite() {
        return Pres_Tiempo_Limite;
    }

    public void setPres_Tiempo_Limite(int pres_Tiempo_Limite) {
        Pres_Tiempo_Limite = pres_Tiempo_Limite;
    }

    public String getPres_Observaciones_Entrega() {
        return Pres_Observaciones_Entrega;
    }

    public void setPres_Observaciones_Entrega(String pres_Observaciones_Entrega) {
        Pres_Observaciones_Entrega = pres_Observaciones_Entrega;
    }

    public Equipo getEqu_id_equipos() {
        return Equ_id_equipos;
    }

    public void setEqu_id_equipos(Equipo equ_id_equipos) {
        Equ_id_equipos = equ_id_equipos;
    }

    public Usuario getUsu_Documento_usurios() {
        return Usu_Documento_usurios;
    }

    public void setUsu_Documento_usurios(Usuario usu_Documento_usurios) {
        Usu_Documento_usurios = usu_Documento_usurios;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "presId=" + presId +
                ", Pres_Fec_Entrega=" + Pres_Fec_Entrega +
                ", Pres_Hora_Entrega=" + Pres_Hora_Entrega +
                ", Pres_Tiempo_Limite=" + Pres_Tiempo_Limite +
                ", Pres_Observaciones_Entrega='" + Pres_Observaciones_Entrega + '\'' +
                ", equipo=" + Equ_id_equipos.getEqu_id() +
                ", usuario=" + Usu_Documento_usurios.getUsu_Documento() +
                '}';
    }

}
