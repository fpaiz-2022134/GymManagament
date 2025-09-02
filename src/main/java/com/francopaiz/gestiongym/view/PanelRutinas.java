/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.francopaiz.gestiongym.view;

import com.francopaiz.gestiongym.controller.ControladorRutinas;
import com.francopaiz.gestiongym.model.Rutina;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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
public class PanelRutinas extends javax.swing.JPanel {

    private JTable tablaRutinas;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombreRutina;
    private JComboBox<String> cmbTipoRutina;
    private JButton btnCrearRutina;
    private ControladorRutinas controlador;

    public PanelRutinas(ControladorRutinas controlador) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Forms
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Gestión de Rutinas"));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombreRutina = new JTextField();
        panelFormulario.add(txtNombreRutina);

        panelFormulario.add(new JLabel("Tipo:"));
        cmbTipoRutina = new JComboBox<>(new String[]{"Cardio", "Fuerza", "Mixta", "Funcional"});
        panelFormulario.add(cmbTipoRutina);

        btnCrearRutina = new JButton("Crear Rutina");
        panelFormulario.add(btnCrearRutina);

        add(panelFormulario, BorderLayout.NORTH);

        // Table
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Tipo", "Practicantes", "Activa"}, 0);
        tablaRutinas = new JTable(modeloTabla);
        add(new JScrollPane(tablaRutinas), BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnCrearRutina.addActionListener(e -> controlador.crearRutina());
    }

    public void actualizarTablaRutinas(ArrayList<Rutina> rutinas) {
        modeloTabla.setRowCount(0);
        for (Rutina rutina : rutinas) {
            modeloTabla.addRow(new Object[]{
                rutina.getId(),
                rutina.getNombre(),
                rutina.getTipo(),
                rutina.getPracticantes(),
                rutina.isActiva() ? "Sí" : "No"
            });
        }
    }

    public void limpiarFormulario() {
        txtNombreRutina.setText("");
        cmbTipoRutina.setSelectedIndex(0);
    }

    public String getNombreRutina() { return txtNombreRutina.getText(); }
    public String getTipoRutina() { return cmbTipoRutina.getSelectedItem().toString(); }
    
    
    
    
    
    
    
    @SuppressWarnings("unchecked")
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
