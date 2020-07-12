package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.myapplication.entidades.Utilidades.*;

public class CargarProducto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ImageView imagen;
    Button btn_cargar;
    EditText nombre_producto, descripcion, precio, stock;
    Uri ruta;
    String categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_producto);
        imagen = (ImageView) findViewById(R.id.imagenId);
        // Spinner
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categoria_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner dropdown = (Spinner) findViewById(R.id.spinner_categoria);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        // BD
        AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this);
        final SQLiteDatabase bd = conn.open();
        // Boton cargar
        btn_cargar = (Button) findViewById(R.id.btnCargarProduct);
        btn_cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre_producto = (EditText) findViewById(R.id.nombre_producto);
                descripcion = (EditText) findViewById(R.id.descripcion);
                precio = (EditText) findViewById(R.id.precio);
                stock = (EditText) findViewById(R.id.stock);
                ContentValues values = new ContentValues();
                values.put(CAMPO_NOMBRE, String.valueOf(nombre_producto.getText()));
                values.put(CAMPO_DESCRIPCION, String.valueOf(descripcion.getText()));
                values.put(CAMPO_PRECIO, String.valueOf(precio.getText()));
                values.put(CAMPO_IDTIENDA, 014);
                values.put(CAMPO_STOCK, String.valueOf(stock.getText()));
                values.put(CAMPO_CATEGORIA, String.valueOf(categoria));
                values.put(CAMPO_IMAGEN, String.valueOf(ruta));
                bd.insert(TABLA_PRODUCTO, CAMPO_ID, values);
                Toast.makeText(getApplicationContext(),
                         "El producto se cargo con éxito.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onclick(View view) {
        cargarImagen();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la imagen"),10);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            ruta = data.getData();
            Uri path = data.getData();
            imagen.setImageURI(path);
            imagen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categoria = (String) parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}