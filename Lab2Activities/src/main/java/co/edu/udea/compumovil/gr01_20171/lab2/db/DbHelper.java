package co.edu.udea.compumovil.gr01_20171.lab2.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import co.edu.udea.compumovil.gr01_20171.lab2.db.DbContract;


/**
 * Created by alejandro on 13/03/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lab2.db";
    private SQLiteDatabase db;
    private String DB_PATH = Environment.DIRECTORY_DOCUMENTS;

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        String tablaUsuarios = "CREATE TABLE " + DbContract.DbEntry.TN_USUARIOS + " ("
                + DbContract.DbEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DbContract.DbEntry.CN_US_USER + " TEXT NOT NULL,"
                + DbContract.DbEntry.CN_US_PASSWORD + " TEXT NOT NULL,"
                + DbContract.DbEntry.CN_US_EMAIL + " TEXT NOT NULL,"
                + DbContract.DbEntry.CN_US_EDAD + " INTEGER NOT NULL,"
                + DbContract.DbEntry.CN_US_FOTO + " TEXT NOT NULL,"
                + DbContract.DbEntry.CN_US_ESTADO + " INTEGER NOT NULL"
                + ");";

        String tablaEventos = "CREATE TABLE " + DbContract.DbEntry.TN_EVENTOS + " ("
                + DbContract.DbEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DbContract.DbEntry.CN_EV_NOMBRE + " TEXT NOT NULL,"
                + DbContract.DbEntry.CN_EV_FOTO + " BLOB,"
                + DbContract.DbEntry.CN_EV_RESPOSABLE + " TEXT NOT NULL,"
                + DbContract.DbEntry.CN_EV_PUNTUACION + " INTEGER NOT NULL,"
                + DbContract.DbEntry.CN_EV_FECHA + " INTEGER NOT NULL,"
                + DbContract.DbEntry.CN_EV_LATITUD + " FLOAT NOT NULL,"
                + DbContract.DbEntry.CN_EV_LONGITUD + " FLOAT NOT NULL,"
                + DbContract.DbEntry.CN_EV_INFORMACION + " TEXT NOT NULL"
                + ")";

        db.execSQL(tablaUsuarios);

        db.execSQL(tablaEventos);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor select(String query){
        Cursor c = db.rawQuery(query,null);
        return c;
    }
}
