package co.edu.udea.compumovil.gr01_20171.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Informe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informe);

        Intent intent = getIntent();
        String informe = intent.getStringExtra("informe");

        TextView tvInforme = (TextView) findViewById(R.id.tv_informe);

        tvInforme.setText(informe);
    }
}
