package co.edu.udea.compumovil.gr01_20171.lab2.db;

import android.provider.BaseColumns;

/**
 * Created by Alejandro on 11/03/2017.
 */

public final class DbContract {
    private DbContract() {
    }

    public static class DbEntry implements BaseColumns
    {
        public static final String TN_USUARIOS = "tbl_users";
        public static final String CN_US_USER = "u_user";
        public static final String CN_US_PASSWORD = "u_password";
        public static final String CN_US_EMAIL = "u_email";
        public static final String CN_US_EDAD = "u_edad";
        public static final String CN_US_FOTO = "u_foto";
        public static final String CN_US_ESTADO = "u_estado";


        public static final String TN_EVENTOS = "tbl_eventos";
        public static final String CN_EV_NOMBRE = "e_nombre";
        public static final String CN_EV_FOTO = "e_foto";
        public static final String CN_EV_RESPOSABLE = "e_responsable";
        public static final String CN_EV_PUNTUACION = "e_puntuacion";
        public static final String CN_EV_FECHA = "e_fecha";
        public static final String CN_EV_LATITUD = "e_latitud";
        public static final String CN_EV_LONGITUD = "e_longitud";
        public static final String CN_EV_INFORMACION = "e_informacion";

    }


}
