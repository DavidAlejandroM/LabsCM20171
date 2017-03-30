package co.edu.udea.compumovil.gr01_20171.lab3;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;

public class Registrarse extends AppCompatActivity {
    public static final int ID = 2;

    EditText et_usuario, et_contrasena1, et_contrasena2, et_correo, et_edad;
    ImageView iv_foto;

    private String usuario;
    private String contrasena1;
    private String contrasena2;
    private String correo;
    private String edad;

    final int REQUEST_CODE_GALLERY = 999;
    private Bitmap bitmap = null;

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
        et_edad = (EditText) findViewById(R.id.et_reg_edad);
        iv_foto = (ImageView) findViewById(R.id.iv_reg_foto);
    }

    public void clickAceptar(View view)
    {
        if(validarCampos())
        {

            boolean a = manager.insertarUsuario(usuario,contrasena1,correo,Integer.valueOf(edad),imageViewToByte(iv_foto),0);
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
        edad = et_edad.getText().toString();

        if(!usuario.isEmpty() && !contrasena1.isEmpty() && !contrasena2.isEmpty() && !correo.isEmpty()
                && contrasena1.equals(contrasena2) && !edad.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void clickSeleccionarImagen(View v)
    {
        ActivityCompat.requestPermissions(
                Registrarse.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(getBaseContext(),"No tiene permisos",Toast.LENGTH_LONG).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();

            try {
                InputStream inputStrem = getContentResolver().openInputStream(uri);
                bitmap = BitmapFactory.decodeStream(inputStrem);
                iv_foto.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
