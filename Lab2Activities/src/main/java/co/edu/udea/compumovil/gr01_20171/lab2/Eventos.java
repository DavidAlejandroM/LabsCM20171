package co.edu.udea.compumovil.gr01_20171.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Evento;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;


public class Eventos extends Fragment {

    FloatingActionButton fab_agregar_eventos;
    private DataBaseManager manager;
    RecyclerView recyclerView;

    public Eventos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);

        fab_agregar_eventos = (FloatingActionButton) view.findViewById(R.id.fab_agregar_eventos);

        fab_agregar_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),AgregarEvento.class);
                startActivity(intent);
            }
        });
        manager = new DataBaseManager(getActivity().getApplicationContext());



        return view;

    }

    @Override
    public void onResume() {
        final ArrayList<Evento> eventos = manager.obtenerTodosEventos();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_lista_eventos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new EventoAdapter(eventos));

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        Evento e = eventos.get(position);
                        Intent intent = new Intent(getActivity().getBaseContext(),VisualizarEvento.class);
                        //intent.putExtra("eventooo",e);
                        intent.putExtra("nombre",e.getNombre());
                        intent.putExtra("informacion",e.getInformacion());
                        intent.putExtra("responsable",e.getResponsable());
                        intent.putExtra("fecha",e.getFecha());
                        intent.putExtra("puntuacion",e.getPuntuacion());
                        intent.putExtra("latitud",e.getLatitud());
                        intent.putExtra("longitud",e.getLongitud());
                        //intent.putExtra("foto",(byte[]) e.getFoto());
                        /*intent.putExtra("foto",e.getFoto());*/
                        startActivity(intent);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        super.onResume();
    }


}
