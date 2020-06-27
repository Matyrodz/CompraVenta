package com.example.myapplication;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    // Tablas
    private static final String crear_tabla_usuario = "CREATE TABLE usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, usuario TEXT, nombre TEXT, apellido TEXT, clave TEXT, email TEXT, direccion TEXT, localidad TEXT, latitud INTEGER, longitud INTEGER, activo INTEGER)";
    private static final String crear_tabla_tienda = "CREATE TABLE tienda (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, direccion TEXT, localidad TEXT, latitud INTEGER, longitud INTEGER, activo INTEGER)";
    private static final String crear_tabla_productos = "CREATE TABLE productos (_id INTEGER PRIMARY KEY AUTOINCREMENT, id_tienda INTEGER, nombre TEXT, stock INTEGER, precio REAL, descripcion TEXT)";
    // Nombre de la db
    private static final String DB_NAME = "database.sqlite";
    // Version db
    private static final int DB_VERSION = 1;

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override // Creo las tablas
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crear_tabla_usuario);
        db.execSQL(crear_tabla_tienda);
        db.execSQL(crear_tabla_productos);
    }

    @Override // Chequeo las versión y actualizo las tablas
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
