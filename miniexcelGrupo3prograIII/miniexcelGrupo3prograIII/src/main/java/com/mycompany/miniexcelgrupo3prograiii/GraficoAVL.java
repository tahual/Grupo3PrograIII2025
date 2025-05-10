/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import com.mycompany.miniexcelgrupo3prograiii.bd.Nodos;
import java.awt.Color;
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
    }

    public void setRaiz(Nodos raiz) {
        this.raiz = raiz;
        repaint(); // para redibujar el árbol
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            dibujarNodo(g, raiz, getWidth() / 2, 30, 0);
        }
    }

    // Método recursivo para dibujar el árbol
    private void dibujarNodo(Graphics g, Nodos nodo, int x, int y, int nivel) {
    if (nodo == null) return;

    int radio = 20; // Tamaño del nodo
    int offsetY = 50; // Separación vertical entre niveles
    int offsetX = (int) (getWidth() / Math.pow(2, nivel + 2)); // Separación horizontal variable

    // Dibujar línea a hijo izquierdo
    if (nodo.getIzquierda() != null) {
        int hijoX = x - offsetX;
        int hijoY = y + offsetY;
        g.drawLine(x, y, hijoX, hijoY);
        dibujarNodo(g, nodo.getIzquierda(), hijoX, hijoY, nivel + 1);
    }

    // Dibujar línea a hijo derecho
    if (nodo.getDerecha() != null) {
        int hijoX = x + offsetX;
        int hijoY = y + offsetY;
        g.drawLine(x, y, hijoX, hijoY);
        dibujarNodo(g, nodo.getDerecha(), hijoX, hijoY, nivel + 1);
    }

    // Dibujar círculo del nodo
    g.setColor(Color.WHITE);
    g.fillOval(x - radio / 2, y - radio / 2, radio, radio);

    // Dibujar valor dentro del nodo
    g.setColor(Color.BLACK);
    g.drawString(String.valueOf(nodo.getValor()), x - 6, y + 5);
}

}
