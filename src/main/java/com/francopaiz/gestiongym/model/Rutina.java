/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.model;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Rutina {
    private int id;
    private String nombre;
    private String tipo;
    private ArrayList<Ejercicio> ejercicios;
    private boolean activa;
    private int practicantes;

    public Rutina(int id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.ejercicios = new ArrayList<>();
        this.activa = true;
        this.practicantes = 0;
    }
    
    
    public void agregarEjercicio(Ejercicio ejercicio){
       ejercicios.add(ejercicio);
    }
    
    public void eliminarEjercicio(Ejercicio ejercicio){
        ejercicios.remove(ejercicio);
    }
    
    public void incrementarPracticantes(){
        practicantes++;
    }
    
    public void decrementarPracticantes(){
        if(practicantes >0){
            practicantes--;
        }
    }
    
    
    
    //Getters and setters

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getPracticantes() {
        return practicantes;
    }

    public void setPracticantes(int practicantes) {
        this.practicantes = practicantes;
    }
    
    @Override public String toString(){
        return nombre + " (" + tipo + ") - " + practicantes + " practicantes";
    }
    
    
}
