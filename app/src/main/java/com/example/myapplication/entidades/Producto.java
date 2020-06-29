package com.example.myapplication.entidades;

public class Producto {
    private Integer _id,id_tienda,stock;
    private String nombre,descripcion;
    private float precio;

    // private static final String crear_tabla_productos = "CREATE TABLE productos (_id INTEGER PRIMARY KEY AUTOINCREMENT, id_tienda INTEGER, nombre TEXT, stock INTEGER, precio REAL, descripcion TEXT)";

    public Producto(Integer _id, Integer id_tienda, Integer stock, String nombre, String descripcion, float precio) {
        this._id = _id;
        this.id_tienda = id_tienda;
        this.stock = stock;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Integer id_tienda) {
        this.id_tienda = id_tienda;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
