package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Opciones extends AppCompatActivity {

    private Spinner spinner;
    private static final String[] idiomas = {"Español", "English", "Portuguese"};
    private Context context = this;
    //SharedPreferences shard = getSharedPreferences("Archivo", context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        spinner = (Spinner)findViewById(R.id.language);
        ArrayAdapter <String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idiomas);
        spinner.setAdapter(adapter);
    }

    /*public void cambiarIdioma(View view){
        String val = spinner.getSelectedItem().toString();
        if(val == "Español"){
            Toast.makeText(this, "Español", Toast.LENGTH_SHORT).show();
            //Función en la que cambia el idioma a Español
        } else if (val == "English"){
            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
            //Funcion en al que cambia el idioma a Inglés
        }
        SharedPreferences shard = getPreferences(context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shard.edit();
        editor.putString("Idioma", val);
        editor.commit();
    }*/
}