package co.edu.udea.compumovil.gr01_20171.lab2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Evento;
import co.edu.udea.compumovil.gr01_20171.lab2.Modelo.Usuario;

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

     public ContentValues generarContentValuesUsuario(String usuario, String password, String email, int edad, byte[] foto, int estado)
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

    public ContentValues generarContentValuesEvento(String nombre,byte[] foto, String responsable,
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

    public boolean insertarUsuario(String usuario, String password, String email, int edad, byte[] foto, int estado)
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

    public boolean insertarEvento(String nombre,byte[] foto, String responsable,
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
                DbContract.DbEntry.TN_EVENTOS,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public boolean updateEstadoUsuario(String usuario,int estado)
    {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(DbContract.DbEntry.CN_US_ESTADO, estado);

        // Which row to update, based on the title
        String selection = DbContract.DbEntry.CN_US_USER + " LIKE ?";
        String[] selectionArgs = { usuario };

        int count = db.update(
                DbContract.DbEntry.TN_USUARIOS,
                values,
                selection,
                selectionArgs);
        if (count == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor inicioSesionCursor(String usuario, String contraseña)
    {
        Cursor cursor = null;

        String query = "SELECT * FROM " + DbContract.DbEntry.TN_USUARIOS + " WHERE " +
                DbContract.DbEntry.CN_US_USER + " = '"+usuario +"' AND " + DbContract.DbEntry.CN_US_PASSWORD +
                " = '"+contraseña+"'";
        cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Usuario inicioSesionUsuario(String usuario, String contraseña)
    {
        Usuario u = null;
        Cursor cursor = inicioSesionCursor(usuario,contraseña);

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            u = new Usuario(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getBlob(5),
                    cursor.getInt(6)
            );
            u.setId(cursor.getInt(0));
        }

        return u;
    }

    public Usuario inicioSesionUsuarioAutomatico()
    {
        Usuario u = null;
        String query = "SELECT * FROM " + DbContract.DbEntry.TN_USUARIOS + " WHERE " + DbContract.DbEntry.CN_US_ESTADO + " = 1";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            u = new Usuario(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getBlob(5),
                    cursor.getInt(6)
            );
            u.setId(cursor.getInt(0));
        }

        return u;
    }

    public Usuario obtenerUSuarioById(int id)
    {
        Usuario u = null;
        String query = "SELECT * FROM " + DbContract.DbEntry.TN_USUARIOS + " WHERE " + DbContract.DbEntry._ID + " = "+String.valueOf(id);
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            u = new Usuario(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getBlob(5),
                    cursor.getInt(6)
            );
            u.setId(cursor.getInt(0));
        }

        return u;
    }

    /*private Usuario getUsuarioFromCursorUsuario(Cursor cursor)
    {
        Usuario u = null;

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            u = new Usuario(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getInt(6)
            );
        }
        return u;
    }*/

    public ArrayList<Evento> obtenerTodosEventos()
    {
        ArrayList<Evento> arrayList = new ArrayList<>();
        Evento evento = null;
        Cursor cursor = selectTodosEventos();
        cursor.moveToFirst();
        int count = cursor.getCount();
        int i = 0;
        while (i<count)
        {
            evento = new Evento(
                    cursor.getString(1),
                    cursor.getBlob(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getFloat(6),
                    cursor.getFloat(7),
                    cursor.getString(8)
            );
            arrayList.add(evento);
            cursor.moveToNext();
            i++;
        }

        return arrayList;
    }

    public Evento getEventoFromNombreFecha(String nombre, String fecha)
    {
        Evento evento = null;
        String query = "SELECT * FROM " + DbContract.DbEntry.TN_EVENTOS + " WHERE " +
                DbContract.DbEntry.CN_EV_NOMBRE + " = '"+ nombre +"'"/*+ " AND "+
                DbContract.DbEntry.CN_EV_FECHA + " = " + fecha*/;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.getCount() == 1)
        {
            cursor.moveToFirst();
            evento = new Evento(
                    cursor.getString(1),
                    cursor.getBlob(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getFloat(6),
                    cursor.getFloat(7),
                    cursor.getString(8)
            );
        }

        return evento;
    }

}
