package com.example.myapplication.ui.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.myapplication.AdminSQLiteOpenHelper;
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
    private AdminSQLiteOpenHelper conn;
    private SQLiteDatabase sql;
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
        btn_crear.setOnClickListener(crearUsuarioClick);

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
            String rol = "comprador";
            if(!edit_clave.equals(edit_clave_dos)){
                Toast.makeText(getApplicationContext(),"Claves Erroneas",Toast.LENGTH_SHORT).show();
            }else {
                Usuario u = new Usuario();
                u.setUsuario(edit_usuario);
                u.setEmail(edit_email);
                u.setClave(edit_clave);
                u.setRol(rol);
                try {
                    sql = conn.open();
                    if (crearUsuario(u)) {
                        Toast.makeText(getApplicationContext(), "Usuario creado Exitosamente!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "El nombre de usuario ya existe!", Toast.LENGTH_LONG).show();
                    }
                    limpiarDatos();
                }catch (Exception c){
                    c.printStackTrace();
                }
                finally{
                    sql.close();
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
    public boolean crearUsuario(Usuario us){
        Usuario usuario=null;//buscarUsuario(us.getUsuario());
        if(usuario == null) {
            ContentValues values = new ContentValues();
            values.put("usuario", us.getUsuario());
            values.put("email", us.getEmail());
            values.put("clave", us.getClave());
            //values.put("rol", us.getRol());
            Long registros=sql.insert("usuario",null,values);
            boolean ok=registros > 0;
            return ok;
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

    public Usuario buscarUsuario(String nombreUsuario){
        String query = "SELECT id,usuario,nombre,apellido,email,localidad" +
                ",direccion,latitud,longitud,activo,rol FROM "+Utilidades.TABLA_USUARIO
                +" WHERE nombre='"+nombreUsuario+"'";

        Cursor raw = sql.rawQuery(query,null);
        String userName = raw.getString(6);
        if(userName == ""){
            return null;
        }else{
            Usuario u = new Usuario();
            u.setId(raw.getInt(0));
            u.setUsuario(raw.getString(1));
            u.setNombre(raw.getString(2));
            u.setApellido(raw.getString(3));
            u.setEmail(raw.getString(4));
            u.setLocalidad(raw.getString(5));
            u.setDireccion(raw.getString(6));
            u.setLatitud(Integer.parseInt(raw.getString(7)));
            u.setLongitud(Integer.parseInt(raw.getString(8)));
            u.setActivo(Integer.parseInt(raw.getString(9)));
            u.setRol(raw.getString(10));
            return u;
        }
        return ok;
    }
}
