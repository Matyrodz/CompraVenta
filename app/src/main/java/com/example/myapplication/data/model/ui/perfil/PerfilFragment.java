package com.example.myapplication.data.model.ui.perfil;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.dao.UsuarioDAO;
import com.example.myapplication.entidades.Usuario;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PerfilFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    PerfilViewModel perfilViewModel;
    UsuarioDAO usuarioDAO;
    EditText edit_nombre;
    EditText edit_apellido;
    EditText edit_telefono;
    EditText edit_email;
    EditText edit_localidad;
    EditText edit_direccion;
    EditText edit_clave;
    EditText edit_clave_dos;
    Button btn_guardar;
    Button btn_dar_baja;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        //datos del usuario
        edit_nombre = (EditText)root.findViewById(R.id.edit_nombre);
        edit_apellido = (EditText)root.findViewById(R.id.edit_apellido);
        edit_telefono = (EditText)root.findViewById(R.id.edit_telefono);
        edit_email = (EditText)root.findViewById(R.id.edit_email);
        edit_localidad = (EditText)root.findViewById(R.id.edit_localidad);
        edit_direccion = (EditText)root.findViewById(R.id.edit_direccion);
        edit_clave = (EditText)root.findViewById(R.id.edit_clave);
        edit_clave_dos = (EditText)root.findViewById(R.id.edit_clave_dos);

        btn_guardar = (Button)root.findViewById(R.id.btn_guardar_datos);
        btn_dar_baja = (Button)root.findViewById(R.id.btn_borrar_usuario);

        usuarioDAO = new UsuarioDAO(root.getContext());
        btn_guardar.setOnClickListener(guardarDatosClick);
        btn_dar_baja.setOnClickListener(darBajaClick);

        renderizarDatosPerfil();
        return root;
    }

    private View.OnClickListener guardarDatosClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Bundle bundle = getActivity().getIntent().getExtras();
            String nombreUsuario = bundle.getString("usuario");
            String telefono = edit_telefono.getText().toString();
            Usuario u = new Usuario();
            u.setUsuario(nombreUsuario);
            u.setNombre(edit_nombre.getText().toString());
            u.setApellido(edit_apellido.getText().toString());
            if(!telefono.isEmpty()){
                u.setTelefono(Integer.parseInt(telefono));
            }
            u.setEmail(edit_email.getText().toString());
            u.setClave(edit_clave.getText().toString());
            u.setLocalidad(edit_localidad.getText().toString());
            u.setDireccion(edit_direccion.getText().toString());


                if(validaDatos(u,edit_clave_dos.getText().toString())){
                    try{
                    usuarioDAO.open();
                    if(usuarioDAO.actualizarUsuario(u)){
                        Toast.makeText(getContext(),"Datos actualizados correctamente.",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getContext(),"Sucedio un error inesperado,revise los datos e intente nuevamente.",Toast.LENGTH_LONG).show();
                    }
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getContext(),"Ocurrio un error",Toast.LENGTH_LONG).show();
                    }
                    finally {
                        usuarioDAO.close();
                    }

                }




        }
    };

    private View.OnClickListener darBajaClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                Bundle b = getActivity().getIntent().getExtras();
                Usuario u = new Usuario();
                u.setUsuario(b.getString("usuario"));
                usuarioDAO.open();
                if(usuarioDAO.deleteUsuario(u)){
                    Toast.makeText(getContext(),"Usuario Borrado correctamente!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);

                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(getContext(),"Ocurrio un error al dar la baja del usuario.",Toast.LENGTH_LONG).show();
            }
            finally {
                usuarioDAO.close();
            }
        }
    };

    public boolean validaDatos(Usuario u, String claveDos){
        boolean ok=true;
        if(!u.getNombre().isEmpty() && !soloLetras(u.getNombre())){
            Toast.makeText(getContext(),"El Nombre debe contener solo letras.",Toast.LENGTH_SHORT).show();
            edit_nombre.setText("");
            edit_nombre.requestFocus();
            ok=false;
        }
        else if(!u.getApellido().isEmpty() && !soloLetras(u.getApellido())){
            Toast.makeText(getContext(),"El Apellido debe contener solo letras.",Toast.LENGTH_SHORT).show();
            edit_apellido.setText("");
            edit_apellido.requestFocus();
            ok=false;
        }
        else if(u.getTelefono() != null && !u.getTelefono().toString().isEmpty() && u.getTelefono() < 7){
            Toast.makeText(getContext(),"El telefono debe contener almenos 8 digitos.",Toast.LENGTH_LONG).show();
            edit_telefono.setText("");
            edit_telefono.requestFocus();
        }
        else if ((!u.getClave().isEmpty() || !claveDos.isEmpty()) && (!u.getClave().isEmpty() && !claveDos.isEmpty())){
            if(u.getClave().length() < 7){
                Toast.makeText(getContext(),"Las clave debe contener almenos 8 dígitos.",Toast.LENGTH_SHORT).show();
                limpiarClaves();
                edit_clave.requestFocus();
                edit_clave_dos.requestFocus();
                ok=false;
            }
            else if(!u.getClave().equals(claveDos)){
                Toast.makeText(getContext(),"La claves no coinciden.",Toast.LENGTH_LONG).show();
                limpiarClaves();
                edit_clave_dos.requestFocus();
                edit_clave.requestFocus();
                ok=false;
            }
            else if(u.getClave().contains(u.getUsuario())){
                Toast.makeText(getContext(),"La clave no debe contener el nombre de usuario",Toast.LENGTH_SHORT).show();
                limpiarClaves();
                ok=false;

            }
        }
        else if(!u.getEmail().isEmpty() && !validarEmail(u.getEmail())){
            Toast.makeText(getContext(),"El Email ingresado es invalido",Toast.LENGTH_SHORT).show();
            this.edit_email.setText("");
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

    public boolean soloLetras(String palabra){
        boolean ok = true;
        // Patrón para validar que tenga una mas letras
        Pattern pattern = Pattern
                .compile("[a-zA-Z]+");

        Matcher mather = pattern.matcher(palabra);

        if (mather.find() != true) {
            ok=false;
        }
        return ok;
    }

    public boolean soloNumeros(String numeros){
        boolean ok = true;
        // Patrón para validar que tenga una mas letras
        Pattern pattern = Pattern
                .compile("[0-9]+");

        Matcher mather = pattern.matcher(numeros);

        if (mather.find() != true) {
            ok=false;
        }
        return ok;
    }

    public void limpiarClaves(){
        edit_clave.setText("");
        edit_clave_dos.setText("");
    }

    public void renderizarDatosPerfil(){
        Bundle b = getActivity().getIntent().getExtras();
        try{
            usuarioDAO.open();
            Usuario u = usuarioDAO.buscarUsuario(b.getString("usuario"));
            edit_nombre.setText((u.getNombre() == null)? "":u.getNombre());
            edit_apellido.setText((u.getApellido() == null)? "":u.getApellido());
            edit_email.setText((u.getEmail() == null)? "":u.getEmail());
            edit_telefono.setText(u.getTelefono());
            edit_localidad.setText((u.getLocalidad() == null)? "":u.getLocalidad());
            edit_direccion.setText((u.getDireccion()==null)?"":u.getDireccion());
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(),"Ocurrio un error.",Toast.LENGTH_LONG).show();
        }
        finally {
            usuarioDAO.close();
        }
    }

    public void cargarLocalidades(){
        final String[] datos = new String[]{"Florencio Varela","Dante Ardigo","Zeballos",
        "Avellaneda","Temperley","Calzada","Lomas de Zamora","Quilmes","Claypole","Ranelagh"
        ,"Bernal"};
       // ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(),R.id.edit_localidad,datos);
    }
}