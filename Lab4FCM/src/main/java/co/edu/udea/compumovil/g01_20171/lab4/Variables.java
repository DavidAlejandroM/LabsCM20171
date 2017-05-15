package co.edu.udea.compumovil.g01_20171.lab4;

/**
 * Created by alejandro on 15/05/17.
 */

public class Variables {

    /*private static ClassicSingleton instance = null;
    protected ClassicSingleton() {
        // Exists only to defeat instantiation.
    }
    public static ClassicSingleton getInstance() {
        if(instance == null) {
            instance = new ClassicSingleton();
        }
        return instance;
    }*/

    private int contadorEventos;
    private static Variables instance;

    private Variables()
    {
        contadorEventos = 0;
    }

    public static Variables getInstance()
    {
        if (instance != null)
        {
            return instance;
        }
        else
        {
            instance = new Variables();
            return instance;
        }
    }

    public int getContadorEventos() {
        return contadorEventos;
    }

    public void setContadorEventos(int contadorEventos) {
        this.contadorEventos = contadorEventos;
    }
}
