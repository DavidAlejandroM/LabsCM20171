package co.edu.udea.compumovil.gr01_20171.lab2;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Login extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Para Ocultar la barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_login);
    }

    public void goToNavigation()
    {
        Intent intent = new Intent(Login.this,navigation.class);
        startActivity(intent);

    }

    public void ClickInicioSesion(View view)
    {
        goToNavigation();
    }
}
