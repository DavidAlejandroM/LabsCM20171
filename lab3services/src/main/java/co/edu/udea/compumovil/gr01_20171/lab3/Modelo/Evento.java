package co.edu.udea.compumovil.gr01_20171.lab3.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alejandro on 13/03/2017.
 */

public class Evento implements Parcelable {

    /*
    *

     valores.put(DbContract.DbEntry.CN_EV_NOMBRE, nombre);
        valores.put(DbContract.DbEntry.CN_EV_FOTO, foto);
        valores.put(DbContract.DbEntry.CN_EV_RESPOSABLE, responsable);
        valores.put(DbContract.DbEntry.CN_EV_PUNTUACION, puntuacion);
        valores.put(DbContract.DbEntry.CN_EV_FECHA, fecha);
        valores.put(DbContract.DbEntry.CN_EV_LATITUD, latitud);
        valores.put(DbContract.DbEntry.CN_EV_LONGITUD, longitud);
        valores.put(DbContract.DbEntry.CN_EV_INFORMACION, informacion);
    * */

    private String nombre;
    private byte[] foto;
    private String responsable;
    private int puntuacion;
    private String fecha;
    private float latitud;
    private float longitud;
    private String informacion;

    public Evento(String nombre, byte[] foto, String responsable, int puntuacion, String fecha, float latitud, float longitud, String informacion) {
        this.nombre = nombre;
        this.foto = foto;
        this.responsable = responsable;
        this.puntuacion = puntuacion;
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.informacion = informacion;
    }

    protected Evento(Parcel in) {
        nombre = in.readString();
        foto = in.createByteArray();
        responsable = in.readString();
        puntuacion = in.readInt();
        fecha = in.readString();
        latitud = in.readFloat();
        longitud = in.readFloat();
        informacion = in.readString();
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeByteArray(foto);
        dest.writeString(responsable);
        dest.writeInt(puntuacion);
        dest.writeString(fecha);
        dest.writeFloat(latitud);
        dest.writeFloat(longitud);
        dest.writeString(informacion);
    }
}
