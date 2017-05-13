package co.edu.udea.compumovil.g01_20171.lab4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.udea.compumovil.g01_20171.lab4.Modelo.Evento;


/**
 * Created by Alejandro on 14/03/2017.
 */

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.ViewHolder> {

    private ArrayList<Evento> eventosList;

    public EventoAdapter(ArrayList<Evento> eventosList) {
        this.eventosList = eventosList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_nombre;
        private TextView tv_informacion;
        private ImageView iv_foto;
        private RatingBar rb_puntuacion;


        public ViewHolder(View itemView)
        {
            super(itemView);

            tv_nombre = (TextView) itemView.findViewById(R.id.tv_nombre_item_evento);
            tv_informacion = (TextView) itemView.findViewById(R.id.tv_informacion_item_evento);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto_item_evento);
            rb_puntuacion = (RatingBar) itemView.findViewById(R.id.rb_puntuacion_item_evento);
        }
    }

    @Override
    public EventoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_evento, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventoAdapter.ViewHolder holder, int position) {
        Evento e = eventosList.get(position);
        String bitmapdata = e.getFoto();
        holder.tv_nombre.setText(e.getNombre());
        holder.tv_informacion.setText(e.getInformacion());
        //holder.iv_foto.setImageBitmap(BitmapFactory.decodeByteArray(bitmapdata,0,bitmapdata.length));
        holder.rb_puntuacion.setRating((float)e.getPuntuacion());

    }

    @Override
    public int getItemCount() {
        return eventosList.size();
    }
}
