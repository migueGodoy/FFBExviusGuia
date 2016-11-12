package com.godoy.migue.ffbexviusguia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.godoy.migue.ffbexviusguia.R;
import com.godoy.migue.ffbexviusguia.clasesBase.Unidad;

import java.util.ArrayList;

/**
 * Created by mikle on 11/10/2016.
 */

public class AdaptadorUnidades extends ArrayAdapter<Unidad> {

    private ArrayList<Unidad> _unidades;

    public AdaptadorUnidades(Context context, ArrayList<Unidad> unidades) {
        super(context, R.layout.fila_lista_unidades, unidades);
        _unidades = unidades;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.fila_lista_unidades, null);

        ImageView imagenUnidad = (ImageView)item.findViewById(R.id.imagenUnidad);
        imagenUnidad.setImageResource(_unidades.get(position).get_imagen());

        TextView nombreUnidad = (TextView)item.findViewById(R.id.nombreUnidad);
        nombreUnidad.setText(_unidades.get(position).get_nombre());

        ImageView estrellasUnidad = (ImageView)item.findViewById(R.id.estrellas);
        estrellasUnidad.setImageResource(_unidades.get(position).get_rangoEstrellas());

        return(item);
    }
}
