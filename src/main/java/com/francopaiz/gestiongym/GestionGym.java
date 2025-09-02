/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.francopaiz.gestiongym;

import com.francopaiz.gestiongym.controller.ControladorPrincipal;
import javax.swing.UIManager;

/**
 *
 * @author Franco Paiz
 * 
 * Github PROJECT: https://github.com/fpaiz-2022134/GymManagament.git
 */
public class GestionGym {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Starting the app
        ControladorPrincipal controlador = new ControladorPrincipal();
        controlador.iniciarAplicacion();
    }
}
