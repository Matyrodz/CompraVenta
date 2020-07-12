package com.example.myapplication.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.MainActivity;
import com.example.myapplication.dao.UsuarioDAO;
<<<<<<< HEAD
=======

>>>>>>> e1533b32a563521d53b6b9af7c4c7051cfffc042
import com.example.myapplication.entidades.Usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginRegistro extends AppCompatActivity{
    Button btn_crear ;
    Button btn_cancelar;
    UsuarioDAO usuarioDAO;
    EditText usuario;
    EditText email;
    EditText getEmail;
    EditText clave;
    EditText clave_dos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registro);
        btn_crear=(Button)findViewById(R.id.btn_crear_cuenta);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);

        btn_crear.setOnClickListener(crearUsuarioClick);
        btn_cancelar.setOnClickListener(cancelarUsuarioClick);
        usuario =(EditText) findViewById(R.id.nombre_usuario);
        email = (EditText)findViewById(R.id.email_usuario);
        clave =(EditText) findViewById(R.id.password_uno);
        clave_dos = (EditText)findViewById(R.id.password_dos);
        usuarioDAO = new UsuarioDAO(getApplicationContext());
    }

    private final View.OnClickListener crearUsuarioClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            String edit_clave_dos = clave_dos.getText().toString();
            Usuario u = new Usuario();
            u.setUsuario(usuario.getText().toString());
            u.setEmail(email.getText().toString());
            u.setClave(clave.getText().toString());
            if(validaDatos(u,edit_clave_dos)) {
                try {
                    usuarioDAO.open();
                    if (usuarioDAO.crearUsuario(u)) {
                        Toast.makeText(getApplicationContext(), "Usuario creado Exitosamente!", Toast.LENGTH_LONG).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("usuario",u.getUsuario());
                        bundle.putString("clave",u.getClave());
                        intent = new Intent(LoginRegistro.this,MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "El nombre de usuario ya existe!", Toast.LENGTH_LONG).show();
                    }
                    limpiarDatos();
                }
                catch (Exception c){
                    c.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Ocurrio un error.",Toast.LENGTH_SHORT).show();
                }
                finally{
                    usuarioDAO.close();
                }
            }
        }
    };

    private final View.OnClickListener cancelarUsuarioClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginRegistro.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    private void limpiarDatos(){
        usuario.setText("");
        email.setText("");
        clave.setText("");
        clave_dos.setText("");

    }

    public void limpiarClaves(){
        clave.setText("");
        clave_dos.setText("");
    }

    public boolean validaDatos(Usuario u, String claveDos){
        boolean ok=true;
        if(u.getClave().isEmpty() && u.getUsuario().isEmpty() && u.getEmail().isEmpty() && claveDos.isEmpty()){
            Toast.makeText(getApplicationContext(),"Todos los datos son obligatorios.",Toast.LENGTH_SHORT).show();
            limpiarDatos();
            ok=false;
        }
        else if(!u.getClave().equals(claveDos)){
            Toast.makeText(getApplicationContext(),"Las claves no coinciden.",Toast.LENGTH_SHORT).show();
            limpiarClaves();
            ok=false;
        }
        else if(u.getClave().length() < 7){
            Toast.makeText(getApplicationContext(),"Las clave debe contener almenos 8 dígitos.",Toast.LENGTH_SHORT).show();
            limpiarClaves();
            ok=false;
        }
        else if(u.getClave().contains(u.getUsuario())){
            Toast.makeText(getApplicationContext(),"La clave no debe contener el nombre de usuario",Toast.LENGTH_SHORT).show();
            limpiarClaves();
            ok=false;

        }
        else if(u.getUsuario().length() < 3){
            Toast.makeText(getApplicationContext(),"El usuario debe contener almenos 4 caracteres. ",Toast.LENGTH_SHORT).show();
            this.usuario.setText("");
            ok=false;
        }
        else if(!validarEmail(u.getEmail())){
            Toast.makeText(getApplicationContext(),"El Email ingresado es invalido",Toast.LENGTH_SHORT).show();
            this.email.setText("");
            ok=false;
        }
        return ok;

    }

    public boolean validarEmail(String email){
        boolean ok = true;
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");

        Matcher mather = pattern.matcher(email);

        if (mather.find() != true) {
            ok=false;
        }
        return ok;
    }
}
