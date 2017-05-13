package co.edu.udea.compumovil.g01_20171.lab4;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import co.edu.udea.compumovil.g01_20171.lab4.Modelo.Evento;


public class EventosActivity extends Fragment {

    private DatabaseReference db;
    private int id = 0;
    private ArrayList<Evento> eventos;
    RecyclerView recyclerView;

    public EventosActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_evento, container, false);

        eventos = new ArrayList<Evento>();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_agregar_eventos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),AgregarEvento.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        db = FirebaseDatabase.getInstance().getReference("eventos");

        Log.d("firebaseReference: ", db.getDatabase().toString());

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                eventos = new ArrayList<Evento>();

                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Evento evento = postSnapshot.getValue(Evento.class);
                    if (evento != null)
                    {
                        eventos.add(evento);
                    }
                }

                id = (int) dataSnapshot.getChildrenCount();

                Toast.makeText(view.getContext(),String.valueOf(dataSnapshot.getChildrenCount()),Toast.LENGTH_LONG).show();

                actualizarListaEventos();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }


        });

        return view;
    }

    @Override
    public void onResume() {

        actualizarListaEventos();

        super.onResume();
    }

    private void actualizarListaEventos() {
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
                        //intent.putExtra("foto",e.getFoto());
                        startActivity(intent);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void clickAgregarEvento(View v)
    {

    }
}
