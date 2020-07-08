package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.AdminSQLiteOpenHelper;
import com.example.myapplication.entidades.Usuario;
import com.example.myapplication.entidades.Utilidades;

public class UsuarioDAO {
    private AdminSQLiteOpenHelper conn;
    //private SQLiteDatabase sql;

    public UsuarioDAO() {
        //this.conn = new AdminSQLiteOpenHelper(context);
    }

    public ContentValues crearUsuario(Usuario us,SQLiteDatabase sql){
        //sql=conn.open();
        Usuario usuario=buscarUsuario(us.getNombre(),sql);
        if(usuario != null) {
            ContentValues values = new ContentValues();
            values.put(Utilidades.CAMPO_USUARIO, us.getUsuario());
            values.put(Utilidades.CAMPO_EMAIL, us.getEmail());
            values.put(Utilidades.CAMPO_CLAVE, us.getClave());
            values.put(Utilidades.CAMPO_ROL, us.getRol());

            return values;
        }
        else{
            return null;
        }



    }

    public Usuario buscarUsuario(String nombreUsuario,SQLiteDatabase sql){
        String query = "SELECT id,usuario,nombre,apellido,email,localidad" +
                ",direccion,latitud,longitud,activo,rol FROM"+Utilidades.TABLA_USUARIO
                +"WHERE (nombre='"+nombreUsuario+"')";

        Cursor raw = sql.rawQuery(query,null);
        if(raw.isNull(0)){
            return null;
        }else{
            Usuario u = new Usuario();
            u.setId(raw.getInt(0));
            u.setUsuario(raw.getString(1));
            u.setNombre(raw.getString(2));
            u.setApellido(raw.getString(3));
            u.setEmail(raw.getString(4));
            u.setLocalidad(raw.getString(5));
            u.setDireccion(raw.getString(6));
            u.setLatitud(Integer.parseInt(raw.getString(7)));
            u.setLongitud(Integer.parseInt(raw.getString(8)));
            u.setActivo(Integer.parseInt(raw.getString(9)));
            u.setRol(raw.getString(10));
            return u;
        }
    }


}
