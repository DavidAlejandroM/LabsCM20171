package co.edu.udea.compumovil.gr01_20171.lab3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;

public class Configuracion extends Fragment
{
    private DataBaseManager manager;
    private Usuario usuario;

    private Switch sw_sesion;
    private Button btn_editar_perfil;

    private View view;

    public Configuracion() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int id = args.getInt("id");

        manager = new DataBaseManager(getActivity().getApplicationContext());

        usuario = manager.obtenerUSuarioById(id);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_configuracion, container, false);



        sw_sesion = (Switch) view.findViewById(R.id.sw_config_sesion);
        btn_editar_perfil = (Button) view.findViewById(R.id.btn_editar_perfil);

        sw_sesion.setChecked(true);

        sw_sesion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    if(manager.updateEstadoUsuario(usuario.getUsuario(),1))
                    {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Inicio de Sesion Automático ACTIVADO",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if(manager.updateEstadoUsuario(usuario.getUsuario(),0))
                    {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Inicio de Sesion Automático DESACTIVADO",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btn_editar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment2 = new Perfil();
                Bundle args = new Bundle();

                args.putInt("id", getActivity().getIntent().getIntExtra("id",0));
                args.putInt("state", 1);
                fragment2.setArguments(args);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main_fragments, fragment2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;

    }


}
