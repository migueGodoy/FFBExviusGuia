package com.godoy.migue.ffbexviusguia.clasesBase;

/**
 * Created by mikle on 10/10/2016.
 */

public class Unidad {
    private String _nombre;
    private int _imagen;
    private int _rangoEstrellas;

    public Unidad(String nombre, int imagen, int rangoEstrellas){
        _nombre = nombre;
        _imagen = imagen;
        _rangoEstrellas = rangoEstrellas;
    }

    public String get_nombre(){
        return _nombre;
    }

    public int get_imagen(){
        return _imagen;
    }

    public int get_rangoEstrellas(){
        return _rangoEstrellas;
    }
}
