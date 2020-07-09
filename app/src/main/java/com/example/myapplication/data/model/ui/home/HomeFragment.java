package com.example.myapplication.data.model.ui.home;

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
import com.example.myapplication.entidades.Producto;
import com.example.myapplication.entidades.Utilidades;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView ListViewProductos;
    AdminSQLiteOpenHelper conn;
    ArrayList<Producto> ListaProductos;
    ArrayList<String> ListaInformacion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        conn = new AdminSQLiteOpenHelper(root.getContext());
        ListViewProductos = (ListView) root.findViewById(R.id.ListViewProduct);
        consultarListaProductos();
        ArrayAdapter adaptador = new ArrayAdapter(root.getContext(),android.R.layout.simple_list_item_1,ListaInformacion);
        ListViewProductos.setAdapter(adaptador);
        return root;
    }
    private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto producto = null;
        ListaProductos = new ArrayList<Producto>();
        Cursor c = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PRODUCTO, null);
        if (c != null) {
            c.moveToFirst();
            do {
                producto = new Producto();
                //Asignamos el valor en nuestras variables para usarlos en lo que necesitemos
                producto.setNombre(c.getString(c.getColumnIndex("nombre")));
                producto.setPrecio(c.getFloat(c.getColumnIndex("precio")));
                producto.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                producto.setImagen(c.getString(c.getColumnIndex("imagen")));
                ListaProductos.add(producto);
            } while (c.moveToNext());
        }
        c.close();
        obtenerLista();
    }

    private void obtenerLista() {
        ListaInformacion = new ArrayList<String>();
        for (int i = 0; i<ListaProductos.size(); i++){
            ListaInformacion.add(ListaProductos.get(i).getNombre()+" - "+ListaProductos.get(i).getDescripcion() + " - "+ ListaProductos.get(i).getPrecio()+" - "+ListaProductos.get(i).getImagen());
        }

    }
}