package co.edu.udea.compumovil.g01_20171.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void clicIniciarSesion(View view){
        Intent intent = new Intent(this,Main.class);
        startActivity(intent);
    }
}
