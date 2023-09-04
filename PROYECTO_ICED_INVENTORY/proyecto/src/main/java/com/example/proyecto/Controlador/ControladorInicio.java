package com.example.proyecto.Controlador;
import com.example.proyecto.Entidad.User;
import com.example.proyecto.Servicios.ServicioUser;
import com.example.proyecto.Servicios.ServiciosUsuarios;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.sql.SQLOutput;


@Controller
public class ControladorInicio {

    ServicioUser userServicio;
    ServiciosUsuarios usuSevicio;

    public ControladorInicio(ServicioUser userServicio, ServiciosUsuarios usuSevicio) {
        this.userServicio = userServicio;
        this.usuSevicio = usuSevicio;
    }

    @GetMapping("/")
    public String index(Model model,@AuthenticationPrincipal OidcUser principal){
        if (principal != null){
            System.out.println(principal.getClaims());
            User user =this.userServicio.getCrearUser(principal.getClaims());
            model.addAttribute("user",user);
            if (user.getRol().equals("Usuario")) {
                return "Principal";

            }
            else{
                return "Equipos";
            }

        }
        else{
            return "index";
        }
    }

    @GetMapping("/index")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/Principal")
    public String PrincipalPage(){
        return "Principal";
    }

    @GetMapping("/Equipos")
    public String EquiposPage(Model model,@AuthenticationPrincipal OidcUser principal){
        User user =this.userServicio.getCrearUser(principal.getClaims());
        model.addAttribute("user",user);
        return "Equipos";
    }





}

