/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.model;

/**
 *
 * @author Dell
 */
public class Ejercicio {
    private int id;
    private String nombre;
    private int series;
    private int repeticiones;
    private String descripcion;
    
    
    public Ejercicio(int id, String nombre, int series, int repeticiones, String descripcion){
        this.id = id;
        this.nombre =  nombre;
        this.series = series;
        this.repeticiones = repeticiones;
        this.descripcion = descripcion;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        return nombre + "(" + series + "series," + repeticiones + "reps)";
    }
    
    
    
}
