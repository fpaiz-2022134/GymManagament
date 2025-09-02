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
public class GestorGym {

    private ArrayList<Miembro> miembros;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Rutina> rutinas;
    private int contadorMiembros = 1;
    private int contadorEntrenadores = 1;
    private int contadorRutinas = 1;

    private ArrayList<Ejercicio> ejercicios; // Lista global de ejercicios
    private int contadorEjercicios = 1;

    public GestorGym() {
        miembros = new ArrayList<>();
        entrenadores = new ArrayList<>();
        rutinas = new ArrayList<>();
        ejercicios = new ArrayList<>();
    }

    //EXERCISES
    public boolean agregarEjercicio(String nombre, int series, int repeticiones, String descripcion) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            Ejercicio nuevoEjercicio = new Ejercicio(contadorEjercicios++, nombre, series, repeticiones, descripcion);
            ejercicios.add(nuevoEjercicio);
            return true;
        }
        return false;
    }

    public Ejercicio buscarEjercicioPorId(int id) {
        return ejercicios.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public boolean agregarEjercicioARutina(int idRutina, int idEjercicio) {
        Rutina rutina = buscarRutinaPorId(idRutina);
        Ejercicio ejercicio = buscarEjercicioPorId(idEjercicio);

        if (rutina != null && ejercicio != null) {
            rutina.agregarEjercicio(ejercicio);
            return true;
        }
        return false;
    }

    public boolean eliminarEjercicioDeRutina(int idRutina, int idEjercicio) {
        Rutina rutina = buscarRutinaPorId(idRutina);
        Ejercicio ejercicio = buscarEjercicioPorId(idEjercicio);

        if (rutina != null && ejercicio != null) {
            rutina.eliminarEjercicio(ejercicio);
            return true;
        }
        return false;
    }

    public boolean toggleEstadoRutina(int idRutina) {
        Rutina rutina = buscarRutinaPorId(idRutina);
        if (rutina != null) {
            rutina.setActiva(!rutina.isActiva());
            return true;
        }
        return false;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    //MEMBERS
    public boolean agregarMiembro(String nombre, String tipoMembresia) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            Miembro nuevoMiembro = new Miembro(contadorMiembros++, nombre, tipoMembresia);
            miembros.add(nuevoMiembro);
            return true;
        }
        return false;
    }

    public boolean eliminarMiembro(int id) {
        Miembro miembro = buscarMiembroPorId(id);

        if (miembro != null) {
            //Cleaning the relations
            if (miembro.getEntrenadorAsignado() != null) {
                miembro.getEntrenadorAsignado().removerMiembro(miembro);
            }

            for (Rutina rutina : miembro.getRutinas()) {
                rutina.decrementarPracticantes();
            }

            return miembros.remove(miembro);
        }
        return false;
    }

    public Miembro buscarMiembroPorId(int id) {
        return miembros.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    //COACHES
    public boolean agregarEntrenador(String nombre, String especialidad) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            Entrenador nuevoEntrenador = new Entrenador(contadorEntrenadores++, nombre, especialidad);
            entrenadores.add(nuevoEntrenador);
            return true;
        }
        return false;
    }

    public Entrenador buscarEntrenadorPorId(int id) {
        return entrenadores.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    //ROUTINES
    public boolean agregarRutina(String nombre, String tipo) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            Rutina nuevaRutina = new Rutina(contadorRutinas++, nombre, tipo);
            rutinas.add(nuevaRutina);
            return true;
        }
        return false;
    }

    public Rutina buscarRutinaPorId(int id) {
        return rutinas.stream().filter(r -> r.getId() == id).findFirst().orElse(null);

    }

    public boolean asignarRutinaAMiembro(int idMiembro, int idRutina) {
        Miembro miembro = buscarMiembroPorId(idMiembro);
        Rutina rutina = buscarRutinaPorId(idRutina);

        if (miembro != null && rutina != null) {
            miembro.agregarRutina(rutina);
            return true;
        }
        return false;
    }

    public boolean asignarEntrenadorAMiembro(int idMiembro, int idEntrenador) {
        Miembro miembro = buscarMiembroPorId(idMiembro);
        Entrenador entrenador = buscarEntrenadorPorId(idEntrenador);

        if (miembro != null && entrenador != null) {
            miembro.asignarEntrenador(entrenador);
            return true;
        }
        return false;
    }

    //Methods for the reports
    public Rutina getRutinaMasPopular() {
        return rutinas.stream()
                .max((r1, r2) -> Integer.compare(r1.getPracticantes(), r2.getPracticantes()))
                .orElse(null);
    }

    public int getNumeroRutinasActivas() {
        return (int) rutinas.stream().filter(Rutina::isActiva).count();
    }

    public Entrenador getEntrenadorConMasAlumnos() {
        return entrenadores.stream()
                .max((e1, e2) -> Integer.compare(e1.getCantidadMiembros(), e2.getCantidadMiembros()))
                .orElse(null);
    }

    // Getters
    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public ArrayList<Rutina> getRutinas() {
        return rutinas;
    }

}
