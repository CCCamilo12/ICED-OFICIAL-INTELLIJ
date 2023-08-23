package com.example.proyecto.Servicios;
import com.example.proyecto.Entidad.Equipo;
import com.example.proyecto.Repositorio.RepositorioEquipos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServicioEquipos {
    private RepositorioEquipos repositorio;

    public ServicioEquipos(RepositorioEquipos repositorio) {
        this.repositorio = repositorio;
    }

    public List<Equipo> mostrarTodos() {
        return repositorio.findAll();
    }

    public ResponseEntity<?> buscarEquipo(int equId) {
        Optional<Equipo> equipoOptional = repositorio.findById(String.valueOf(equId));
        if (equipoOptional.isPresent()) {
            Equipo equipo = equipoOptional.get();
            return ResponseEntity.ok(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public String insertarEquipo(Equipo equipo) {
        if (equipo == null) {
            return "Objeto de equipo nulo";
        }

        if (repositorio.findById(String.valueOf(equipo.getEqu_id())).isPresent()) {
            return "El equipo ya está registrado";
        } else {
            try {
                repositorio.save(equipo);
                return "Se registró el equipo correctamente";
            } catch (Exception e) {
                return "Error al registrar el equipo: " + e.getMessage();
            }
        }
    }



    public String eliminarEquipo(String codigo) {
        if (repositorio.findById(codigo).isPresent()) {
            repositorio.deleteById(codigo);
            return "El Dispositivo con el codigo " + codigo + " fue eliminado exitosamente";
        } else {
            return "No se encontró ningún Dispositivo con el codigo " + codigo;
        }
    }

    public String actualizarEquipo(Equipo E){
        if (repositorio.findById(String.valueOf(E.getEqu_id())).isPresent()){
            repositorio.save(E);
            return "El equipo se Actualizo correctamente";
        }else{

            return "El id del equipo ya existe";
        }
    }
}

