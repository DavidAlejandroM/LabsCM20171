package co.edu.udea.compumovil.gr01_20171.lab3;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Evento;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;

public class VisualizarEvento extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_responsable;
    private EditText et_informacion;
    private EditText et_fecha;
    private EditText et_latitud;
    private EditText et_longitud;
    private RatingBar rb_puntuacion;
    private ImageView iv_foto;
    private DataBaseManager manager;
    Evento evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_evento);
        Intent intent = getIntent();
        String nombre = (String) intent.getStringExtra("nombre");
        String informacion = (String) intent.getStringExtra("informacion");
        String responsable = (String) intent.getStringExtra("responsable");
        String fecha = (String) intent.getStringExtra("fecha");
        int puntuacion = (int) intent.getIntExtra("puntuacion",0);
        float latitud = (float) intent.getFloatExtra("latitud",0);
        float longitud = (float) intent.getFloatExtra("longitud",0);

        manager = new DataBaseManager(getApplicationContext());
        evento = manager.getEventoFromNombreFecha(nombre,fecha);


        et_nombre = (EditText) findViewById(R.id.et_nombre_vis_evento);
        et_responsable = (EditText) findViewById(R.id.et_responsable_vis_evento);
        et_fecha = (EditText) findViewById(R.id.et_fecha_vis_evento);
        et_informacion = (EditText) findViewById(R.id.et_informacion_vis_evento);
        et_latitud = (EditText) findViewById(R.id.et_latitud_vis_evento);
        et_longitud = (EditText) findViewById(R.id.et_longitud_vis_evento);
        rb_puntuacion = (RatingBar) findViewById(R.id.rb_puntuacion_vis_evento);
        iv_foto = (ImageView) findViewById(R.id.iv_foto_vis_evento);

        et_nombre.setText(nombre);
        et_responsable.setText(responsable);
        et_fecha.setText(fecha);
        et_informacion.setText(informacion);
        et_latitud.setText(String.valueOf(latitud));
        et_longitud.setText(String.valueOf(longitud));
        rb_puntuacion.setRating((float)puntuacion);
        iv_foto.setImageBitmap(BitmapFactory.decodeByteArray(evento.getFoto(),0,evento.getFoto().length));

    }

    public void clickGoogleMaps(View view)
    {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("geo:"+ String.valueOf(evento.getLatitud()) +
                ","+String.valueOf(evento.getLongitud())+"?z=18");

// Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
// Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

// Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);

    }

}
