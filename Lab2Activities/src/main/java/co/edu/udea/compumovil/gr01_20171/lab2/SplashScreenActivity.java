package co.edu.udea.compumovil.gr01_20171.lab2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Window;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DbHelper;

/**
 * Created by alejandro on 10/03/17.
 */

public class SplashScreenActivity extends Activity {

    //duracion del SplashScreen
    private static final long SPLASH_SCREEN_DELAY = 3000;
    private DataBaseManager manager;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = new DataBaseManager(getBaseContext());
        usuario = manager.inicioSesionUsuarioAutomatico();

        // Set portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Para Ocultar la barra de titulo
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.splash_screen);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (usuario != null)
                {
                    // inciamos la nueva actividad
                    Intent mainIntent = new Intent().setClass(
                            SplashScreenActivity.this, Main.class);
                    mainIntent.putExtra("id", usuario.getId());
                    startActivity(mainIntent);
                }
                else
                {
                    // inciamos la nueva actividad
                    Intent mainIntent = new Intent().setClass(
                            SplashScreenActivity.this, Login.class);
                    startActivity(mainIntent);
                }



                //finalizamos la actividad del splash screen
                finish();
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
