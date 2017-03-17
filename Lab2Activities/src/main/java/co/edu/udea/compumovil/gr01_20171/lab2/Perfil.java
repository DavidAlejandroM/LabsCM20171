package co.edu.udea.compumovil.gr01_20171.lab2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;


public class Perfil extends Fragment {

    private Usuario usuario;
    private DataBaseManager manager;

    public Perfil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        manager = new DataBaseManager(getActivity().getApplicationContext());
        Bundle args = getArguments();
        int id = args.getInt("id");
        usuario = manager.obtenerUSuarioById(id);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false);

    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

   /* public static final int ID = 4;
    static String nombreUsuario;
    TextView nom, email, edad;

    public static Perfil newInstance(String nombre) {
        nombreUsuario = nombre;
        return new Perfil();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }*/
}
