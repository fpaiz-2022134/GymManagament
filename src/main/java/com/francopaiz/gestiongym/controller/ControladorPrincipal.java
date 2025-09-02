/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.controller;

import com.francopaiz.gestiongym.model.CSVManager;
import com.francopaiz.gestiongym.model.GestorGym;
import com.francopaiz.gestiongym.view.VentanaPrincipal;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dell
 */
public class ControladorPrincipal {
    private GestorGym gestorGym;
    private VentanaPrincipal ventanaPrincipal;
    private CSVManager csvManager;
    private ControladorMiembros controladorMiembros;
    private ControladorEntrenadores controladorEntrenadores;
    private ControladorRutinas controladorRutinas;

    public ControladorPrincipal() {
        gestorGym = new GestorGym();
        csvManager = new CSVManager();
        
        // Controllers
        controladorMiembros = new ControladorMiembros(gestorGym);
        controladorEntrenadores = new ControladorEntrenadores(gestorGym);
        controladorRutinas = new ControladorRutinas(gestorGym);
        
        // Main window
        ventanaPrincipal = new VentanaPrincipal(this);
        
       //Panels for controllers
        controladorMiembros.setPanelMiembros(ventanaPrincipal.getPanelMiembros());
        controladorEntrenadores.setPanelEntrenadores(ventanaPrincipal.getPanelEntrenadores());
        controladorRutinas.setPanelRutinas(ventanaPrincipal.getPanelRutinas());
        
        // Charging data
        cargarDatosEjemplo();
    }

    public void iniciarAplicacion() {
        SwingUtilities.invokeLater(() -> {
            ventanaPrincipal.setVisible(true);
            actualizarTodasLasVistas();
        });
    }

    public void generarReportes() {
        Map<String, Object> reportes = new HashMap<>();
        reportes.put("rutinaMasPopular", gestorGym.getRutinaMasPopular());
        reportes.put("rutinasActivas", gestorGym.getNumeroRutinasActivas());
        reportes.put("entrenadorMasAlumnos", gestorGym.getEntrenadorConMasAlumnos());
        
        ventanaPrincipal.getPanelReportes().actualizarReportes(reportes);
    }

    public void exportarDatosACSV() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar datos como CSV");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV", "csv"));
        
        if (fileChooser.showSaveDialog(ventanaPrincipal) == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
            if (!rutaArchivo.endsWith(".csv")) {
                rutaArchivo += ".csv";
            }
            
            if (csvManager.exportarMiembrosACSV(rutaArchivo, gestorGym.getMiembros())) {
                JOptionPane.showMessageDialog(ventanaPrincipal, 
                    "Datos exportados exitosamente a: " + rutaArchivo);
            } else {
                JOptionPane.showMessageDialog(ventanaPrincipal, 
                    "Error al exportar datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void cargarDatosEjemplo() {
        // Adding some data
        gestorGym.agregarEntrenador("Carlos Rodríguez", "Fuerza");
        gestorGym.agregarEntrenador("María González", "Cardio");
        gestorGym.agregarEntrenador("José López", "Funcional");
        
        gestorGym.agregarRutina("Cardio Intenso", "Cardio");
        gestorGym.agregarRutina("Fuerza Total", "Fuerza");
        gestorGym.agregarRutina("Funcional Básico", "Funcional");
        
        gestorGym.agregarMiembro("Ana Martínez", "Premium");
        gestorGym.agregarMiembro("Pedro Sánchez", "Básica");
        gestorGym.agregarMiembro("Laura Jiménez", "VIP");
        
        // Some assignments
        gestorGym.asignarEntrenadorAMiembro(1, 1);
        gestorGym.asignarEntrenadorAMiembro(2, 2);
        gestorGym.asignarEntrenadorAMiembro(3, 1);
        
        gestorGym.asignarRutinaAMiembro(1, 1);
        gestorGym.asignarRutinaAMiembro(1, 2);
        gestorGym.asignarRutinaAMiembro(2, 1);
        gestorGym.asignarRutinaAMiembro(3, 3);
    }

    private void actualizarTodasLasVistas() {
        controladorMiembros.actualizarVista();
        controladorEntrenadores.actualizarVista();
        controladorRutinas.actualizarVista();
        generarReportes();
    }

    //Getters
    public ControladorMiembros getControladorMiembros() { return controladorMiembros; }
    public ControladorEntrenadores getControladorEntrenadores() { return controladorEntrenadores; }
    public ControladorRutinas getControladorRutinas() { return controladorRutinas; }
    public GestorGym getGestorGym() { return gestorGym; }
}
