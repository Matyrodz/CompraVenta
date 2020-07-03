package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.login.LoginRegistro;

public class MainActivity extends AppCompatActivity {

    Button btn_registrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_registrarse = (Button) findViewById(R.id.btn_registrarse);

        btn_registrarse.setOnClickListener(viewRegistrarseListener);
    }

    private View.OnClickListener viewRegistrarseListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, LoginRegistro.class);
            startActivity(intent);
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