package com.example.proyecto.Servicios;

import com.example.proyecto.Entidad.*;
import com.example.proyecto.Repositorio.RepositorioEquipos;
import com.example.proyecto.Repositorio.RepositorioPrestamos;
import com.example.proyecto.Repositorio.RepositorioUsuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioPrestamos {
    private RepositorioPrestamos repositorio;
    private RepositorioEquipos reposiEqui;
    private RepositorioUsuarios reposiUsu;

    public ServicioPrestamos(RepositorioPrestamos repositorio, RepositorioEquipos reposiEqui, RepositorioUsuarios reposiUsu) {
        this.repositorio = repositorio;
        this.reposiEqui = reposiEqui;
        this.reposiUsu = reposiUsu;
    }

    public List<Prestamo> MostrarTodosPrestamos(){
        return repositorio.findAll();
    }

    public ResponseEntity<?> buscarPrestamo(int presId) {
        Optional<Prestamo> prestamoOptional = repositorio.findById(String.valueOf(presId));
        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            return ResponseEntity.ok(prestamo);
        } else {
            String mensaje = "El préstamo con ID " + presId + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }





    public String actualizarPrestamo(String Eq, String Us, Prestamo prestamo){
        if (reposiEqui.findById(Eq).isPresent() && reposiUsu.findById(Us).isPresent()){
            Equipo eq = reposiEqui.findById(Eq).get();
            Usuario us = reposiUsu.findById(Us).get();
            prestamo.setEqu_id_equipos(eq);
            prestamo.setUsu_Documento_usurios(us);
            repositorio.save(prestamo);
            if (repositorio.findById(String.valueOf(prestamo.getPresId())).isPresent()){
                repositorio.save(prestamo);
                return "Actualizado exitosamente";
            }
            else{

                return "El prestamo no existe";
            }
        }
        else{

            return "Equipo o Usuario  mal ingresado";
        }
    }

    public String insertarPrestamo(String Eq, String Us, Prestamo prestamo) {
        if (reposiEqui.findById(Eq).isPresent() && reposiUsu.findById(Us).isPresent()){
            Equipo eq = reposiEqui.findById(Eq).get();
            Usuario us = reposiUsu.findById(Us).get();
            prestamo.setEqu_id_equipos(eq);
            prestamo.setUsu_Documento_usurios(us);
            repositorio.save(prestamo);
            return "Registro Exitoso";
        }
        else {
            return "Cliente, Producto o Usuario mal ingresado";
        }

    }


    public String eliminarPrestamo(int presId) {
        if (repositorio.findById(String.valueOf(presId)).isPresent()) {
            repositorio.deleteById(String.valueOf(presId));
            return "El Dispositivo con el codigo " + presId + " fue eliminado exitosamente";
        } else {
            return "No se encontró ningún Dispositivo con el codigo " + presId;
        }
    }


}
