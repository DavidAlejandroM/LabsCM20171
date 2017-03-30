package co.edu.udea.compumovil.gr01_20171.lab3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;


//import co.edu.udea.compumovil.gr01_20171.lab2.listener.OnFragmentInteractionListener;


public class Login extends Activity  {


    public static final int id = 1 ;
    Button registro,iniciar;
    EditText user, pass;
    private DataBaseManager manager;
    private String usuario;
    private String contrasena;
    private Usuario u;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = new DataBaseManager(this.getBaseContext());

       // manager.insertarUsuario("1","1","jandro240@gmail.com",27,"foto");

        Cursor c;

        c = manager.selectTodosUsuarios();

        //Toast.makeText(this.getBaseContext(),String.valueOf(c.getCount()),Toast.LENGTH_LONG).show();

    }

    //private OnFragmentInteractionListener mlistener;
/*
    public static Login newInstance(){
        return new Login();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_login, container, false);
    }*/

   /* @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreateView(view, savedInstanceState);
        *//*registro = (Button)view.findViewById(R.id.registro);
        //registro.setOnClickListener(this);
        iniciar = (Button)view.findViewById(R.id.iniciar);
        //iniciar.setOnClickListener(this);*//*
        pass =(EditText) view.findViewById(R.id.et_contraseña);
        user = (EditText) view.findViewById(R.id.et_login_usuario);
        //mlistener = (OnFragmentInteractionListener) getActivity();
    }*/
/*
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registro:
                mlistener.setFragment(registro.id,null,false);
                break;
            case R.id.iniciar:
                String usu = user.getText().toString();
                String pas = pass.getText().toString();

                if(pas.isEmpty()){
                    Toast.makeText(getContext(), R.string.falta_dato, Toast.LENGTH_LONG).show();
                    return;
                }
                if(usu.isEmpty()){
                    Toast.makeText(getContext(), R.string.falta_dato, Toast.LENGTH_LONG).show();
                    return;
                }

        }
    }*/

    public void goToNavigation()
    {
        Intent intent = new Intent(Login.this,Main.class);
        intent.putExtra("id", u.getId());
        startActivity(intent);
        this.finish();

    }

    public void ClickInicioSesion(View view)
    {
        if (validarDatos()) {

            u = manager.inicioSesionUsuario(usuario, contrasena);

            if(u != null)
            {
                manager.updateEstadoUsuario(usuario,1);
                goToNavigation();
            }
            else
            {
                Toast.makeText(getBaseContext(),"Usuario y/o Contraseña erroneo",Toast.LENGTH_SHORT);
            }

        }
        else
        {
            Toast.makeText(getBaseContext(),"Usuario y/o Contraseña erroneo",Toast.LENGTH_SHORT);
        }

    }

    private boolean validarDatos() {
        user = (EditText) findViewById(R.id.et_login_usuario);
        pass = (EditText) findViewById(R.id.et_login_contraseña);

        usuario = user.getText().toString();
        contrasena = pass.getText().toString();
        if(!usuario.isEmpty() && !contrasena.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void goToRegistro()
    {
        Intent intent = new Intent(Login.this,Registrarse.class);
        startActivity(intent);

    }

    public void ClickRegistrarse(View view)
    {
        goToRegistro();
    }
}
