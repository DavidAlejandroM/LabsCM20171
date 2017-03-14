package co.edu.udea.compumovil.gr01_20171.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Evento;
import co.edu.udea.compumovil.gr01_20171.lab2.R;

public class VisualizarEvento extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_responsable;
    private EditText et_informacion;
    private EditText et_fecha;
    private EditText et_latitud;
    private EditText et_longitud;
    private RatingBar rb_puntuacion;
    private ImageView iv_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_evento);
        Intent intent = getIntent();
        Evento evento = (Evento) intent.getSerializableExtra("evento");

        et_nombre = (EditText) findViewById(R.id.et_nombre_vis_evento);


    }
}
