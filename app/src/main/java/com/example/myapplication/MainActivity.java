package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.dao.PersonaDAO;
import com.example.myapplication.dao.UsuarioDAO;
import com.example.myapplication.data.model.menu_principal;
import com.example.myapplication.ui.login.LoginRegistro;

import java.sql.SQLInput;


public class MainActivity extends AppCompatActivity {

    Button ir;
    Button btn_registrarse;
    Button btn_iniciar_sesion;
    EditText edit_usuario;
    EditText edit_clave;
    CheckBox checkbox_guardar;
    UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }
        btn_registrarse = (Button) findViewById(R.id.btn_registrarse);
        btn_iniciar_sesion = (Button) findViewById(R.id.btn_inicio_sesion);
        edit_usuario = (EditText) findViewById(R.id.edit_usuario_login);
        edit_clave = (EditText) findViewById(R.id.edit_password_login);
        checkbox_guardar = (CheckBox) findViewById(R.id.checkbox_guardar_usuario);

        btn_iniciar_sesion.setOnClickListener(iniciarSesionClick);
        btn_registrarse.setOnClickListener(viewRegistrarseListener);
        checkbox_guardar.setOnClickListener(guardarUsuarioClick);
        ir = (Button) findViewById(R.id.goTo);
        ir.setOnClickListener(viewMenuListener);

        Bundle b = getIntent().getExtras();
        cargarUsuarioRecienCreado(b);
        cargarUsuarioDePreferencia();

        usuarioDAO = new UsuarioDAO(getApplicationContext());
    }

    private View.OnClickListener viewRegistrarseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginRegistro.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener viewMenuListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, menu_principal.class);
            startActivity((i));
        }
    };

    public View.OnClickListener iniciarSesionClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String usuario = edit_usuario.getText().toString();
            String clave = edit_clave.getText().toString();
            Intent intent;
            try{
                usuarioDAO.open();
                if(usuarioDAO.buscarUsuario(usuario,clave).isNull()){
                    Toast.makeText(getApplicationContext(),"El usuario o la clave son incorrectos.",Toast.LENGTH_LONG).show();
                }
                else{
                    intent = new Intent(MainActivity.this,menu_principal.class);
                    Toast.makeText(getApplicationContext(),"Bienvenido: "+usuario,Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Ocurrio un error.",Toast.LENGTH_LONG).show();
            }
            finally {
                usuarioDAO.close();
            }
        }
    };

    private View.OnClickListener guardarUsuarioClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean isChecked = checkbox_guardar.isChecked();//((CheckBox)view).isChecked();

            if(isChecked){
                SharedPreferences pref = getSharedPreferences("login",Context.MODE_PRIVATE );
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("usuario",edit_usuario.getText().toString());
                edit.putString("clave",edit_clave.getText().toString());
                edit.commit();
            }
        }
    };

    public void cargarUsuarioDePreferencia(){
        try {
            SharedPreferences pref = getSharedPreferences("login",Context.MODE_PRIVATE);
            edit_usuario.setText(pref.getString("usuario","sin datos"));
            edit_clave.setText(pref.getString("clave","default"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cargarUsuarioRecienCreado(Bundle b){
        try{
            String usuarioLogueado = b.getString("usuario");
            String clave = b.getString("clave");
            if(!usuarioLogueado.isEmpty()){
                edit_usuario.setText(usuarioLogueado);
                edit_clave.setText(clave);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testFragments(){
        Fragment firsFragment = new Fragment();
        firsFragment.setArguments(getIntent().getExtras());


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_sesion,firsFragment)
                .commit();

    }
}