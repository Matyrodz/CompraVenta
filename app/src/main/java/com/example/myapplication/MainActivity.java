package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.dao.PersonaDAO;
import com.example.myapplication.dao.UsuarioDAO;
import com.example.myapplication.data.model.menu_principal;
import com.example.myapplication.ui.login.LoginRegistro;
/*import com.example.myapplication.ui.login.LoginActivity;*/


public class MainActivity extends AppCompatActivity {

    /*Button logIn;
    Button register;*/
    Button ir;
    Button btn_registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_registrarse = (Button) findViewById(R.id.btn_registrarse);
        btn_registrarse.setOnClickListener(viewRegistrarseListener);
        ir = (Button) findViewById(R.id.goTo);
        ir.setOnClickListener(viewMenuListener);
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

    public void testFragments(){
        Fragment firsFragment = new Fragment();
        firsFragment.setArguments(getIntent().getExtras());


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_sesion,firsFragment)
                .commit();

    }
}