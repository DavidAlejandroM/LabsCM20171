package co.edu.udea.compumovil.gr01_20171.lab2.Modelo;

import java.io.Serializable;

/**
 * Created by Alejandro on 13/03/2017.
 */

public class Usuario implements Serializable{

    private String usuario;
    private String contraseña;
    private String email;
    private int edad;
    private byte[] foto;
    private int estado;
    private int id;

    public Usuario(String usuario, String contraseña, String email, int edad, byte[] foto, int estado) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.email = email;
        this.edad = edad;
        this.foto = foto;
        this.estado = estado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
