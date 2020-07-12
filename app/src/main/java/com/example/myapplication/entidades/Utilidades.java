package com.example.myapplication.entidades;

public class Utilidades {

    // Constantes de Usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_CLAVE = "clave";
    public static final String CAMPO_APELLIDO = "apellido";
    public static final String CAMPO_EMAIL = "email";
    public static final String CAMPO_DIRECCION = "direccion";
    public static final String CAMPO_LOCALIDAD = "localidad";
    public static final String CAMPO_LATITUD = "latitud";
    public static final String CAMPO_LONGITUD = "longitud";
    public static final String CAMPO_ROL = "rol";
    public static final String CAMPO_ACTIVO = "activo";
    // TABLA Usuario
   /*  public static final String CREAR_TABLA_USUARIO= "CREATE TABLE "+"" +  TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_USUARIO+" TEXT NOT NULL, "+CAMPO_CLAVE+" TEXT NOT NULL, "+
            CAMPO_NOMBRE +" TEXT NOT NULL, "+ CAMPO_APELLIDO + " TEXT NOT NULL, "+CAMPO_DIRECCION + " TEXT NOT NULL, "+ CAMPO_LOCALIDAD + " TEXT NOT NULL, "+
            CAMPO_EMAIL + " TEXT NOT NULL, "+CAMPO_ROL + " TEXT, "+ CAMPO_LATITUD +" INTEGER NOT NULL, "+ CAMPO_LONGITUD+ " INTEGER NOT NULL, "+ CAMPO_ACTIVO + " INTEGER NOT NULL)";
 */

    // TABLA Usuario
    public static final String CREAR_TABLA_USUARIO= "CREATE TABLE " +  TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_USUARIO+" TEXT, "+CAMPO_NOMBRE + " TEXT, "+
            CAMPO_APELLIDO + " TEXT, "+CAMPO_DIRECCION + " TEXT, "+ CAMPO_LOCALIDAD + " TEXT, "+CAMPO_CLAVE+" TEXT ,"+ CAMPO_EMAIL + " TEXT, "+CAMPO_ROL + " TEXT, "+
            CAMPO_LATITUD +" INTEGER, "+ CAMPO_LONGITUD+ " INTEGER, "+ CAMPO_ACTIVO + " INTEGER)";

    // Constantes de Producto
    public static final String TABLA_PRODUCTO = "producto";
    public static final String CAMPO_STOCK = "stock";
    public static final String CAMPO_DESCRIPCION = "descripcion";
    public static final String CAMPO_IDTIENDA="id_tienda";
    public static final String CAMPO_PRECIO ="precio";
    public static final String CAMPO_IMAGEN = "imagen";
    // TABLA PRODUCTO
    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE "+ TABLA_PRODUCTO+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_IDTIENDA+ " INTEGER NOT NULL, "+
            CAMPO_STOCK+ " INTEGER NOT NULL, "+CAMPO_PRECIO+ " REAL NOT NULL, "+CAMPO_NOMBRE+ " TEXT NOT NULL, "+CAMPO_DESCRIPCION+ " TEXT, "+CAMPO_IMAGEN+ " TEXT NOT NULL)";

    // Constantes de venta
    public static final String TABLA_VENTA = "venta";
    public static final String CAMPO_ID_CLIENTE = "id_cliente";
    public static final String CAMPO_ID_VENDEDOR = "id_vendedor";
    public static final String CAMPO_ESTADO = "estado";
    public static final String CAMPO_COMPROBANTE ="nro_comprobante";
    public static final String CAMPO_TIPOCOMPROBANTE= "tipo_comprobante";
    public static final String CAMPO_TOTAL ="total";
    public static final String CAMPO_DATE = "fecha_alta";
    // TABLA TIENDA
    public static final String CREAR_TABLA_VENTA = "CREATE TABLE "+ TABLA_VENTA +" ("+CAMPO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_ID_CLIENTE+" INTEGER NOT NULL, "+CAMPO_ID_VENDEDOR+ " INTEGER NOT NULL, "+CAMPO_ESTADO+" TEXT NOT NULL, "+
            CAMPO_TIPOCOMPROBANTE+" TEXT NOT NULL, "+ CAMPO_COMPROBANTE+" INTEGER NOT NULL, "+CAMPO_TOTAL+" REAL NOT NULL, "+CAMPO_DATE+ " DATETIME DEFAULT CURRENT_DATE)";


    // Constantes de Categoria
    public static final String TABLA_CATEGORIA = "categoria";
    // TABLA CATEGORIA
    public static final String CREAR_TABLA_CATEGORIA = "CREATE TABLE "+""+ TABLA_CATEGORIA+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_NOMBRE+" TEXT NOT NULL, "+CAMPO_DESCRIPCION+" TEXT NOT NULL, "+CAMPO_ACTIVO+" INTEGER NOT NULL)";

    // Constantes de detalle de venta
    public static final String TABLA_DETALLEVENTA = "detalle_venta";
    public static final String CAMPO_ID_VENTA ="id_venta";
    public static final String CAMPO_ARTICULO ="id_articulo";
    public static final String CAMPO_CANTIDAD ="cantidad";
    // TABLA DETALLE VENTA
    public static final String  CREAR_TABLA_DETALLEVENTA = "CREATE TABLE "+TABLA_DETALLEVENTA+" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_ID_VENTA+" INTEGER NOT NULL, "+CAMPO_ARTICULO+" INTEGER NOT NULL, "+CAMPO_CANTIDAD+" INTEGER NOT NULL, "+ CAMPO_PRECIO+" REAL NOT NULL)";
}
