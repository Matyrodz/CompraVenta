package com.example.myapplication.entidades;

public class Personas {
    private Integer id, telefono, latitud, longitud, activo;
    private String nombre, usuario, rol, apellido, clave, email, direccion, localidad;

    public Personas(Integer id, Integer telefono, Integer latitud, Integer longitud, Integer activo, String nombre, String rol, String usuario, String apellido, String clave, String email, String direccion, String localidad) {
        this.id = id;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.activo = activo;
        this.nombre = nombre;
        this.usuario = usuario;
        this.apellido = apellido;
        this.rol = rol;
        this.clave = clave;
        this.email = email;
        this.direccion = direccion;
        this.localidad = localidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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




    //  private static final String crear_tabla_usuario = "CREATE TABLE usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, nombre TEXT, apellido TEXT, clave TEXT, email TEXT, direccion TEXT, localidad TEXT, latitud INTEGER, longitud INTEGER, activo INTEGER)";
}
