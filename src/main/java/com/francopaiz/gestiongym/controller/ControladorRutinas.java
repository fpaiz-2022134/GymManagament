/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.controller;

import com.francopaiz.gestiongym.model.GestorGym;
import com.francopaiz.gestiongym.view.PanelRutinas;
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
                panelRutinas.limpiarFormulario();
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
}
