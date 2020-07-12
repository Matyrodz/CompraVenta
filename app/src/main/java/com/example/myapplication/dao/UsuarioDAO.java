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
    private SQLiteDatabase sql;

    public UsuarioDAO(Context context) {
        this.conn = new AdminSQLiteOpenHelper(context);
    }

    public boolean crearUsuario(Usuario us){
        Usuario usuario=buscarUsuario(us.getUsuario());
        if(usuario.isNull()) {
            ContentValues values = new ContentValues();
            values.put("usuario", us.getUsuario());
            values.put("email", us.getEmail());
            values.put("clave", us.getClave());
            values.put("activo", 1);
            return sql.insert("usuario",null,values) > 0;
        }
        else{
            return false;
        }

    }

    public Usuario buscarUsuario(String nombreUsuario){
        String query = "SELECT * FROM "+Utilidades.TABLA_USUARIO
                +" WHERE usuario='"+nombreUsuario+"'";

        Cursor raw = sql.rawQuery(query,null);
        Usuario u = new Usuario();
        if(raw != null && raw.moveToFirst()){
            do{
                //u.setId(raw.getInt(raw.getColumnIndex("id")));
                u.setUsuario(raw.getString(raw.getColumnIndex("usuario")));
                u.setNombre(raw.getString(raw.getColumnIndex("nombre")));
                u.setApellido(raw.getString(raw.getColumnIndex("apellido")));
                u.setApellido(raw.getString(raw.getColumnIndex("clave")));
                //u.setEmail(raw.getString(raw.getColumnIndex("email")));
                //u.setLocalidad(raw.getString(raw.getColumnIndex("localidad")));
                //u.setDireccion(raw.getString(raw.getColumnIndex("direccion")));
                //u.setLatitud(raw.getInt(raw.getColumnIndex("latitud")));
                //u.setLongitud(raw.getInt(raw.getColumnIndex("longitud")));
                //u.setActivo(raw.getInt(raw.getColumnIndex("activo")));
                //u.setRol(raw.getString(10));

            }while(raw.moveToNext());
        }
        return u;
    }

    public Usuario buscarUsuario(String nombreUsuario, String clave){
        String query = "SELECT * FROM "+Utilidades.TABLA_USUARIO
                +" WHERE usuario='"+nombreUsuario+"' and clave='"+clave
                +"' and activo=1";

        Cursor raw = sql.rawQuery(query,null);
        Usuario u = new Usuario();
        if(raw != null && raw.moveToFirst()){
            do{
                u.setUsuario(raw.getString(raw.getColumnIndex("usuario")));
                u.setNombre(raw.getString(raw.getColumnIndex("nombre")));
                u.setApellido(raw.getString(raw.getColumnIndex("apellido")));

            }while(raw.moveToNext());
        }
        return u;
    }

    public void open(){
        this.sql = this.conn.open();
    }
    public void close(){
        sql.close();
    }


}
