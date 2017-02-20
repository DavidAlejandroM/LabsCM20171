package co.edu.udea.compumovil.gr01_20171.lab1;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import co.edu.udea.compumovil.gr01_20171.lab1.ContactInfo;

public class PersonalInfo extends AppCompatActivity {

    EditText et_nombres;
    EditText et_apellido;

    Contacto c;

    EditText etDate;
    Spinner spEstudio;

    Button btn_cambiar;
    Button btn_siguiente;

    private String[] nivelesEstudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        c = new Contacto();

        etDate = (EditText) findViewById(R.id.et_fecha_nacimiento);

        btn_cambiar = (Button) findViewById(R.id.btn_cambiar);


        /**
         * funcionalidad al boton de cambiar para abrir el fragment
         */
        btn_cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* DialogFragment newFragment = new DatePickerFragment(etDate);
                newFragment.show(getFragmentManager(), "datePicker");*/
            }
        });

        incializandoComponentes();

        crearSpinner();

        botonSiguiente();

    }

    private void incializandoComponentes() {
        et_nombres = (EditText) findViewById(R.id.et_nombres);

    }

    private void botonSiguiente() {
        btn_siguiente = (Button) findViewById(R.id.btn_siguiente);

        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombres = et_nombres.getText().toString();

                if (nombres.isEmpty() == false)
                {
                    c.setNombres(nombres);
                    Intent intent = new Intent(getApplicationContext(), ContactInfo.class);
                    intent.putExtra("contacto" , c);
                    startActivity(intent);
                }


            }
        });
    }

    /**
     * inicializamos el spinner y se le da funcionalidad
     */
    private void crearSpinner()
    {
        nivelesEstudio = getResources().getStringArray(R.array.escolaridad_array);
        spEstudio = (Spinner) findViewById(R.id.sp_grado_escolaridad) ;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.escolaridad_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spEstudio.setAdapter(adapter);

        spEstudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),nivelesEstudio[position],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


}
