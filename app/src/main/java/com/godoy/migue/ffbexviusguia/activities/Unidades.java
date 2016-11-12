package com.godoy.migue.ffbexviusguia.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.godoy.migue.ffbexviusguia.R;
import com.godoy.migue.ffbexviusguia.adapters.AdaptadorUnidades;
import com.godoy.migue.ffbexviusguia.clasesBase.Unidad;
import com.godoy.migue.ffbexviusguia.database.ffbeDatabase;

import java.util.ArrayList;

public class Unidades extends AppCompatActivity {
    private ListView listaUnidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unidades);

        listaUnidades = (ListView) findViewById(R.id.listaUnidades);

        /*String arrayNombreUnidades[] = new String[]{"Rain", "Lasswell"};//, "Fina", "La nube", "Garland"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arrayNombreUnidades);

        listaUnidades.setAdapter(adaptador);*/

        /*
        Creación manual de unidades
        Unidad rain = new Unidad("Rain", getResourceId("ffbe_rain"), getResourceId("cinco_estrellas"));
        Unidad lasswell = new Unidad("Lasswell", getResourceId("ffbe_lasswell"), getResourceId("cinco_estrellas"));

        Unidad[] unidades = new Unidad[]{rain, lasswell};
        */

        ffbeDatabase db = new ffbeDatabase(getApplicationContext(), 1);
        ArrayList<Unidad> unidades = db.obtenerUnidades();

        AdaptadorUnidades adaptadorUnidades = new AdaptadorUnidades(getApplicationContext(), unidades);

        listaUnidades.setAdapter(adaptadorUnidades);

        listaUnidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                String nombreUnidadSeleccionada =
                        ((Unidad)a.getItemAtPosition(position)).get_nombre();

                Intent i = new Intent(getApplicationContext(), detallesUnidad.class);

                Bundle bundle = new Bundle();
                bundle.putString("nombre", nombreUnidadSeleccionada);
                i.putExtras(bundle);

                startActivity(i);
            }
        });
    }


}
