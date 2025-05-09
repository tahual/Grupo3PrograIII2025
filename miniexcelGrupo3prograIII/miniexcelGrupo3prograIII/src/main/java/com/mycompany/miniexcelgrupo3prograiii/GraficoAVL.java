/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import com.mycompany.miniexcelgrupo3prograiii.bd.Nodos;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author USUARIO
 */
public class GraficoAVL extends JPanel {

    private Nodos raiz;

    public GraficoAVL(Nodos raiz) {
        this.raiz = raiz;
        setPreferredSize(new Dimension(600, 400)); // Tamaño del panel donde se va a dibujar el árbol
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarArbol(g, raiz, getWidth() / 2, 20, 50); // Llamada a la función de dibujo
    }

    // Método recursivo para dibujar el árbol
    private void dibujarArbol(Graphics g, Nodos nodo, int x, int y, int offset) {
        if (nodo != null) {
            g.fillOval(x - 15, y - 15, 30, 30); // Dibuja el nodo
            g.drawString(String.valueOf(nodo.getValor()), x - 7, y + 5); // Escribe el valor del nodo

            // Dibuja las líneas que conectan con los hijos
            if (nodo.getIzquierda() != null) {
                g.drawLine(x, y, x - offset, y + 50); // Línea hacia el hijo izquierdo
                dibujarArbol(g, nodo.getIzquierda(), x - offset, y + 50, offset / 2); // Recursión para el hijo izquierdo
            }
            if (nodo.getDerecha() != null) {
                g.drawLine(x, y, x + offset, y + 50); // Línea hacia el hijo derecho
                dibujarArbol(g, nodo.getDerecha(), x + offset, y + 50, offset / 2); // Recursión para el hijo derecho
            }
        }
    }

    // Actualiza la raíz del árbol y repinta el gráfico
public void setRaiz(Nodos nuevaRaiz) {
    this.raiz = nuevaRaiz;  // Actualizar la raíz
    repaint();  // Vuelve a pintar el gráfico
}

}
