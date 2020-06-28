package com.example.myapplication.data.model;

public class Usuario {
    private int id;
    private String tipoUsuario;
    public String nombre;
    private String contraseña;
    public String tipo_documento;
    public String num_documento;
    public String dirección;
    public int telefono;
    public String email;
    public String localidad;

    //Las funciones siguientes son para conectarse con la Base de datos y que envié los datos también

    public void Usuario(int id, String name, String password, String document, String documentNum, String direction, int phone, String email, String localidad){
        this.id = id;
        this.nombre = name;
        this.contraseña = password;
        this.tipo_documento = document;
        this.num_documento = documentNum;
        this.dirección = direction;
        this.telefono = phone;
        this.email = email;
        this.localidad = localidad;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setTipoUsuario(String tipo){
        this.tipoUsuario = tipo;
    }

    public String getTipoUsuario(){
        return this.tipoUsuario;
    }

    public void setPassword(String password){
        this.contraseña = password;
    }

    public String getContraseña(){
        return this.contraseña;
    }
}
