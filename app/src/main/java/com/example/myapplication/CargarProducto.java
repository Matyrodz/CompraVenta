package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.entidades.Utilidades;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.myapplication.entidades.Utilidades.*;

public class CargarProducto extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView imagen;
    Button btn_cargar;
    EditText nombre_producto, descripcion, precio, stock;
    String currentPhotoPath;
    Uri ruta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_producto);
        imagen = (ImageView) findViewById(R.id.imagenId);

        if (ContextCompat.checkSelfPermission(CargarProducto.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CargarProducto.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CargarProducto.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this);
        final SQLiteDatabase bd = conn.open();
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

    public void onclickTomar(View view){
        tomarFoto();
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la imagen"),10);
    }

    public void tomarFoto(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
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
}