package co.edu.udea.compumovil.gr01_20171.lab2;

import android.app.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//import co.edu.udea.compumovil.gr01_20171.lab2.listener.OnFragmentInteractionListener;


public class Login extends Activity  {


    public static final int id = 1 ;
    Button registro,iniciar;
    EditText user, pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        startActivity(intent);

    }

    public void ClickInicioSesion(View view)
    {
        goToNavigation();
    }
}
