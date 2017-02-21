package co.edu.udea.compumovil.gr01_20171.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.Toast;

public class OtherInfo extends AppCompatActivity {

    private CheckBox cbLeer;
    private CheckBox cbVerTv;
    private CheckBox cbBailar;
    private CheckBox cbCantar;
    private CheckBox cbNadar;
    private RatingBar rbLeer;
    private RatingBar rbVerTv;
    private RatingBar rbBailar;
    private RatingBar rbCantar;
    private RatingBar rbNadar;
    private Button btnMostrar;


    private Persona persona;

    private String informe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);

        Intent intent = getIntent();
        persona = (Persona) intent.getSerializableExtra("persona");

        //inicializando componetes de la UI
        cbLeer = (CheckBox) findViewById(R.id.cb_leer);
        cbVerTv = (CheckBox) findViewById(R.id.cb_verTv);
        cbBailar = (CheckBox) findViewById(R.id.cb_bailar);
        cbCantar = (CheckBox) findViewById(R.id.cb_cantar);
        cbNadar = (CheckBox) findViewById(R.id.cb_nadar);
        rbLeer = (RatingBar) findViewById(R.id.rb_leer);
        rbVerTv = (RatingBar) findViewById(R.id.rb_ver_tv);
        rbBailar = (RatingBar) findViewById(R.id.rb_bailar);
        rbCantar = (RatingBar) findViewById(R.id.rb_cantar);
        rbNadar = (RatingBar) findViewById(R.id.rb_nadar);
        btnMostrar = (Button) findViewById(R.id.btn_mostrar);


        eventosClickRatingBar();
        eventoClickMostrar();
    }

    private void eventoClickMostrar() {
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                informe = getResources().getString(R.string.nombres)+": " + persona.getNombres() + "\n" +
                        getResources().getString(R.string.apellido)+": " + persona.getApellidos() + "\n"+
                        getResources().getString(R.string.sexo)+": " + persona.getSexo() + "\n" +
                        getResources().getString(R.string.fecha_nacimiento)+": " + persona.getFechaNacimiento() + "\n"+
                        getResources().getString(R.string.grado_escolar)+": " + persona.getGradoEscolaridad() + "\n"+
                        getResources().getString(R.string.telefono)+": " + persona.getTelefono() + "\n"+
                        getResources().getString(R.string.direccion)+": " + persona.getDireccion() + "\n"+
                        getResources().getString(R.string.email)+":" + persona.getEmail() + "\n"+
                        getResources().getString(R.string.pais)+": " + persona.getpais() + "\n"+
                        getResources().getString(R.string.cuidad)+": " + persona.getCiudad() + "\n"+
                        getResources().getString(R.string.pasa_tiempos)+"\n";
                if (cbLeer.isChecked())
                {
                    informe = informe + getResources().getString(R.string.leer)+": "+ String.valueOf(rbLeer.getRating()*100/5)+" %\n";
                }
                if (cbVerTv.isChecked())
                {
                    informe = informe + getResources().getString(R.string.ver_tv)+": "+ String.valueOf(rbVerTv.getRating()*100/5)+" %\n";
                }
                if (cbBailar.isChecked())
                {
                    informe = informe + getResources().getString(R.string.Bailar)+": "+ String.valueOf(rbBailar.getRating()*100/5)+" %\n";
                }
                if (cbCantar.isChecked())
                {
                    informe = informe + getResources().getString(R.string.cantar)+": "+ String.valueOf(rbCantar.getRating()*100/5)+" %\n";
                }
                if (cbNadar.isChecked())
                {
                    informe = informe + getResources().getString(R.string.nadar)+": "+ String.valueOf(rbNadar.getRating()*100/5)+" %\n";
                }

                Intent intent = new Intent(getApplicationContext(),Informe.class);
                intent.putExtra("informe",informe);
                startActivity(intent);
            }
        });
    }

    private void eventosClickRatingBar() {
        rbLeer.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cbLeer.setChecked(true);
            }
        });

        rbVerTv.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cbVerTv.setChecked(true);
            }
        });

        rbBailar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cbBailar.setChecked(true);
            }
        });

        rbCantar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cbCantar.setChecked(true);
            }
        });

        rbNadar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                cbNadar.setChecked(true);
            }
        });

    }
}
