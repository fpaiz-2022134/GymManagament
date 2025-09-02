/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.francopaiz.gestiongym.view;

import com.francopaiz.gestiongym.controller.ControladorRutinas;
import com.francopaiz.gestiongym.model.Ejercicio;
import com.francopaiz.gestiongym.model.Rutina;
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
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class PanelRutinas extends javax.swing.JPanel {

    private JTable tablaRutinas;
    private DefaultTableModel modeloTablaRutinas;
    private JTable tablaEjercicios;
    private DefaultTableModel modeloTablaEjercicios;
    private JTextField txtNombreRutina, txtNombreEjercicio, txtSeries, txtRepeticiones, txtDescripcion;
    private JComboBox<String> cmbTipoRutina;
    private JButton btnCrearRutina, btnAgregarEjercicio, btnEliminarEjercicio, btnToggleActiva;
    private ControladorRutinas controlador;
    private int rutinaSeleccionadaId = -1;

    public PanelRutinas(ControladorRutinas controlador) {
        this.controlador = controlador;
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

       
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Rutinas"));

       
        JPanel panelFormRutina = new JPanel(new GridLayout(4, 2, 5, 5));
        panelFormRutina.add(new JLabel("Nombre:"));
        txtNombreRutina = new JTextField();
        panelFormRutina.add(txtNombreRutina);

        panelFormRutina.add(new JLabel("Tipo:"));
        cmbTipoRutina = new JComboBox<>(new String[]{"Cardio", "Fuerza", "Mixta", "Funcional"});
        panelFormRutina.add(cmbTipoRutina);

        JPanel panelBotonesRutina = new JPanel(new FlowLayout());
        btnCrearRutina = new JButton("Crear Rutina");
        btnToggleActiva = new JButton("Activar/Desactivar");
        panelBotonesRutina.add(btnCrearRutina);
        panelBotonesRutina.add(btnToggleActiva);
        panelFormRutina.add(panelBotonesRutina);

        panelIzquierdo.add(panelFormRutina, BorderLayout.NORTH);

      
        modeloTablaRutinas = new DefaultTableModel(new String[]{"ID", "Nombre", "Tipo", "Ejercicios", "Practicantes", "Activa"}, 0);
        tablaRutinas = new JTable(modeloTablaRutinas);
        tablaRutinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelIzquierdo.add(new JScrollPane(tablaRutinas), BorderLayout.CENTER);

        splitPane.setLeftComponent(panelIzquierdo);

       
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createTitledBorder("Ejercicios de la Rutina"));

        
        JPanel panelFormEjercicio = new JPanel(new GridLayout(6, 2, 5, 5));

        panelFormEjercicio.add(new JLabel("Nombre Ejercicio:"));
        txtNombreEjercicio = new JTextField();
        panelFormEjercicio.add(txtNombreEjercicio);

        panelFormEjercicio.add(new JLabel("Series:"));
        txtSeries = new JTextField();
        panelFormEjercicio.add(txtSeries);

        panelFormEjercicio.add(new JLabel("Repeticiones:"));
        txtRepeticiones = new JTextField();
        panelFormEjercicio.add(txtRepeticiones);

        panelFormEjercicio.add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        panelFormEjercicio.add(txtDescripcion);

        JPanel panelBotonesEjercicio = new JPanel(new FlowLayout());
        btnAgregarEjercicio = new JButton("Agregar Ejercicio");
        btnEliminarEjercicio = new JButton("Eliminar Ejercicio");
        panelBotonesEjercicio.add(btnAgregarEjercicio);
        panelBotonesEjercicio.add(btnEliminarEjercicio);
        panelFormEjercicio.add(panelBotonesEjercicio);

        panelDerecho.add(panelFormEjercicio, BorderLayout.NORTH);

      
        modeloTablaEjercicios = new DefaultTableModel(new String[]{"ID", "Nombre", "Series", "Repeticiones", "Descripción"}, 0);
        tablaEjercicios = new JTable(modeloTablaEjercicios);
        panelDerecho.add(new JScrollPane(tablaEjercicios), BorderLayout.CENTER);

        splitPane.setRightComponent(panelDerecho);
        splitPane.setDividerLocation(400);

        add(splitPane, BorderLayout.CENTER);

        
        JLabel lblInfo = new JLabel("Seleccione una rutina para gestionar sus ejercicios");
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblInfo, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        btnCrearRutina.addActionListener(e -> controlador.crearRutina());
        btnAgregarEjercicio.addActionListener(e -> controlador.agregarEjercicioARutina());
        btnEliminarEjercicio.addActionListener(e -> controlador.eliminarEjercicioDeRutina());
        btnToggleActiva.addActionListener(e -> controlador.toggleEstadoRutina());

        
        tablaRutinas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tablaRutinas.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    rutinaSeleccionadaId = (Integer) modeloTablaRutinas.getValueAt(filaSeleccionada, 0);
                    controlador.cargarEjerciciosDeRutina(rutinaSeleccionadaId);
                }
            }
        });
    }

    public void actualizarTablaRutinas(ArrayList<Rutina> rutinas) {
        modeloTablaRutinas.setRowCount(0);
        for (Rutina rutina : rutinas) {
            modeloTablaRutinas.addRow(new Object[]{
                rutina.getId(),
                rutina.getNombre(),
                rutina.getTipo(),
                rutina.getEjercicios().size(), 
                rutina.getPracticantes(),
                rutina.isActiva() ? "Sí" : "No"
            });
        }
    }

    public void actualizarTablaEjercicios(ArrayList<Ejercicio> ejercicios) {
        modeloTablaEjercicios.setRowCount(0);
        for (Ejercicio ejercicio : ejercicios) {
            modeloTablaEjercicios.addRow(new Object[]{
                ejercicio.getId(),
                ejercicio.getNombre(),
                ejercicio.getSeries(),
                ejercicio.getRepeticiones(),
                ejercicio.getDescripcion()
            });
        }
    }

    public void limpiarFormularioRutina() {
        txtNombreRutina.setText("");
        cmbTipoRutina.setSelectedIndex(0);
    }

    public void limpiarFormularioEjercicio() {
        txtNombreEjercicio.setText("");
        txtSeries.setText("");
        txtRepeticiones.setText("");
        txtDescripcion.setText("");
    }

    // Getters para rutinas
    public String getNombreRutina() {
        return txtNombreRutina.getText();
    }

    public String getTipoRutina() {
        return cmbTipoRutina.getSelectedItem().toString();
    }

    public int getRutinaSeleccionadaId() {
        return rutinaSeleccionadaId;
    }

    // Getters para ejercicios
    public String getNombreEjercicio() {
        return txtNombreEjercicio.getText();
    }

    public String getSeries() {
        return txtSeries.getText();
    }

    public String getRepeticiones() {
        return txtRepeticiones.getText();
    }

    public String getDescripcion() {
        return txtDescripcion.getText();
    }

    public int getEjercicioSeleccionadoId() {
        int filaSeleccionada = tablaEjercicios.getSelectedRow();
        if (filaSeleccionada >= 0) {
            return (Integer) modeloTablaEjercicios.getValueAt(filaSeleccionada, 0);
        }
        return -1;
    }

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
