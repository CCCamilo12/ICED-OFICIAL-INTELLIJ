package com.example.proyecto.Repositorio;

import com.example.proyecto.Entidad.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUser  extends JpaRepository<User,String> {
    User findByEmail(String Email);
}
