/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MiniExcel {

    public JPanel crearPanel() {
        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Crear la lista enlazada
        ListaEnlazada listaenlazada = new ListaEnlazada();

        // Crear la tabla (JTable)
        String[] columnas = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        Object[][] datos = new Object[10][11];
        for (int i = 0; i < 10; i++) {
            datos[i][0] = String.valueOf(i + 1);
        }

        JTable tabla = new JTable(datos, columnas);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Crear un botón para mostrar el contenido de la lista enlazada
        JButton btnMostrar = new JButton("Mostrar Lista Enlazada");
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        JScrollPane textAreaScroll = new JScrollPane(textArea);

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaenlazada.limpiarLista(); // Limpiar la lista antes de agregar nuevos datos
                textArea.setText(""); // Limpiar el JTextArea

                // Recorrer la tabla y agregar las celdas a la lista enlazada
                for (int i = 0; i < 10; i++) {
                    for (int j = 1; j <= 10; j++) {
                        String valor = tabla.getValueAt(i, j) == null ? "" : tabla.getValueAt(i, j).toString();
                        listaenlazada.agregarCelda(i + 1, j, valor);
                    }
                }

                // Mostrar el resultado actualizado de la lista enlazada en el JTextArea
                textArea.setText(listaenlazada.recorrerLista());
            }
        });

        // Agregar los componentes al panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnMostrar, BorderLayout.SOUTH);
        panel.add(textAreaScroll, BorderLayout.NORTH);
        
//-----------------------------------------------------------------------------------------------------------------------------------

        tabla.getModel().addTableModelListener(e -> {
            int fila = e.getFirstRow();
            int columna = e.getColumn();
            Object valor = tabla.getValueAt(fila, columna);

            if (valor != null && valor.toString().startsWith("=")) {
                String resultado = FormulaEvaluator.evaluarFormula(valor.toString(), tabla);
                tabla.setValueAt(resultado, fila, columna); // Reemplaza la fórmula por el resultado
            }
        });
        return panel;
    }
}
