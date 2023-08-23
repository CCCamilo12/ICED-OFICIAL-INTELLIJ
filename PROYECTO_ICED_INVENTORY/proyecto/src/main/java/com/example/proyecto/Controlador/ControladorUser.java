package com.example.proyecto.Controlador;

import com.example.proyecto.Entidad.User;
import com.example.proyecto.Servicios.ServicioUser;
import com.example.proyecto.Servicios.ServiciosUsuarios;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorUser {
    ServicioUser userServicio;
    ServiciosUsuarios usuSevicio;


    public ControladorUser(ServicioUser userServicio, ServiciosUsuarios usuSevicio) {
        this.userServicio = userServicio;
        this.usuSevicio = usuSevicio;
    }

    @GetMapping("/user")
    public User user(@AuthenticationPrincipal OidcUser principal){
        System.out.println(principal.getClaims());
        String email = (String) principal.getClaims().get("email");
        User user = this.userServicio.buscarEmail(email);
        return user;
    }
}
