package co.edu.udea.compumovil.gr01_20171.lab3;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;
//import co.edu.udea.compumovil.gr01_20171.lab2.listener.OnFragmentInteractionListener;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DataBaseManager manager;
    private Usuario usuario;
    private ImageView iv_foto_menu;
    private TextView tv_correo;
    private TextView tv_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this.getBaseContext());

        Intent intent = getIntent();
        int id = (int) intent.getIntExtra("id",0);

        usuario = manager.obtenerUSuarioById(id);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View v = navigationView.getHeaderView(0);

        iv_foto_menu = (ImageView) v.findViewById(R.id.iv_foto_main);
        tv_usuario = (TextView) v.findViewById(R.id.tv_titulo_menu);
        tv_correo = (TextView) v.findViewById(R.id.tv_correo_menu);
        iv_foto_menu.setImageBitmap(BitmapFactory.decodeByteArray(usuario.getFoto(),0,usuario.getFoto().length));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle Main view item clicks here.
        int id = item.getItemId();
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if (id == R.id.nav_eventos) {
            fragment = new Eventos();
            fragmentTransaction = true;
            Toast.makeText(getBaseContext(),"Click eventos",Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_eventos) {
            fragment = new Eventos();
            fragmentTransaction = true;

        } else if (id == R.id.nav_perfil) {
            fragment = new Perfil();
            Bundle args = new Bundle();
            args.putInt("id",getIntent().getIntExtra("id",0));
            fragment.setArguments(args);
            fragmentTransaction = true;

        } else if (id == R.id.nav_configuraciones) {
            fragment = new Configuracion();
            Bundle args = new Bundle();
            args.putInt("id",getIntent().getIntExtra("id",0));
            args.putInt("state", 0);
            fragment.setArguments(args);
            fragmentTransaction = true;

        } else if (id == R.id.nav_cerrar) {
            manager.updateEstadoUsuario(usuario.getUsuario(),0);
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);

            this.finish();

        } else if (id == R.id.nav_acerca) {
            fragment = new AcercaDe();
            fragmentTransaction = true;
        }

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/

        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main_fragments, fragment)
                    .commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
