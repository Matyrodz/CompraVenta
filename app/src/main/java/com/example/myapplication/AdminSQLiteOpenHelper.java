package com.example.myapplication;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.myapplication.entidades.Utilidades;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase bd;
    // Nombre de la db
    private static final String DB_NAME = "database.sqlite";
    // Version db
    private static final int DB_VERSION = 1;

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Utilidades.CREAR_TABLA_USUARIO);
            db.execSQL(Utilidades.CREAR_TABLA_VENTA);
            db.execSQL(Utilidades.CREAR_TABLA_PRODUCTO);
            db.execSQL(Utilidades.CREAR_TABLA_CATEGORIA);
            db.execSQL(Utilidades.CREAR_TABLA_DETALLEVENTA);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_VENTA);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_PRODUCTO);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS "+ Utilidades.TABLA_DETALLEVENTA);
        onCreate(db);
    }
    public SQLiteDatabase open(){
        bd = this.getWritableDatabase();
        return bd;
    }
    public void close(){
        bd.close();
    }
}