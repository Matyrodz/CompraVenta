package com.example.myapplication.entidades;

import java.util.Date;

public class Utilidades {

    // Constantes de Usuario
    public static final String TABLA_USUARIO = "persona", CAMPO_USUARIO = "usuario",CAMPO_ID = "id", CAMPO_NOMBRE = "nombre", CAMPO_CLAVE = "clave", CAMPO_APELLIDO = "apellido", CAMPO_EMAIL = "email", CAMPO_DIRECCION = "direccion", CAMPO_LOCALIDAD = "localidad",CAMPO_LATITUD = "latitud", CAMPO_LONGITUD = "longitud", CAMPO_ACTIVO = "activo";
    // TABLA Usuario
    public static final String CREAR_TABLA_USUARIO= "CREATE TABLE " +  TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_USUARIO+" TEXT, "+CAMPO_NOMBRE + " TEXT, "+
            CAMPO_APELLIDO + " TEXT, "+CAMPO_DIRECCION + " TEXT, "+ CAMPO_LOCALIDAD + " TEXT, "+ CAMPO_EMAIL + " TEXT, "+
            CAMPO_LATITUD +" INTEGER, "+ CAMPO_LONGITUD+ " INTEGER, "+ CAMPO_ACTIVO + " INTEGER";

    // Constantes de venta
    public static final String TABLA_VENTA = "venta", CAMPO_ID_CLIENTE = "id_cliente", CAMPO_ID_VENDEDOR = "id_vendedor", CAMPO_ESTADO = "estado", CAMPO_COMPROBANTE ="nro_comprobante", CAMPO_TIPOCOMPROBANTE= "tipo_comprobante",CAMPO_TOTAL ="total", CAMPO_DATE = "fecha_alta";
    // TABLA TIENDA
    public static final String CREAR_TABLA_VENTA = "CREATE TABLE "+TABLA_VENTA +" ("+CAMPO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_ID_CLIENTE+" INTEGER, "+CAMPO_ID_VENDEDOR+
            " INTEGER, "+CAMPO_ESTADO+" TEXT, "+CAMPO_TIPOCOMPROBANTE+" TEXT, "+ CAMPO_COMPROBANTE+" INTEGER, "+CAMPO_TOTAL+" FLOAT"+CAMPO_DATE+" DATETIME DEFAULT CURRENT_DATE";

    // Constantes de Producto
    public static final String TABLA_PRODUCTO = "producto", CAMPO_STOCK = "stock", CAMPO_DESCRIPCION = "descripcion", CAMPO_IDTIENDA="id_tienda",CAMPO_PRECIO ="precio";
    // TABLA PRODUCTO
    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE"+ TABLA_PRODUCTO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_IDTIENDA+ " INTEGER, "+
            CAMPO_STOCK+ " INTEGER, "+CAMPO_PRECIO+ " FLOAT, "+CAMPO_NOMBRE+ " TEXT, "+CAMPO_DESCRIPCION+ " TEXT";

    // Constantes de Categoria
    public static final String TABLA_CATEGORIA = "categoria";
    // TABLA CATEGORIA
    public static final String CREAR_TABLA_CATEGORIA = "CREATE TABLE"+ TABLA_CATEGORIA+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_NOMBRE+" TEXT, "+CAMPO_DESCRIPCION+" TEXT, "+CAMPO_ACTIVO+" INTEGER";

    // Constantes de detalle de venta
    public static final String TABLA_DETALLEVENTA = "detalle_venta", CAMPO_ID_VENTA="id_venta", CAMPO_ARTICULO ="id_articulo",CAMPO_CANTIDAD ="cantidad";
    // TABLA DETALLE VENTA
    public static final String  CREAR_TABLA_DETALLEVENTA = "CREATE TABLE"+TABLA_DETALLEVENTA+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_ID_VENTA+" INTEGER, "+CAMPO_ARTICULO+" INTEGER, "+CAMPO_CANTIDAD+" INTEGER, "+ CAMPO_PRECIO+" FLOAT";
}
