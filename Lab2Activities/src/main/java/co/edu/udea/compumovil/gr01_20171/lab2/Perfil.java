package co.edu.udea.compumovil.gr01_20171.lab2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Perfil extends Fragment {
    public static final int ID = 4;
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
        nom = (TextView) view.findViewById(R.id.nombreperf);
        email= (TextView) view.findViewById(R.id.correoperf);
        edad= (TextView) view.findViewById(R.id.edadperf);

    }
}
