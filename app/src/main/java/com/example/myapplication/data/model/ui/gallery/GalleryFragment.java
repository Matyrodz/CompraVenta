package com.example.myapplication.data.model.ui.gallery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.AdminSQLiteOpenHelper;
import com.example.myapplication.R;
import com.example.myapplication.data.model.ui.home.HomeViewModel;
import com.example.myapplication.entidades.Producto;
import com.example.myapplication.entidades.Utilidades;

import java.util.ArrayList;


public class GalleryFragment extends Fragment {

    private HomeViewModel homeViewModel;
    /*ArrayList<String> ListaInformacion;
    ArrayList<Producto> ListaProductos;
    ListView ListViewProductos;
    AdminSQLiteOpenHelper conn;
    ArrayAdapter adaptador;*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        /*if (ListaInformacion != null) {
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

        conn.close();*/
        return root;
    }

    /*private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto producto = null;
        ListaProductos = new ArrayList<Producto>();
        Cursor c = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PRODUCTO , null);
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
    }*/
}