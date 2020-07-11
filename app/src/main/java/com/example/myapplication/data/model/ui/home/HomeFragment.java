package com.example.myapplication.data.model.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;


public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private HomeViewModel homeViewModel;
    ListView ListViewProductos;
    AdminSQLiteOpenHelper conn;
    ArrayList<Producto> ListaProductos;
    ArrayList<String> ListaInformacion;
    CheckBox MayorPrecio, MenorPrecio;
    String filtro_categoria = "";
    String filtro = "";
    ArrayAdapter adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        if(ListaInformacion != null) {
            final TextView textView = root.findViewById(R.id.text_home);
            homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);

                }
            });
        }
        // Spinner
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(root.getContext(),
                R.array.filtro_categoria, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner dropdown = (Spinner) root.findViewById(R.id.filtror_categoria);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        conn = new AdminSQLiteOpenHelper(root.getContext());
        conn.open();
        ListViewProductos = (ListView) root.findViewById(R.id.ListViewProduct);
        consultarListaProductos();
        if(ListaInformacion != null) {
            adaptador = new ArrayAdapter(root.getContext(), android.R.layout.simple_list_item_1, ListaInformacion);
            ListViewProductos.setAdapter(adaptador);
        }

        MayorPrecio = (CheckBox) root.findViewById(R.id.box_mayorprecio);
        MenorPrecio = (CheckBox) root.findViewById(R.id.box_menorprecio);
        MayorPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MayorPrecio.isChecked()){
                    filtro = " ORDER BY precio DESC;";
                    MenorPrecio.setChecked(false);
                    if (adaptador != null){
                        Toast.makeText(HomeFragment.this.getContext(),"Precio de mayor a menor", Toast.LENGTH_LONG).show();
                        adaptador.clear();
                        ListViewProductos.invalidateViews();
                        consultarListaProductos();
                        if(ListaInformacion != null) {
                            adaptador = new ArrayAdapter(HomeFragment.this.getContext(), android.R.layout.simple_list_item_1, ListaInformacion);
                            ListViewProductos.setAdapter(adaptador);
                        }
                    }else{
                        Toast.makeText(HomeFragment.this.getContext(),"No hay elementos para filtrar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        MenorPrecio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MenorPrecio.isChecked()){
                    filtro = " ORDER BY precio ASC;";
                    MayorPrecio.setChecked(false);
                }
                if (adaptador != null){
                    Toast.makeText(HomeFragment.this.getContext(),"Precio de menor a mayor", Toast.LENGTH_SHORT).show();
                    adaptador.clear();
                    ListViewProductos.invalidateViews();
                    consultarListaProductos();
                    if(ListaInformacion != null) {
                        adaptador = new ArrayAdapter(HomeFragment.this.getContext(), android.R.layout.simple_list_item_1, ListaInformacion);
                        ListViewProductos.setAdapter(adaptador);
                    }
                }else{
                    Toast.makeText(HomeFragment.this.getContext(),"No hay elementos para filtrar", Toast.LENGTH_SHORT).show();
                }
            }
        });
        conn.close();
        return root;
    }
    private void consultarListaProductos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Producto producto = null;
        ListaProductos = new ArrayList<Producto>();
        Cursor c = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_PRODUCTO + filtro_categoria +filtro, null);
        if (c.getCount() != 0){
            c.moveToFirst();
            do {
                producto = new Producto();
                producto.setNombre(c.getString(c.getColumnIndex("nombre")));
                producto.setPrecio(c.getFloat(c.getColumnIndex("precio")));
                producto.setDescripcion(c.getString(c.getColumnIndex("descripcion")));
                producto.setImagen(c.getString(c.getColumnIndex("imagen")));
                ListaProductos.add(producto);
            } while (c.moveToNext());
            c.close();
            obtenerLista();
        }
    }

    private void obtenerLista() {
        ListaInformacion = new ArrayList<String>();
        for (int i = 0; i < ListaProductos.size(); i++) {
            ListaInformacion.add(ListaProductos.get(i).getNombre() + " - " + ListaProductos.get(i).getDescripcion() + " - " + ListaProductos.get(i).getPrecio() + "$ - " + ListaProductos.get(i).getDescripcion());
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (adaptador != null && (int) parent.getItemIdAtPosition(position) != 7){
            filtro_categoria = " WHERE categoria LIKE '"+(String) parent.getItemAtPosition(position)+"'";
            adaptador.clear();
            ListViewProductos.invalidateViews();
            consultarListaProductos();
            if(ListaInformacion != null) {
                adaptador = new ArrayAdapter(HomeFragment.this.getContext(), android.R.layout.simple_list_item_1, ListaInformacion);
                ListViewProductos.setAdapter(adaptador);
            }
        }else if(adaptador != null && (int) parent.getItemIdAtPosition(position) == 7) {
            filtro_categoria = "";
            adaptador.clear();
            ListViewProductos.invalidateViews();
            consultarListaProductos();
            if(ListaInformacion != null) {
                adaptador = new ArrayAdapter(HomeFragment.this.getContext(), android.R.layout.simple_list_item_1, ListaInformacion);
                ListViewProductos.setAdapter(adaptador);
            }
        }else{
            Toast.makeText(HomeFragment.this.getContext(),"No hay elementos para filtrar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}