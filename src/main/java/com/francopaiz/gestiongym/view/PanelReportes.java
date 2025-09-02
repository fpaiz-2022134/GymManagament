/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.francopaiz.gestiongym.view;

import com.francopaiz.gestiongym.controller.ControladorPrincipal;
import com.francopaiz.gestiongym.model.Entrenador;
import com.francopaiz.gestiongym.model.Rutina;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class PanelReportes extends javax.swing.JPanel {

    private JLabel lblRutinaMasPopular, lblRutinasActivas, lblEntrenadorMasAlumnos;
    private JButton btnGenerarReporte;
    private ControladorPrincipal controlador;
    
    public PanelReportes(ControladorPrincipal controlador){
        this.controlador = controlador;
        inicializarComponentes();
        configurarEventos();
    }
    
        
        private void inicializarComponentes() {
        setLayout(new GridLayout(4, 1, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Reportes del Gimnasio"));

        lblRutinaMasPopular = new JLabel("Rutina más popular: ---");
        lblRutinasActivas = new JLabel("Rutinas activas: ---");
        lblEntrenadorMasAlumnos = new JLabel("Entrenador con más alumnos: ---");
        btnGenerarReporte = new JButton("Generar Reporte");

        add(lblRutinaMasPopular);
        add(lblRutinasActivas);
        add(lblEntrenadorMasAlumnos);
        add(btnGenerarReporte);
    }
        
    private void configurarEventos() {
       btnGenerarReporte.addActionListener(e -> controlador.generarReportes());
    }

    public void actualizarReportes(Map<String, Object> reportes) {
        Rutina rutinaMasPopular = (Rutina) reportes.get("rutinaMasPopular");
        Integer rutinasActivas = (Integer) reportes.get("rutinasActivas");
        Entrenador entrenadorMasAlumnos = (Entrenador) reportes.get("entrenadorMasAlumnos");

        lblRutinaMasPopular.setText("Rutina más popular: " + 
            (rutinaMasPopular != null ? rutinaMasPopular.getNombre() + " (" + 
             rutinaMasPopular.getPracticantes() + " practicantes)" : "Ninguna"));
        
        lblRutinasActivas.setText("Rutinas activas: " + (rutinasActivas != null ? rutinasActivas : 0));
        
        lblEntrenadorMasAlumnos.setText("Entrenador con más alumnos: " + 
            (entrenadorMasAlumnos != null ? entrenadorMasAlumnos.getNombre() + " (" + 
             entrenadorMasAlumnos.getCantidadMiembros() + " alumnos)" : "Ninguno"));
    }
    
    
    
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
