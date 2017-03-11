package co.edu.udea.compumovil.gr01_20171.lab1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactInfo extends AppCompatActivity {

    private EditText etTelefono;
    private EditText etDireccion;
    private EditText etEmail;
    private AutoCompleteTextView atvPaises;
    private AutoCompleteTextView atvCiudades;
    Button btnSiguiente;

    AutoCompleteTextView autoCompleteTextView;
    String [] nombrePiases, nombreCiudades;
    private Context context;
    private Persona persona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Intent intent = getIntent();

        //obtener la persona
        persona = (Persona) intent.getSerializableExtra("persona");

        //inicializando componentes
        etTelefono = (EditText) findViewById(R.id.et_telefono);
        etDireccion = (EditText) findViewById(R.id.et_direccion);
        etEmail = (EditText) findViewById(R.id.et_email);
        atvPaises = (AutoCompleteTextView)findViewById(R.id.paises);
        atvCiudades = (AutoCompleteTextView)findViewById(R.id.ciudades);

        this.context = this;

        nombrePiases = getResources().getStringArray(R.array.nom_paises);
        ArrayAdapter<String>adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombrePiases);
        atvPaises.setAdapter(adaptador);

        atvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pais = atvPaises.getText().toString();

                if (pais.equals("Colombia")){
                    Toast.makeText(getApplicationContext(),"Es Colombia!!", Toast.LENGTH_LONG).show();


                    nombreCiudades = getResources().getStringArray(R.array.nom_ciudades);
                    ArrayAdapter<String>adaptador = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,nombreCiudades);
                    atvCiudades.setAdapter(adaptador);

                }
                else {
                    Toast.makeText(getApplicationContext(),"No es Colombia!!", Toast.LENGTH_LONG).show();


                }

            }
        });

        btnSiguiente = (Button) findViewById(R.id.btn_siguiete_contact_info);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String telefono = etTelefono.getText().toString();
                String direccion = etDireccion.getText().toString();
                String email = etEmail.getText().toString();
                String pais = atvPaises.getText().toString();
                String ciudad = atvCiudades.getText().toString();

                if (!telefono.isEmpty() && !direccion.isEmpty() && !email.isEmpty()
                        && !pais.isEmpty() && !ciudad.isEmpty())
                {
                    persona.setTelefono(Integer.parseInt(telefono));
                    persona.setEmail(email);
                    persona.setDireccion(direccion);
                    persona.setpais(pais);
                    persona.setCiudad(ciudad);

                    Intent intent = new Intent(getApplicationContext(),OtherInfo.class);
                    intent.putExtra("persona",persona);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"La información ingresada no es correcta", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
}
