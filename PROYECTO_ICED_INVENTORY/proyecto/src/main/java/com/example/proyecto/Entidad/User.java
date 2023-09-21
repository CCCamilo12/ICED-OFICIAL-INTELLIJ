package com.example.proyecto.Entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String email;

    @Column(name = "usuario",unique = true)
    private String usuario;

    @Column(name = "img")
    private String img;

    @Column(name = "auth_id", unique = true)
    private String auth_id;

    @Column(name = "rol")
    private String rol;

    public User() {
    }

    public User(String email, String usuario, String img, String auth_id, String rol) {
        this.email = email;
        this.usuario = usuario;
        this.img = img;
        this.auth_id = auth_id;
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(String auth_id) {
        this.auth_id = auth_id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
