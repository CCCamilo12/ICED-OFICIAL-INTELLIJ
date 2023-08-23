package com.example.proyecto.Repositorio;

import com.example.proyecto.Entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioUsuarios  extends JpaRepository<Usuario,String> {
    Usuario findBycorreo(String email);
}
