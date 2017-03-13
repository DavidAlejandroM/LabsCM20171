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
        public static final String TN_USUARIOS = "tbl_usuarios";

    }


}
