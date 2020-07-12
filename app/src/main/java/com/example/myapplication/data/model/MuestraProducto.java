package com.example.myapplication.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.AdminSQLiteOpenHelper;
import com.example.myapplication.R;
import com.example.myapplication.data.model.ui.home.HomeFragment;
import com.example.myapplication.entidades.Producto;
import com.example.myapplication.entidades.Utilidades;

import java.util.ArrayList;

public class MuestraProducto extends AppCompatActivity {
    TextView nombre, precio, descripcion, categoria;
    Button comprar, ubicacion;
    AdminSQLiteOpenHelper conn;
    String nombre_producto, descripcion_producto, categoria_producto, precio_producto;
    Producto producto;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_producto);
        nombre = (TextView) findViewById(R.id.nombre_muestra);
        categoria = (TextView) findViewById(R.id.categoria_producto);
        precio = (TextView) findViewById(R.id.precio_producto);
        descripcion = (TextView) findViewById(R.id.descripcion_muestra);
        comprar = (Button) findViewById(R.id.comprar_producto);
        ubicacion = (Button) findViewById(R.id.ubicacion_producto);

        Bundle datos = this.getIntent().getExtras();
        nombre_producto = datos.getString("nombre");
        descripcion_producto = datos.getString("descripcion");
        categoria_producto = datos.getString("categoria");
        precio_producto = datos.getString("precio");

        nombre_producto = nombre_producto.substring(0, nombre_producto.length() - 1);

        nombre.setText(nombre_producto);
        categoria.setText(categoria_producto);
        descripcion.setText(descripcion_producto);
        precio.setText(precio_producto);
        precio_producto = precio_producto.substring(1, precio_producto.length() - 2);
        conn = new AdminSQLiteOpenHelper(this);
        final SQLiteDatabase bd = conn.open();
        consultarListaProductos();
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MuestraProducto.this, Ubicacion_producto.class);
                intent.putExtra("latitud", producto.getLatitud());
                intent.putExtra("longitud", producto.getLongitud());
                startActivity(intent);
            }
        });

    }
    private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PRODUCTO + " WHERE nombre LIKE '"+nombre_producto+"';"/*+"' AND (precio LIKE '"+precio_producto+"');"*/, null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            producto = new Producto();
            producto.setNombre(c.getString(c.getColumnIndex("nombre")));
            producto.setPrecio(c.getString(c.getColumnIndex("precio")));
            producto.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
            producto.setCategoria(c.getString(c.getColumnIndex("categoria")));
            producto.setLatitud(c.getString(c.getColumnIndex("latitud")));
            producto.setLongitud(c.getString(c.getColumnIndex("longitud")));
        }
        c.close();
    }
}