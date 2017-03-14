package co.edu.udea.compumovil.gr01_20171.lab2;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;

public class Registrarse extends AppCompatActivity {
    public static final int ID = 2;

    EditText et_usuario, et_contrasena1, et_contrasena2, et_correo;

    private String usuario;
    private String contrasena1;
    private String contrasena2;
    private String correo;

    private DataBaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        manager = new DataBaseManager(getBaseContext());
        iniciliazarComponentes();
    }

    private void iniciliazarComponentes() {
        et_usuario = (EditText) findViewById(R.id.et_reg_usuario);
        et_contrasena1 = (EditText) findViewById(R.id.et_reg_contraseña);
        et_contrasena2 = (EditText) findViewById(R.id.et_reg_contraseña2);
        et_correo = (EditText) findViewById(R.id.et_reg_correo);
    }

    public void clickAceptar(View view)
    {
        if(validarCampos())
        {
            boolean a = manager.insertarUsuario(usuario,contrasena1,correo,20,"foto.jpg");
            if (a)
            {
                Toast.makeText(getBaseContext(),"Registro Exitoso!!!",Toast.LENGTH_LONG).show();
                this.finish();
            }
            else
            {
                Toast.makeText(getBaseContext(),"Registro NO Exitoso!!!",Toast.LENGTH_LONG).show();
            }

        }
    }

    /**
     * esta funcion valida que los campos no esten vacios y que las contraseñas coincidan
     * @return retorna true si ninguno vacio y contraseñas, false de lo contrario
     */
    private boolean validarCampos() {
        usuario = et_usuario.getText().toString();
        contrasena1 = et_contrasena1.getText().toString();
        contrasena2 = et_contrasena2.getText().toString();
        correo = et_correo.getText().toString();

        if(!usuario.isEmpty() && !contrasena1.isEmpty() && !contrasena2.isEmpty() & !correo.isEmpty()
                && contrasena1.equals(contrasena2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
