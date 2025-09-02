/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.controller;

import com.francopaiz.gestiongym.model.Ejercicio;
import com.francopaiz.gestiongym.model.GestorGym;
import com.francopaiz.gestiongym.model.Rutina;
import com.francopaiz.gestiongym.view.PanelRutinas;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ControladorRutinas {
    private GestorGym gestorGym;
    private PanelRutinas panelRutinas;

    public ControladorRutinas(GestorGym gestor) {
        this.gestorGym = gestor;
    }

    public void setPanelRutinas(PanelRutinas panel) {
        this.panelRutinas = panel;
    }

    public void crearRutina() {
        String nombre = panelRutinas.getNombreRutina();
        String tipo = panelRutinas.getTipoRutina();

        if (validarDatos(nombre)) {
            if (gestorGym.agregarRutina(nombre, tipo)) {
                panelRutinas.limpiarFormularioRutina();
                actualizarVista();
                JOptionPane.showMessageDialog(panelRutinas, "Rutina creada exitosamente");
            } else {
                JOptionPane.showMessageDialog(panelRutinas, "Error al crear rutina",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validarDatos(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelRutinas, "El nombre es obligatorio",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void actualizarVista() {
        if (panelRutinas != null) {
            panelRutinas.actualizarTablaRutinas(gestorGym.getRutinas());
        }
    }
    
    
    public void agregarEjercicioARutina() {
    int idRutina = panelRutinas.getRutinaSeleccionadaId();
    if (idRutina == -1) {
        JOptionPane.showMessageDialog(panelRutinas, "Seleccione una rutina primero");
        return;
    }

    String nombre = panelRutinas.getNombreEjercicio();
    String seriesStr = panelRutinas.getSeries();
    String repeticionesStr = panelRutinas.getRepeticiones();
    String descripcion = panelRutinas.getDescripcion();

    if (validarDatosEjercicio(nombre, seriesStr, repeticionesStr)) {
        try {
            int series = Integer.parseInt(seriesStr);
            int repeticiones = Integer.parseInt(repeticionesStr);
            
            if (gestorGym.agregarEjercicio(nombre, series, repeticiones, descripcion)) {
                ArrayList<Ejercicio> ejercicios = gestorGym.getEjercicios();
                int idEjercicio = ejercicios.get(ejercicios.size() - 1).getId();
                
                if (gestorGym.agregarEjercicioARutina(idRutina, idEjercicio)) {
                    panelRutinas.limpiarFormularioEjercicio();
                    cargarEjerciciosDeRutina(idRutina);
                    actualizarVista();
                    JOptionPane.showMessageDialog(panelRutinas, "Ejercicio agregado exitosamente");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panelRutinas, 
                "Series y repeticiones deben ser números válidos",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
        }
    }
}

public void eliminarEjercicioDeRutina() {
    int idRutina = panelRutinas.getRutinaSeleccionadaId();
    int idEjercicio = panelRutinas.getEjercicioSeleccionadoId();
    
    if (idRutina == -1 || idEjercicio == -1) {
        JOptionPane.showMessageDialog(panelRutinas, "Seleccione una rutina y un ejercicio");
        return;
    }

    int confirmacion = JOptionPane.showConfirmDialog(panelRutinas,
        "¿Está seguro de eliminar este ejercicio de la rutina?", 
        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        if (gestorGym.eliminarEjercicioDeRutina(idRutina, idEjercicio)) {
            cargarEjerciciosDeRutina(idRutina);
            actualizarVista();
            JOptionPane.showMessageDialog(panelRutinas, "Ejercicio eliminado exitosamente");
        }
    }
}

public void toggleEstadoRutina() {
    int idRutina = panelRutinas.getRutinaSeleccionadaId();
    if (idRutina == -1) {
        JOptionPane.showMessageDialog(panelRutinas, "Seleccione una rutina primero");
        return;
    }

    if (gestorGym.toggleEstadoRutina(idRutina)) {
        actualizarVista();
        JOptionPane.showMessageDialog(panelRutinas, "Estado de rutina cambiado exitosamente");
    }
}

public void cargarEjerciciosDeRutina(int idRutina) {
    Rutina rutina = gestorGym.buscarRutinaPorId(idRutina);
    if (rutina != null && panelRutinas != null) {
        panelRutinas.actualizarTablaEjercicios(rutina.getEjercicios());
    }
}

private boolean validarDatosEjercicio(String nombre, String series, String repeticiones) {
    if (nombre == null || nombre.trim().isEmpty()) {
        JOptionPane.showMessageDialog(panelRutinas, "El nombre del ejercicio es obligatorio",
            "Error de validación", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if (series == null || series.trim().isEmpty()) {
        JOptionPane.showMessageDialog(panelRutinas, "Las series son obligatorias",
            "Error de validación", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    if (repeticiones == null || repeticiones.trim().isEmpty()) {
        JOptionPane.showMessageDialog(panelRutinas, "Las repeticiones son obligatorias",
            "Error de validación", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    try {
        int seriesNum = Integer.parseInt(series);
        int repeticionesNum = Integer.parseInt(repeticiones);
        
        if (seriesNum <= 0 || repeticionesNum <= 0) {
            JOptionPane.showMessageDialog(panelRutinas, 
                "Series y repeticiones deben ser números positivos",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(panelRutinas, 
            "Series y repeticiones deben ser números válidos",
            "Error de validación", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    return true;
}
    
    
    
}
