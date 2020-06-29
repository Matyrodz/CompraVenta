package com.example.myapplication.entidades;

public class Detalle_venta {
    private Integer idDetalle_venta, id_venta, id_articulo, cantidad;
    private Float precio;

    public Detalle_venta(Integer idDetalle_venta, Integer id_venta, Integer id_articulo, Integer cantidad, Float precio) {
        this.idDetalle_venta = idDetalle_venta;
        this.id_venta = id_venta;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Integer getIdDetalle_venta() {
        return idDetalle_venta;
    }

    public void setIdDetalle_venta(Integer idDetalle_venta) {
        this.idDetalle_venta = idDetalle_venta;
    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
