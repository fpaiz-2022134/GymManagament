/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.francopaiz.gestiongym.view;

import com.francopaiz.gestiongym.controller.ControladorEntrenadores;
import com.francopaiz.gestiongym.model.Entrenador;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class PanelEntrenadores extends javax.swing.JPanel {

    private JTable tablaEntrenadores;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtEspecialidad;
    private JButton btnAgregar;
    private ControladorEntrenadores controlador;

    public PanelEntrenadores(ControladorEntrenadores controlador) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Form
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Gestión de Entrenadores"));

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Especialidad:"));
        txtEspecialidad = new JTextField();
        panelFormulario.add(txtEspecialidad);

        btnAgregar = new JButton("Agregar Entrenador");
        panelFormulario.add(btnAgregar);

        add(panelFormulario, BorderLayout.NORTH);

        // Table
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Especialidad", "Alumnos"}, 0);
        tablaEntrenadores = new JTable(modeloTabla);
        add(new JScrollPane(tablaEntrenadores), BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnAgregar.addActionListener(e -> controlador.agregarEntrenador());
    }

    public void actualizarTablaEntrenadores(ArrayList<Entrenador> entrenadores) {
        modeloTabla.setRowCount(0);
        for (Entrenador entrenador : entrenadores) {
            modeloTabla.addRow(new Object[]{
                entrenador.getId(),
                entrenador.getNombre(),
                entrenador.getEspecialidad(),
                entrenador.getCantidadMiembros()
            });
        }
    }

    public void limpiarFormulario() {
        txtNombre.setText("");
        txtEspecialidad.setText("");
    }

    public String getNombre() { return txtNombre.getText(); }
    public String getEspecialidad() { return txtEspecialidad.getText(); }
   
    
    
    
    
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
