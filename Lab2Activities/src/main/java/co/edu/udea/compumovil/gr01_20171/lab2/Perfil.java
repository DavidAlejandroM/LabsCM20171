package co.edu.udea.compumovil.gr01_20171.lab2;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;
import co.edu.udea.compumovil.gr01_20171.lab2.db.DataBaseManager;


public class Perfil extends Fragment {

    private Usuario usuario;
    private DataBaseManager manager;

    private EditText et_nombre;
    private EditText et_corre;
    private EditText et_edad;

    private ImageView iv_foto;



    public Perfil() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        manager = new DataBaseManager(getActivity().getApplicationContext());
        Bundle args = getArguments();
        int id = args.getInt("id");
        usuario = manager.obtenerUSuarioById(id);



        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_perfil, container, false);

        et_nombre = (EditText) view.findViewById(R.id.et_perfil_nombre);
        et_corre = (EditText) view.findViewById(R.id.et_perfil_correo);
        et_edad = (EditText) view.findViewById(R.id.et_perfil_edad);
        iv_foto = (ImageView) view.findViewById(R.id.iv_perfil_foto);

        et_nombre.setText(usuario.getUsuario());
        et_corre.setText(usuario.getEmail());
        et_edad.setText(String.valueOf(usuario.getEdad()));
        iv_foto.setImageBitmap(BitmapFactory.decodeByteArray(usuario.getFoto(),0,usuario.getFoto().length));
        // Inflate the layout for this fragment
        return view;

    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

   /* public static final int ID = 4;
    static String nombreUsuario;
    TextView nom, email, edad;

    public static Perfil newInstance(String nombre) {
        nombreUsuario = nombre;
        return new Perfil();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }*/
}
