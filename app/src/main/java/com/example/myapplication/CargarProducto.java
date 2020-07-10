package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.entidades.Utilidades;

import static com.example.myapplication.entidades.Utilidades.*;

public class CargarProducto extends AppCompatActivity {
    ImageView imagen;
    Button btn_cargar;
    EditText nombre_producto, descripcion, precio, stock;
    Uri path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_producto);
        imagen = (ImageView) findViewById(R.id.imagenId);

        AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this);
        final SQLiteDatabase bd = conn.open();
        btn_cargar = (Button) findViewById(R.id.btnCargarProduct);
        btn_cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre_producto = (EditText) findViewById(R.id.nombre_producto);
                ContentValues values = new ContentValues();
                values.put(CAMPO_NOMBRE, String.valueOf(nombre_producto.getText()));
                values.put(CAMPO_DESCRIPCION, /*String.valueOf(descripcion.getText())*/"roja");
                values.put(CAMPO_PRECIO, /*Float.parseFloat(String.valueOf(precio.getText()))*/14);
                values.put(CAMPO_IDTIENDA, 014);
                values.put(CAMPO_STOCK, /*Integer.parseInt(String.valueOf(stock.getText()))*/2);
                values.put(CAMPO_IMAGEN, /*String.valueOf(path.getPath()*/"imagen/pieza.png");
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
            path = data.getData();
            imagen.setImageURI(path);
            imagen.setVisibility(View.VISIBLE);
        }
    }
}