package com.example.myapplication.data.model;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;

public class MuestraProducto extends AppCompatActivity {
    TextView nombre, precio, descripcion, categoria;
    Button comprar, ubicacion;

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
        String nombre_producto = datos.getString("nombre");
        String descripcion_producto = datos.getString("descripcion");
        String categoria_producto = datos.getString("categoria");
        String precio_producto = datos.getString("precio");

        nombre.setText(nombre_producto);
        categoria.setText(categoria_producto);
        descripcion.setText(descripcion_producto);
        precio.setText(precio_producto);

    }
}