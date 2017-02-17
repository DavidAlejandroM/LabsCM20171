package co.edu.udea.compumovil.gr01_20171.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class ContactInfo extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String [] NombrePiases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.paises);
        NombrePiases = getResources().getStringArray(R.array.nom_paises);
        ArrayAdapter<String>adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,NombrePiases);
        autoCompleteTextView.setAdapter(adaptador);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String pais = autoCompleteTextView.getText().toString();

                if (pais.equals("Colombia")){
                    Toast.makeText(getApplicationContext(),"Es Colombia!!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"No es Colombia!!", Toast.LENGTH_LONG).show();


                }

            }
        });
    }
}
