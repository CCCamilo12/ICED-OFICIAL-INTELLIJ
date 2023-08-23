package com.example.proyecto.Controlador;

import com.example.proyecto.Entidad.Prestamo;
import com.example.proyecto.Servicios.ServicioPrestamos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ControladorPrestamos {
    private ServicioPrestamos Servicio;

    public ControladorPrestamos(ServicioPrestamos servicio){
        this.Servicio= servicio;
    }

    @GetMapping("/ListarPrestamos")
    public List<Prestamo> listarPrestamos(){
        return Servicio.MostrarTodosPrestamos();
    }

    @GetMapping("/BuscarPrestamo/{cod}")
    public ResponseEntity<?> buscarID(@PathVariable("cod") int PresId){
        return Servicio.buscarPrestamo(PresId);
    }
    @DeleteMapping("/EliminarPrestamo/{cod}")
    public String eliminarUsuarioo(@PathVariable("cod") int PresId) {
        return Servicio.eliminarPrestamo(PresId);
    }
    @PostMapping("/insertarPrestamo/{Eq}/{Us}")
    public String insertar_Prestamoo(@PathVariable("Eq") String Eq, @PathVariable("Us") String Us,@RequestBody Prestamo prestamo) {
        return Servicio.insertarPrestamo(Eq, Us, prestamo);
    }

    @PostMapping("/ActualizarPrestamo/{Eq}/{Us}")
    public String actualizarPrestamoo(@PathVariable("Eq") String Eq, @PathVariable("Us") String Us,@RequestBody Prestamo prestamo) {
        return Servicio.actualizarPrestamo(Eq, Us, prestamo);
    }



}
