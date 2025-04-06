/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.miniexcelgrupo3prograiii;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
/**
 *
 * @author danyt
 */
public class MiniexcelGrupo3prograIII {
      public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Mini Excel");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de MiniExcel y obtener el panel
        MiniExcel miniExcel = new MiniExcel();
        JPanel panel = miniExcel.crearPanel();

        // Agregar el panel a la ventana y hacerla visible
        frame.add(panel);
        frame.setVisible(true);
    }
}

