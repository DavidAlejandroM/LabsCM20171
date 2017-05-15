package co.edu.udea.compumovil.g01_20171.lab4.Widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.edu.udea.compumovil.g01_20171.lab4.Modelo.Evento;
import co.edu.udea.compumovil.g01_20171.lab4.R;
import co.edu.udea.compumovil.g01_20171.lab4.Variables;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aylar-HP on 10/09/2015.
 */
public class UpdateWidgetService extends Service {
    private static final String LOG = "widget.example";
    final int[] value = {0};
    @Override
    public void onStart(Intent intent, int startId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());
        Variables var = Variables.getInstance();

        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);

        for (int widgetId : allWidgetIds) {

            int number = (new Random().nextInt(100));

            final RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.my_widget);
            Log.w("WidgetExample", String.valueOf(number));

            DatabaseReference db = FirebaseDatabase.getInstance().getReference("eventos");

            Log.d("firebaseReference: ", db.getDatabase().toString());

            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Variables var = Variables.getInstance();
                    Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                    var.setContadorEventos((int) dataSnapshot.getChildrenCount());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }


            });

            remoteViews.setTextViewText(R.id.update,
                    "Numero de Eventos: "+String.valueOf(var.getContadorEventos()));

            Intent clickIntent = new Intent(this.getApplicationContext(),
                    MyWidget.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        super.onStart(intent, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

}

