package co.edu.udea.compumovil.g01_20171.lab4;

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
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import co.edu.udea.compumovil.g01_20171.lab4.Modelo.Evento;


public class AgregarEvento extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_responsable;
    private EditText et_informacion;
    private EditText et_fecha;
    private EditText et_latitud;
    private EditText et_longitud;
    private RatingBar rb_puntuacion;
    private ImageView iv_foto;
    private String nombre;
    private String foto;
    private String responsable;
    private String informacion;
    private String fecha;
    private float latitud;
    private float longitud;
    private int rating;
    private Bitmap bitmap = null;
    private int id;

    private DatabaseReference db;
    private String EVENTOS = "eventos";
    //private DataBaseManager manager;

    final int REQUEST_CODE_GALLERY = 999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id",0);
        setContentView(R.layout.activity_agregar_evento);
        db = FirebaseDatabase.getInstance().getReference();
        inicializarComponente();
    //    manager = new DataBaseManager(getApplicationContext());
    }

    private void inicializarComponente() {

        et_nombre = (EditText) findViewById(R.id.et_nombre_add_evento);
        et_responsable = (EditText) findViewById(R.id.et_responsable_add_evento);
        et_informacion= (EditText) findViewById(R.id.et_informacion_add_evento);
        et_fecha= (EditText) findViewById(R.id.et_fecha_add_evento);
        et_latitud= (EditText) findViewById(R.id.et_latitud_add_evento);
        et_longitud= (EditText) findViewById(R.id.et_longitud_add_evento);
        rb_puntuacion= (RatingBar) findViewById(R.id.rb_puntuacion_add_evento);
        iv_foto= (ImageView) findViewById(R.id.iv_foto_add_evento);

    }

    public void clickSeleccionarImagen(View v)
    {
        ActivityCompat.requestPermissions(
                AgregarEvento.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
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
            Toast.makeText(this,uri.getPath(),Toast.LENGTH_LONG).show();
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

    public void clickAceptarEvento(View v)
    {
        if (validarDatos())
        {
            //boolean b = manager.insertarEvento(nombre,imageViewToByte(iv_foto),responsable,rating,fecha,
             //       latitud,longitud,informacion);
            //id ++;
            Evento evento = new Evento(nombre,foto,responsable,rating,fecha,latitud,longitud,informacion);
            db.child(EVENTOS).child(String.valueOf(id)).setValue(evento);
            boolean b = true;
            if (b)
            {
                id++;
                Toast.makeText(getApplicationContext(),"EventosActivity Registrado Satisfactoriamente",Toast.LENGTH_LONG);
                this.finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"EventosActivity no Ingresado INTALO MAS TARDE",Toast.LENGTH_LONG);
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Ingrese Bien toda la informacion",Toast.LENGTH_LONG);
        }
    }

    private boolean validarDatos()
    {
        String lat = et_latitud.getText().toString();
        String lon = et_longitud.getText().toString();
        nombre = et_nombre.getText().toString();
        responsable = et_responsable.getText().toString();
        informacion = et_informacion.getText().toString();
        fecha = et_fecha.getText().toString();


        rating = ((int) rb_puntuacion.getRating());


        if (!nombre.isEmpty() && !responsable.isEmpty() && !informacion.isEmpty() && !fecha.isEmpty() &&
                !lat.isEmpty() && !lon.isEmpty() && bitmap != null) {
            latitud = Float.valueOf(et_latitud.getText().toString());
            longitud = Float.valueOf(et_longitud.getText().toString());
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
}
