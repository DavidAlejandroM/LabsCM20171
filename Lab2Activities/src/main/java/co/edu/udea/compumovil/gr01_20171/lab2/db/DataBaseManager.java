package co.edu.udea.compumovil.gr01_20171.lab2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alejandro on 13/03/17.
 */

public class DataBaseManager {

    private DbHelper helper;
    private SQLiteDatabase db;

     public DataBaseManager(Context context)
     {
         helper = new DbHelper(context);
         db = helper.getWritableDatabase();
     }

     public ContentValues generarContentValuesUsuario(String usuario, String password, String email, int edad, String foto
     )
     {
         ContentValues valores = new ContentValues();
         valores.put(DbContract.DbEntry.CN_US_USER, usuario);
         valores.put(DbContract.DbEntry.CN_US_PASSWORD, password);
         valores.put(DbContract.DbEntry.CN_US_EMAIL, email);
         valores.put(DbContract.DbEntry.CN_US_EDAD, edad);
         valores.put(DbContract.DbEntry.CN_US_FOTO, foto);

         return valores;
     }

    public ContentValues generarContentValuesEvento(String nombre,String foto, String responsable,
                                                    int puntuacion, String fecha, float latitud,
                                                    float longitud, String informacion)
    {
        ContentValues valores = new ContentValues();
        valores.put(DbContract.DbEntry.CN_EV_NOMBRE, nombre);
        valores.put(DbContract.DbEntry.CN_EV_FOTO, foto);
        valores.put(DbContract.DbEntry.CN_EV_RESPOSABLE, responsable);
        valores.put(DbContract.DbEntry.CN_EV_PUNTUACION, puntuacion);
        valores.put(DbContract.DbEntry.CN_EV_FECHA, fecha);
        valores.put(DbContract.DbEntry.CN_EV_LATITUD, latitud);
        valores.put(DbContract.DbEntry.CN_EV_LONGITUD, longitud);
        valores.put(DbContract.DbEntry.CN_EV_INFORMACION, informacion);

        return valores;
    }

}
