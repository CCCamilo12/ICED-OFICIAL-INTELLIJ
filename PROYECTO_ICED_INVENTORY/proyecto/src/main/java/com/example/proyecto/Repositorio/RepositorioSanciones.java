package com.example.proyecto.Repositorio;

import com.example.proyecto.Entidad.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioSanciones extends JpaRepository<Sancion,String> {

}
