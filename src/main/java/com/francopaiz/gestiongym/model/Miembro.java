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
public class Miembro {
    private int id;
    private String nombre;
    private String tipoMembresia;
    private ArrayList<Rutina> rutinas;
    private Entrenador entrenadorAsignado;

    public Miembro(int id, String nombre, String tipoMembresia) {
        this.id = id;
        this.nombre = nombre;
        this.tipoMembresia = tipoMembresia;
        this.rutinas = new ArrayList<>();
    }
    
    
    public void agregarRutina(Rutina rutina){
        if(!rutinas.contains(rutina)){
            rutinas.add(rutina);
            rutina.incrementarPracticantes();
        }
    }
    
    
    public void eliminarRutina(Rutina rutina){
        if(rutinas.remove(rutina)){
            rutina.decrementarPracticantes();
        }
    }
    
    public void asignarEntrenador(Entrenador entrenador){
        if(this.entrenadorAsignado != null){
            this.entrenadorAsignado.removerMiembro(this);
        }
        
        this.entrenadorAsignado = entrenador;
        
        if(entrenador != null){
            entrenador.asignarMiembro(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

    public Entrenador getEntrenadorAsignado() {
        return entrenadorAsignado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
    
    @Override
    public String toString(){
        return nombre + " (" + tipoMembresia + ")";
    }
    
    
}
