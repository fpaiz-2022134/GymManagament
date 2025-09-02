/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.francopaiz.gestiongym.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class CSVManager {
     private static final String SEPARADOR = ",";

    public boolean exportarMiembrosACSV(String rutaArchivo, ArrayList<Miembro> miembros) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("ID,Nombre,TipoMembresia,Entrenador");
            for (Miembro miembro : miembros) {
                writer.printf("%d,%s,%s,%s%n",
                    miembro.getId(),
                    miembro.getNombre(),
                    miembro.getTipoMembresia(),
                    miembro.getEntrenadorAsignado() != null ? 
                        miembro.getEntrenadorAsignado().getNombre() : "Sin asignar");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Miembro> cargarMiembrosDesdeCSV(String rutaArchivo) {
        ArrayList<Miembro> miembros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            reader.readLine(); // Saltar encabezado
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR);
                if (datos.length >= 3) {
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String tipoMembresia = datos[2];
                    miembros.add(new Miembro(id, nombre, tipoMembresia));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return miembros;
    }
}
