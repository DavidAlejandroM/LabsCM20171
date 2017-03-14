package co.edu.udea.compumovil.gr01_20171.lab2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

     public ContentValues generarContentValuesUsuario(String usuario, String password, String email, int edad, String foto, int estado)
     {
         ContentValues valores = new ContentValues();
         valores.put(DbContract.DbEntry.CN_US_USER, usuario);
         valores.put(DbContract.DbEntry.CN_US_PASSWORD, password);
         valores.put(DbContract.DbEntry.CN_US_EMAIL, email);
         valores.put(DbContract.DbEntry.CN_US_EDAD, edad);
         valores.put(DbContract.DbEntry.CN_US_FOTO, foto);
         valores.put(DbContract.DbEntry.CN_US_ESTADO, estado);

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

    public boolean insertarUsuario(String usuario, String password, String email, int edad, String foto, int estado)
    {
        ContentValues valores = generarContentValuesUsuario(usuario, password, email, edad, foto, estado);
        long row = db.insert(DbContract.DbEntry.TN_USUARIOS,null,valores);

        if (row != -1) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean insertarEvento(String nombre,String foto, String responsable,
                                  int puntuacion, String fecha, float latitud,
                                  float longitud, String informacion)
    {
        ContentValues valores = generarContentValuesEvento(nombre, foto, responsable, puntuacion, fecha, latitud,longitud, informacion);
        long row = db.insert(DbContract.DbEntry.TN_EVENTOS,null,valores);

        if (row != -1) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor selectTodosUsuarios()
    {
        return db.query(
                DbContract.DbEntry.TN_USUARIOS,// Nombre de la tabla
                null,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                null  // Cláusula ORDER BY
                );
    }

    public Cursor selectTodosEventos()
    {
        return db.query(
                DbContract.DbEntry.TN_USUARIOS,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    

}
