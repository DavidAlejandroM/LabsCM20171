package co.edu.udea.compumovil.gr01_20171.lab1;

/**
 * Created by Alejandro on 18/02/2017.
 */

public class Contacto {
    private String nombres;
    private String apellidos;
    private String sexo;
    private String fechaNacimiento;
    private String gradoEscolaridad;
    private int telefono;
    private String direccion;
    private String email;
    private String pais;
    private String Ciudad;

    public Contacto() {
        
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGradoEscolaridad() {
        return gradoEscolaridad;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getpais() {
        return pais;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setGradoEscolaridad(String gradoEscolaridad) {
        this.gradoEscolaridad = gradoEscolaridad;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setpais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        Ciudad = ciudad;
    }
}
