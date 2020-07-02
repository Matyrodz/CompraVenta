package com.example.myapplication.entidades;

public class Categoria {
    private Integer id_Categoria, activo;
    private String nombre, descripcion;

    public Categoria(Integer id_Categoria, Integer activo, String nombre, String descripcion) {
        this.id_Categoria = id_Categoria;
        this.activo = activo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId_Categoria() {
        return id_Categoria;
    }

    public void setId_Categoria(Integer id_Categoria) {
        this.id_Categoria = id_Categoria;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
