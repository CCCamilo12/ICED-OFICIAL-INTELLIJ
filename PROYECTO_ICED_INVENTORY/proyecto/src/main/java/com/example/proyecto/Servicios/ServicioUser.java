package com.example.proyecto.Servicios;

import com.example.proyecto.Entidad.User;
import com.example.proyecto.Entidad.Usuario;
import com.example.proyecto.Repositorio.RepositorioUser;
import com.example.proyecto.Repositorio.RepositorioUsuarios;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ServicioUser {
    private RepositorioUser repositorio;
    private RepositorioUsuarios reposiUsu;

    public ServicioUser(RepositorioUser repositorio, RepositorioUsuarios reposiUsu) {
        this.repositorio = repositorio;
        this.reposiUsu = reposiUsu;
    }

    public User crear(User user){
        return repositorio.save(user);
    }

    public User buscarEmail(String email){
        if(repositorio.findById(email).isPresent()){
            return this.repositorio.findById(email).get();
        }else {
            return null;
        }
    }

    public User getCrearUser(Map<String,Object> dataUser){
        String email = (String) dataUser.get("email");

        User user=buscarEmail(email);
        String rol="";
        if (user==null){
            String name = (String) dataUser.get("nickname");
            String imag = (String) dataUser.get("picture");
            String auth_id = (String) dataUser.get("sub");
            if (reposiUsu.findByEmail(email)!=null){
                rol="Usuario";
            }
            else{
                rol="otro";
            }
            User nuevo = new User(email,name,imag,auth_id,rol);
            return this.crear(nuevo);
        }else{
            return user;
        }
    }

}
