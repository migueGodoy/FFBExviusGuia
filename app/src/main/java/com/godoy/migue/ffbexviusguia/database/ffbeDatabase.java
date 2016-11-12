package com.godoy.migue.ffbexviusguia.database;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.godoy.migue.ffbexviusguia.clasesBase.Unidad;

import java.util.ArrayList;

/**
 * Created by mikle on 19/10/2016.
 */

public class ffbeDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FFBE.db";

    public static final int DATABASE_VERSION = 1;

    private final Context _context;

    //Sentencia SQL para crear la tabla de Unidades
    String sqlCreateTablaUnidades = "CREATE TABLE Unidades (codigo INTEGER primary key autoincrement," +
            "nombre TEXT, imagen TEXT, estrellas INTEGER, VIT INTEGER, ATQ INTEGER, MAG INTEGER," +
            "PM INTEGER, DEF INTEGER, ESP INTEGER, AttackHits INTEGER, DropCheck INTEGER," +
            "resFuego INTEGER, resHielo INTEGER, resElectro INTEGER, resAqua INTEGER," +
            "resViento INTEGER, resPiedra INTEGER, resLuz INTEGER, resOscuridad INTEGER," +
            "resVeneno INTEGER, resCeguera INTEGER, resSueno INTEGER, resSilencio INTEGER," +
            "resParalisis INTEGER, resConfusion INTEGER, resVirus INTEGER, resPetrificar INTEGER)";

    public ffbeDatabase(Context contexto, int version){
        super(contexto, DATABASE_NAME, null, version);
        _context = contexto;
    }

    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreateTablaUnidades);

        //Crear Rain
        db.execSQL("INSERT INTO Unidades (nombre, imagen, estrellas, VIT, ATQ, MAG, PM, DEF, ESP, AttackHits," +
                "DropCheck, resFuego, resHielo, resElectro, resAqua, resViento, resPiedra, resLuz, resOscuridad," +
                "resVeneno, resCeguera, resSueno, resSilencio, resParalisis, resConfusion, resVirus, resPetrificar) " +
                "VALUES ('Rain', 'ffbe_rain', 5, 2488, 89, 86, 97, 88, 80, 2, 6, 0, 0, 0, 0, 0, 0, 0, 0, " +
                "0, 0, 0, 0, 0, 0, 0, 0)");

        //Crear Lasswell
        db.execSQL("INSERT INTO Unidades (nombre, imagen, estrellas, VIT, ATQ, MAG, PM, DEF, ESP, AttackHits," +
                "DropCheck, resFuego, resHielo, resElectro, resAqua, resViento, resPiedra, resLuz, resOscuridad," +
                "resVeneno, resCeguera, resSueno, resSilencio, resParalisis, resConfusion, resVirus, resPetrificar) " +
                "VALUES ('Lasswell', 'ffbe_lasswell', 5, 2349, 96, 89, 100, 82, 74, 1, 12, 0, 0, 0, 0, 0, 0, 0, 0, " +
                "0, 0, 0, 0, 0, 0, 0, 0)");

        //Crear Fina
        db.execSQL("INSERT INTO Unidades (nombre, imagen, estrellas, VIT, ATQ, MAG, PM, DEF, ESP, AttackHits," +
                "DropCheck, resFuego, resHielo, resElectro, resAqua, resViento, resPiedra, resLuz, resOscuridad," +
                "resVeneno, resCeguera, resSueno, resSilencio, resParalisis, resConfusion, resVirus, resPetrificar) " +
                "VALUES ('Fina', 'ffbe_fina', 5, 1867, 79, 93, 131, 79, 101, 1, 12, 0, 0, 0, 0, 0, 0, 0, 0, " +
                "0, 0, 0, 0, 0, 0, 0, 0)");

        //Crear Lid
        db.execSQL("INSERT INTO Unidades (nombre, imagen, estrellas, VIT, ATQ, MAG, PM, DEF, ESP, AttackHits," +
                "DropCheck, resFuego, resHielo, resElectro, resAqua, resViento, resPiedra, resLuz, resOscuridad," +
                "resVeneno, resCeguera, resSueno, resSilencio, resParalisis, resConfusion, resVirus, resPetrificar) " +
                "VALUES ('Lid', 'ffbe_lid', 5, 2186, 85, 84, 106, 80, 76, 1, 12, 0, 0, 0, 0, 0, 0, 0, 0, " +
                "0, 0, 0, 0, 0, 0, 0, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se crea la nueva versión de la tabla
        //db.execSQL(sqlCreateTablaUnidades);
    }

    private void insertarUnidad(){

    }

    public ArrayList<Unidad> obtenerUnidades(){
        ArrayList<Unidad> listaUnidades = new ArrayList<Unidad>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sqlObtenerUnidades = "Select nombre, imagen, estrellas from Unidades";

        Cursor cursor = db.rawQuery(sqlObtenerUnidades, null);
        if(cursor.moveToFirst()){
            do{
                String nombreUnidad = cursor.getString(cursor.getColumnIndex("nombre"));
                int imagenUnidad = getResourceId(cursor.getString(cursor.getColumnIndex("imagen")));

                String estrellas = cursor.getString(cursor.getColumnIndex("estrellas"));
                switch(estrellas){
                    case "1":
                        estrellas = "una_estrella";
                        break;
                    case "2":
                        estrellas = "dos_estrellas";
                        break;
                    case "3":
                        estrellas = "tres_estrellas";
                        break;
                    case "4":
                        estrellas = "cuatro_estrellas";
                        break;
                    case "5":
                        estrellas = "cinco_estrellas";
                        break;
                    case "6":
                        estrellas = "seis_estrellas";
                        break;
                }

                int estrellasUnidad = getResourceId(estrellas);
                Unidad unidad = new Unidad(nombreUnidad, imagenUnidad, estrellasUnidad);
                listaUnidades.add(unidad);
            }while(cursor.moveToNext());
        }

        return listaUnidades;
    }

    private int getResourceId(String name){
        Resources resources = _context.getResources();
        int result = resources.getIdentifier(name, "drawable",
                _context.getPackageName());
        return result;
    }
}
