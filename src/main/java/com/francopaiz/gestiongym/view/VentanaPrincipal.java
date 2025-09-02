/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.francopaiz.gestiongym.view;

import com.francopaiz.gestiongym.controller.ControladorPrincipal;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author Dell
 */
public class VentanaPrincipal extends javax.swing.JFrame {


    
     private JTabbedPane tabbedPane;
    private PanelMiembros panelMiembros;
    private PanelEntrenadores panelEntrenadores;
    private PanelRutinas panelRutinas;
    private PanelReportes panelReportes;
    private ControladorPrincipal controladorPrincipal;

    public VentanaPrincipal(ControladorPrincipal controlador) {
        this.controladorPrincipal = controlador;
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        tabbedPane = new JTabbedPane();
        
        // Crear paneles con sus controladores
        panelMiembros = new PanelMiembros(controladorPrincipal.getControladorMiembros());
        panelEntrenadores = new PanelEntrenadores(controladorPrincipal.getControladorEntrenadores());
        panelRutinas = new PanelRutinas(controladorPrincipal.getControladorRutinas());
        panelReportes = new PanelReportes(controladorPrincipal);

        tabbedPane.addTab("Miembros", panelMiembros);
        tabbedPane.addTab("Entrenadores", panelEntrenadores);
        tabbedPane.addTab("Rutinas", panelRutinas);
        tabbedPane.addTab("Reportes", panelReportes);

        add(tabbedPane);
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión del Gimnasio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public PanelMiembros getPanelMiembros() { return panelMiembros; }
    public PanelEntrenadores getPanelEntrenadores() { return panelEntrenadores; }
    public PanelRutinas getPanelRutinas() { return panelRutinas; }
    public PanelReportes getPanelReportes() { return panelReportes; }
    
    
    
    
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
