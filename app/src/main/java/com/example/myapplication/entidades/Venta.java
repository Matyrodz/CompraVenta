package com.example.myapplication.entidades;

import java.util.Date;

public class Venta {
    private Integer _id, id_cliente, id_vendedor, num_comprobante;
    private String estado, tipo_comprobante;
    private Float total;
    private Date fecha_alta;

    public Venta(Integer _id, Integer id_cliente, Integer id_vendedor, Integer num_comprobante, String estado, String tipo_comprobante, Float total, Date fecha_alta) {
        this._id = _id;
        this.id_cliente = id_cliente;
        this.id_vendedor = id_vendedor;
        this.num_comprobante = num_comprobante;
        this.estado = estado;
        this.tipo_comprobante = tipo_comprobante;
        this.total = total;
        this.fecha_alta = fecha_alta;
    }

    public Integer get_id() { return _id; }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(Integer id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Integer getNum_comprobante() {
        return num_comprobante;
    }

    public void setNum_comprobante(Integer num_comprobante) {
        this.num_comprobante = num_comprobante;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
}
