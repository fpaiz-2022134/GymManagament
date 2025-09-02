/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.francopaiz.gestiongym.view;

import com.francopaiz.gestiongym.controller.ControladorMiembros;
import com.francopaiz.gestiongym.model.Miembro;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
public class PanelMiembros extends javax.swing.JPanel {

    private JTable tablaMiembros;
    private DefaultTableModel modeloTabla;
    private JTextField txtNombre, txtId;
    private JComboBox<String> cmbMembresia;
    private JButton btnAgregar, btnEliminar, btnAsignarEntrenador, btnAsignarRutina;
    private ControladorMiembros controlador;

    public PanelMiembros(ControladorMiembros controlador) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        // Form
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Gestión de Miembros"));

        panelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEditable(false);
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Membresía:"));
        cmbMembresia = new JComboBox<>(new String[]{"Básica", "Premium", "VIP"});
        panelFormulario.add(cmbMembresia);

        // Buttons
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnAsignarEntrenador = new JButton("Asignar Entrenador");
        btnAsignarRutina = new JButton("Asignar Rutina");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnAsignarEntrenador);
        panelBotones.add(btnAsignarRutina);
        panelFormulario.add(panelBotones);

        add(panelFormulario, BorderLayout.NORTH);

        // Table
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Membresía", "Entrenador"}, 0);
        tablaMiembros = new JTable(modeloTabla);
        add(new JScrollPane(tablaMiembros), BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnAgregar.addActionListener(e -> controlador.agregarMiembro());
        btnEliminar.addActionListener(e -> controlador.eliminarMiembro());
        btnAsignarEntrenador.addActionListener(e -> controlador.asignarEntrenador());
        btnAsignarRutina.addActionListener(e -> controlador.asignarRutina());
        
        tablaMiembros.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosSeleccionados();
            }
        });
    }

    private void cargarDatosSeleccionados() {
        int filaSeleccionada = tablaMiembros.getSelectedRow();
        if (filaSeleccionada >= 0) {
            txtId.setText(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
            txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
            cmbMembresia.setSelectedItem(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
        }
    }

    public void actualizarTablaMiembros(ArrayList<Miembro> miembros) {
        modeloTabla.setRowCount(0);
        for (Miembro miembro : miembros) {
            modeloTabla.addRow(new Object[]{
                miembro.getId(),
                miembro.getNombre(),
                miembro.getTipoMembresia(),
                miembro.getEntrenadorAsignado() != null ? 
                    miembro.getEntrenadorAsignado().getNombre() : "Sin asignar"
            });
        }
    }

    public void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        cmbMembresia.setSelectedIndex(0);
    }

    public String getNombre() { return txtNombre.getText(); }
    public String getTipoMembresia() { return cmbMembresia.getSelectedItem().toString(); }
    public int getIdSeleccionado() {
        try {
            return Integer.parseInt(txtId.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
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
