package com.example.proyecto.Servicios;
import com.example.proyecto.Entidad.Equipo;
import com.example.proyecto.Entidad.Usuario;
import com.example.proyecto.Repositorio.RepositorioUsuarios;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ServiciosUsuarios {
   private RepositorioUsuarios reposiUsu;

    public ServiciosUsuarios(RepositorioUsuarios repositorio) {
        this.reposiUsu = repositorio;
    }

    public List<Usuario> mostrarTodosUsuarios() {
        return reposiUsu.findAll();
    }

    public Usuario mostrarUsuarioCorreo(String email){
        Usuario usu=null;
        usu=reposiUsu.findBycorreo(email);
        return usu;
    }

    public ResponseEntity<?> BuscarUsuario(int Usu_Documento) {
        Optional<Usuario> usuarioOptional = reposiUsu.findById(String.valueOf(Usu_Documento));
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public String insertaUsuario(Usuario Us){
        if (reposiUsu.findById(Us.getUsu_Documento()).isPresent())
            return "El Usuario ya existe";
        else{
            reposiUsu.save(Us);
            return "Registrado exitosamente";
        }
    }

    public String eliminarUsuario(String Usu_Documento) {
        if (reposiUsu.findById(Usu_Documento).isPresent()) {
            reposiUsu.deleteById(Usu_Documento);
            return "El Usuario con numero de Documento " + Usu_Documento + " fue eliminado exitosamente";
        } else {
            return "No se encontró ningún Usuario con el Numero de Cedula " + Usu_Documento;
        }
    }

    public String actualizarUsuario(Usuario Doc){
        if (reposiUsu.findById(String.valueOf(Doc.getUsu_Documento())).isPresent()){
            reposiUsu.save(Doc);
            return "La informacion del Usuario se actualizo correctamente";
        }else{

            return "El usuario no se actualizo, Numero de Cedula no actualizable";
        }
    }
}
