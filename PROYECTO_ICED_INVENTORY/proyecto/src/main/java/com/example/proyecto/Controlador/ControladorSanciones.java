package com.example.proyecto.Controlador;
import com.example.proyecto.Entidad.Prestamo;
import com.example.proyecto.Entidad.Sancion;
import com.example.proyecto.Servicios.ServicioSanciones;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ControladorSanciones {

    private ServicioSanciones servicio;

    public ControladorSanciones(ServicioSanciones servicio){
        this.servicio=servicio;
    }
    @GetMapping("/listarSanciones")
    public List<Sancion>listarSancio(){
        return servicio.MostrarTodosSanciones();
    }

    @GetMapping("/BuscarSanciones/{cod}")
    public ResponseEntity<?> buscarID(@PathVariable("cod") int id_sancion){
        return servicio.BuscarSancion(id_sancion);
    }

//    @PostMapping("/InsertarSanciones/")
//    public String insertarSancion(@RequestBody Sancion sancion){
//        return servicio.insertarSanciones(sancion);
//    }

    @DeleteMapping("/EliminarSanciones/{id}")
    public String eliminar(@PathVariable("id") String id) {
        return servicio.eliminarSanciones(id);
    }

//    @PostMapping("/ActualizarSanciones/")
//    public String actualizarProductos(@RequestBody Sancion actu){
//        return servicio.actualizarSanciones(actu);
//    }
}
