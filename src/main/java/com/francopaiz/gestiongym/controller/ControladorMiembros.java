/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.controller;

import com.francopaiz.gestiongym.model.Rutina;
import com.francopaiz.gestiongym.model.Entrenador;
import com.francopaiz.gestiongym.model.GestorGym;
import com.francopaiz.gestiongym.view.PanelMiembros;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ControladorMiembros {
    private GestorGym gestorGym;
    private PanelMiembros panelMiembros;
    
    public ControladorMiembros(GestorGym gestor){
        this.gestorGym = gestor;
    }
    
    public void setPanelMiembros(PanelMiembros panel){
        this.panelMiembros = panel;
        
    }
    
    public void agregarMiembro() {
        String nombre = panelMiembros.getNombre();
        String tipoMembresia = panelMiembros.getTipoMembresia();

        if (validarDatos(nombre)) {
            if (gestorGym.agregarMiembro(nombre, tipoMembresia)) {
                panelMiembros.limpiarFormulario();
                actualizarVista();
                JOptionPane.showMessageDialog(panelMiembros, "Miembro agregado exitosamente");
            } else {
                JOptionPane.showMessageDialog(panelMiembros, "Error al agregar miembro", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void eliminarMiembro() {
        int id = panelMiembros.getIdSeleccionado();
        if (id > 0) {
            int confirmacion = JOptionPane.showConfirmDialog(panelMiembros,
                "¿Está seguro de eliminar este miembro?", "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (gestorGym.eliminarMiembro(id)) {
                    panelMiembros.limpiarFormulario();
                    actualizarVista();
                    JOptionPane.showMessageDialog(panelMiembros, "Miembro eliminado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(panelMiembros, "Error al eliminar miembro",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(panelMiembros, "Seleccione un miembro para eliminar");
        }
    }

    public void asignarEntrenador() {
        int idMiembro = panelMiembros.getIdSeleccionado();
        if (idMiembro > 0) {
            ArrayList<Entrenador> entrenadores = gestorGym.getEntrenadores();
            if (!entrenadores.isEmpty()) {
                String[] nombres = entrenadores.stream()
                    .map(e -> e.getId() + " - " + e.getNombre())
                    .toArray(String[]::new);
                
                String seleccion = (String) JOptionPane.showInputDialog(panelMiembros,
                    "Seleccione un entrenador:", "Asignar Entrenador",
                    JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);
                
                if (seleccion != null) {
                    int idEntrenador = Integer.parseInt(seleccion.split(" - ")[0]);
                    if (gestorGym.asignarEntrenadorAMiembro(idMiembro, idEntrenador)) {
                        actualizarVista();
                        JOptionPane.showMessageDialog(panelMiembros, "Entrenador asignado exitosamente");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(panelMiembros, "No hay entrenadores disponibles");
            }
        } else {
            JOptionPane.showMessageDialog(panelMiembros, "Seleccione un miembro primero");
        }
    }

    public void asignarRutina() {
        int idMiembro = panelMiembros.getIdSeleccionado();
        if (idMiembro > 0) {
            ArrayList<Rutina> rutinas = gestorGym.getRutinas();
            if (!rutinas.isEmpty()) {
                String[] nombres = rutinas.stream()
                    .filter(Rutina::isActiva)
                    .map(r -> r.getId() + " - " + r.getNombre())
                    .toArray(String[]::new);
                
                if (nombres.length > 0) {
                    String seleccion = (String) JOptionPane.showInputDialog(panelMiembros,
                        "Seleccione una rutina:", "Asignar Rutina",
                        JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);
                    
                    if (seleccion != null) {
                        int idRutina = Integer.parseInt(seleccion.split(" - ")[0]);
                        if (gestorGym.asignarRutinaAMiembro(idMiembro, idRutina)) {
                            actualizarVista();
                            JOptionPane.showMessageDialog(panelMiembros, "Rutina asignada exitosamente");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(panelMiembros, "No hay rutinas activas disponibles");
                }
            } else {
                JOptionPane.showMessageDialog(panelMiembros, "No hay rutinas disponibles");
            }
        } else {
            JOptionPane.showMessageDialog(panelMiembros, "Seleccione un miembro primero");
        }
    }

    private boolean validarDatos(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelMiembros, "El nombre es obligatorio",
                "Error de validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void actualizarVista() {
        if (panelMiembros != null) {
            panelMiembros.actualizarTablaMiembros(gestorGym.getMiembros());
        }
    }
}
