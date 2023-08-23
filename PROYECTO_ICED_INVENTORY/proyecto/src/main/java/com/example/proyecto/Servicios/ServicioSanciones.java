package com.example.proyecto.Servicios;

import com.example.proyecto.Entidad.Equipo;
import com.example.proyecto.Entidad.Sancion;
import com.example.proyecto.Repositorio.RepositorioSanciones;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioSanciones {
    private RepositorioSanciones repositorio;

    public ServicioSanciones(RepositorioSanciones repositorio){
        this.repositorio=repositorio;
    }
    public List<Sancion>MostrarTodosSanciones(){
        return repositorio.findAll();
    }


    public ResponseEntity<?> BuscarSancion(int id_sancion) {
        Optional<Sancion> sancionOptional = repositorio.findById(String.valueOf(id_sancion));
        if (sancionOptional.isPresent()) {
            Sancion sancion = sancionOptional.get();
            return ResponseEntity.ok(sancion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    public String insertarSanciones(Sancion sancion) {
//        if (sancion == null) {
//            return "Objeto de Sancion nulo";
//        }
//
//        if (repositorio.findById(String.valueOf(sancion.getPres_Id())).isPresent()) {
//            return "Sancion ya registrada";
//        } else {
//            try {
//                repositorio.save(sancion);
//                return "Se registró la Sancion correctamente";
//            } catch (Exception e) {
//                return "Error al registrar la Sancion: " + e.getMessage();
//            }
//        }
//    }

    public String eliminarSanciones(String id) {
        if (repositorio.findById(id).isPresent()) {
            repositorio.deleteById(id);
            return "la sancion con el codigo " + id + " fue eliminada exitosamente";
        } else {
            return "No se encontró ningúna sancion con el codigo " + id;
        }
    }

//    public String actualizarSanciones(Sancion E){
//        if (repositorio.findById(String.valueOf(E.getPres_Id())).isPresent()){
//            repositorio.save(E);
//            return "Sancion actualiazada correctamente";
//        }else{
//
//            return "la sancion no se actualizo, id ya existente";
//        }
//    }

    }


