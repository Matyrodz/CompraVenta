package com.example.myapplication.entidades;

public class Usuario {
    private Integer id, telefono, latitud, longitud, activo;
    private String nombre, usuario, rol, apellido, clave, email, direccion, localidad;

    public Usuario(){}
    public Usuario(Integer id, Integer telefono, Integer latitud, Integer longitud, Integer activo, String nombre, String usuario, String rol, String apellido, String clave, String email, String direccion, String localidad) {
        this.id = id;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.activo = activo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.rol = rol;
        this.apellido = apellido;
        this.clave = clave;
        this.email = email;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public boolean isNull(){
        if(this.id == null && this.usuario == null && this.nombre == null && this.apellido == null
        && this .email == null && this.clave == null && this.localidad == null){
            return true;
        }
        else{
            return false;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getLatitud() {
        return latitud;
    }

    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", telefono=" + telefono +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                ", activo=" + activo +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", rol='" + rol + '\'' +
                ", apellido='" + apellido + '\'' +
                ", clave='" + clave + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
