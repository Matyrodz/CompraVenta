package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class Opciones extends AppCompatActivity {

    private Spinner spinner;
    private static final String[] idiomas = {"Español", "English", "Portuguese"};
    private Context context = this;
    Button btn_idioma;

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        spinner = (Spinner)findViewById(R.id.language);
        ArrayAdapter <String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, idiomas);
        spinner.setAdapter(adapter);
        btn_idioma = (Button)findViewById(R.id.btnAceptar);
        btn_idioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarIdioma();
            }
        });


    }

    public void cambiarIdioma(){
        String val = spinner.getSelectedItem().toString();
        if(val == "Español"){
            Toast.makeText(this, "Español", Toast.LENGTH_SHORT).show();
            Locale locale = new Locale("es");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getResources().updateConfiguration(config, null);
            super.recreate();
            //Función en la que cambia el idioma a Español
        } else if (val == "English"){
            Toast.makeText(this, "English", Toast.LENGTH_SHORT).show();
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getResources().updateConfiguration(config, null);
            super.recreate();
        }else{
            Toast.makeText(this, "Português", Toast.LENGTH_SHORT).show();
            Locale locale = new Locale("pt");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            context.getResources().updateConfiguration(config, null);
            super.recreate();
        }
    }
    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }
}