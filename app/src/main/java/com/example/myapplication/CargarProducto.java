package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.entidades.Utilidades;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.myapplication.entidades.Utilidades.*;

public class CargarProducto extends AppCompatActivity implements AdapterView.OnItemSelectedListener, LocationListener {

    static final int REQUEST_TAKE_PHOTO = 1;
    ImageView imagen;
    Button btn_cargar;
    EditText nombre_producto, descripcion, precio, stock;
    String currentPhotoPath;
    Uri ruta;
    String categoria, lat, longi;
    private LocationManager locManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        // Cargar ubicación
        onLocationChanged(rastreoGPS());
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
                values.put(CAMPO_LATITUD, String.valueOf(lat));
                values.put(CAMPO_LONGITUD, String.valueOf(longi));
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
        startActivityForResult(intent.createChooser(intent, "Seleccione la imagen"), 10);
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
        if (resultCode == RESULT_OK) {
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

    // Geolocalización
    @SuppressLint("MissingPermission")
    private Location rastreoGPS() {
        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = String.valueOf(locManager.getBestProvider(criteria, true));
        locManager.requestLocationUpdates(bestProvider,1000,0, this);
        Location loc = locManager.getLastKnownLocation(bestProvider);
        return loc;
    }

    @Override
    public void onLocationChanged(Location loc) {
        this.lat = String.valueOf(loc.getLatitude());
        this.longi = String.valueOf(loc.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}