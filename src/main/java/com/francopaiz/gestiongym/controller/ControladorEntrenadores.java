/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.controller;

import com.francopaiz.gestiongym.model.GestorGym;
import com.francopaiz.gestiongym.view.PanelEntrenadores;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ControladorEntrenadores {
    private GestorGym gestorGym;
    private PanelEntrenadores panelEntrenadores;

    public ControladorEntrenadores(GestorGym gestor) {
        this.gestorGym = gestor;
    }

    public void setPanelEntrenadores(PanelEntrenadores panel) {
        this.panelEntrenadores = panel;
    }

    public void agregarEntrenador() {
        String nombre = panelEntrenadores.getNombre();
        String especialidad = panelEntrenadores.getEspecialidad();

        if (validarDatos(nombre, especialidad)) {
            if (gestorGym.agregarEntrenador(nombre, especialidad)) {
                panelEntrenadores.limpiarFormulario();
                actualizarVista();
                JOptionPane.showMessageDialog(panelEntrenadores, "Entrenador agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(panelEntrenadores, "Error al agregar entrenador",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validarDatos(String nombre, String especialidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelEntrenadores, "El nombre es obligatorio",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (especialidad == null || especialidad.trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelEntrenadores, "La especialidad es obligatoria",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void actualizarVista() {
        if (panelEntrenadores != null) {
            panelEntrenadores.actualizarTablaEntrenadores(gestorGym.getEntrenadores());
        }
    }
}
