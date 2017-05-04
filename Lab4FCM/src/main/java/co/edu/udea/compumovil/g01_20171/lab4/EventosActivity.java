package co.edu.udea.compumovil.g01_20171.lab4;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.udea.compumovil.g01_20171.lab4.Modelo.Evento;


public class EventosActivity extends Fragment {

    private DatabaseReference db;

    public EventosActivity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evento, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_agregar_eventos);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),AgregarEvento.class);
                startActivity(intent);
            }
        });

        db = FirebaseDatabase.getInstance().getReference();

        Log.d("firebaseReference: ", db.getDatabase().toString());

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Evento post = dataSnapshot.getValue("eventos");
                System.out.println(post.getNombre());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return view;
    }

    public void clickAgregarEvento(View v)
    {

    }
}
