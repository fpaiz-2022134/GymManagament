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
public class Entrenador {
    
    private int id;
    private String nombre;
    private String especialidad;
    private ArrayList<Miembro> miembrosAsignados;
    
    
    public Entrenador (int id, String nombre, String especialidad){
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.miembrosAsignados = new ArrayList<>();
    }
    
    
    //Managing members
    
    public void asignarMiembro(Miembro miembro){
        if(miembrosAsignados.contains(miembro)){
            miembrosAsignados.add(miembro);
        }
    }
    
    
    public void removerMiembro(Miembro miembro){
        miembrosAsignados.remove(miembro);
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public ArrayList<Miembro> getMiembrosAsignados() {
        return miembrosAsignados;
    }

    public int getCantidadMiembros(){
        return miembrosAsignados.size();
    }
    
    @Override
    public String toString(){
        return nombre + " (" + especialidad + ") - " + miembrosAsignados.size() + " alumnos";
    }
    
}
