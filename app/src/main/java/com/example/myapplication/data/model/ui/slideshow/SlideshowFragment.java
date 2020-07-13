package com.example.myapplication.data.model.ui.slideshow;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.AdminSQLiteOpenHelper;
import com.example.myapplication.R;
import com.example.myapplication.data.model.MuestraProducto;
import com.example.myapplication.data.model.ui.home.HomeFragment;
import com.example.myapplication.data.model.ui.home.HomeViewModel;
import com.example.myapplication.entidades.Producto;
import com.example.myapplication.entidades.Utilidades;

import java.util.ArrayList;


public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private HomeViewModel homeViewModel;
    ListView ListViewProductos;
    AdminSQLiteOpenHelper conn;
    ArrayList<Producto> ListaProductos;
    ArrayList<String> ListaInformacion;
    CheckBox MayorPrecio, MenorPrecio;
    String filtro_usuario = "";
    String filtro = "014";
    ArrayAdapter adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        if(ListaInformacion != null) {
            final TextView textView = root.findViewById(R.id.text_home);
            homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);

                }
            });
        }

        conn = new AdminSQLiteOpenHelper(root.getContext());
        conn.open();
        ListViewProductos = (ListView) root.findViewById(R.id.ListViewProduct);
        consultarListaProductos();
        if(ListaInformacion != null) {
            adaptador = new ArrayAdapter(root.getContext(), android.R.layout.simple_list_item_1, ListaInformacion);
            ListViewProductos.setAdapter(adaptador);
        }

        ListViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                String[] producto = item.split("-");
                Intent intent = new Intent(SlideshowFragment.this.getContext(), MuestraProducto.class);
                intent.putExtra("nombre", producto[0]);
                intent.putExtra("categoria", producto[1]);
                intent.putExtra("precio", producto[2]);
                intent.putExtra("descripcion", producto[3]);
                startActivity(intent);
            }
        });

        conn.close();
        return root;
    }

    private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto producto = null;
        ListaProductos = new ArrayList<Producto>();
        Cursor c = db.rawQuery("SELECT * FROM "+ Utilidades.CAMPO_IDTIENDA + filtro_usuario +filtro, null);
        if (c.getCount() != 0){
            c.moveToFirst();
            do {
                producto = new Producto();
                producto.setNombre(c.getString(c.getColumnIndex("nombre")));
                producto.setPrecio(c.getString(c.getColumnIndex("precio")));
                producto.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                producto.setCategoria(c.getString(c.getColumnIndex("categoria")));
                ListaProductos.add(producto);
            } while (c.moveToNext());
            c.close();
            obtenerLista();
        }
    }

    private void obtenerLista() {
        ListaInformacion = new ArrayList<String>();
        for (int i = 0; i < ListaProductos.size(); i++) {
            ListaInformacion.add(ListaProductos.get(i).getNombre() + " - " + ListaProductos.get(i).getCategoria() + " - " + ListaProductos.get(i).getPrecio() + "$ - " + ListaProductos.get(i).getDescripcion());
        }
    }
}