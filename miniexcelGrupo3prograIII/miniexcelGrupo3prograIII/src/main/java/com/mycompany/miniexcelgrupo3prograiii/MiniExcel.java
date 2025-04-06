/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author danyt
 */

public class MiniExcel {

    public JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ListaEnlazada listaenlazada = new ListaEnlazada();

        // Cabeceras
        String[] columnas = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        Object[][] datos = new Object[10][11];
        for (int i = 0; i < 10; i++) {
            datos[i][0] = String.valueOf(i + 1);
        }

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);

        JButton btnMostrar = new JButton("Mostrar Lista Enlazada");
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane textAreaScroll = new JScrollPane(textArea);

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaenlazada.limpiar(); // Limpiar la lista enlazada
                textArea.setText("");

                for (int i = 0; i < 10; i++) {
                    for (int j = 1; j <= 10; j++) {
                        String valor = tabla.getValueAt(i, j) == null ? "" : tabla.getValueAt(i, j).toString();
                        if (!valor.isEmpty()) {
                            listaenlazada.insertar(i + 1, j, valor);
                        }
                    }
                }

                textArea.setText(listaenlazada.recorrerLista());
            }
        });

        // Evaluación de fórmulas ----------------------------------------------------------
        tabla.getModel().addTableModelListener(e -> {
            int fila = e.getFirstRow();
            int columna = e.getColumn();
            Object valor = tabla.getValueAt(fila, columna);

            if (valor != null && valor.toString().startsWith("=")) {
                String resultado = FormulaEvaluator.evaluarFormula(valor.toString(), tabla);
                tabla.setValueAt(resultado, fila, columna); // Reemplaza fórmula por resultado
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnMostrar, BorderLayout.SOUTH);
        panel.add(textAreaScroll, BorderLayout.NORTH);

        return panel;
    }
}
