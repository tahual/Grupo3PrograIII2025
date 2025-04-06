/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;



/**
 *
 * @author danyt
 */
public class ListaEnlazada {
    private Celda cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertar(int fila, int columna, String valor) {
        Celda nueva = new Celda(fila, columna, valor);
        
        if (cabeza == null) {
            cabeza = nueva;
            return;
        }

        // Buscar posición por fila
        Celda actualFila = cabeza, anteriorFila = null;
        while (actualFila != null && actualFila.fila < fila) {
            anteriorFila = actualFila;
            actualFila = actualFila.abajo;
        }

        // Buscar posición por columna
        Celda actualColumna = cabeza, anteriorColumna = null;
        while (actualColumna != null && actualColumna.columna < columna) {
            anteriorColumna = actualColumna;
            actualColumna = actualColumna.derecha;
        }

        // Insertar en fila
        if (anteriorFila != null) {
            nueva.abajo = anteriorFila.abajo;
            anteriorFila.abajo = nueva;
        } else if (cabeza.fila > fila) {
            nueva.abajo = cabeza;
            cabeza = nueva;
        }

        // Insertar en columna
        if (anteriorColumna != null) {
            nueva.derecha = anteriorColumna.derecha;
            anteriorColumna.derecha = nueva;
        } else if (cabeza.columna > columna) {
            nueva.derecha = cabeza;
            cabeza = nueva;
        }
    }

    public String obtener(int fila, int columna) {
        Celda actual = cabeza;
        while (actual != null && actual.fila < fila) actual = actual.abajo;
        while (actual != null && actual.columna < columna) actual = actual.derecha;
        return (actual != null && actual.fila == fila && actual.columna == columna) ? actual.valor : null;
    }

    public void eliminar(int fila, int columna) {
        if (cabeza == null) return;

        Celda actual = cabeza, anterior = null;

        // Buscar celda a eliminar verticalmente
        while (actual != null && actual.fila < fila) {
            anterior = actual;
            actual = actual.abajo;
        }

        while (actual != null && actual.columna < columna) {
            anterior = actual;
            actual = actual.derecha;
        }

        if (actual != null && actual.fila == fila && actual.columna == columna) {
            if (anterior != null) {
                if (anterior.abajo == actual) anterior.abajo = actual.abajo;
                if (anterior.derecha == actual) anterior.derecha = actual.derecha;
            } else {
                cabeza = null; // si es la única celda
            }
        }
    }

    public String recorrerLista() {
        StringBuilder sb = new StringBuilder();
        Celda fila = cabeza;
        while (fila != null) {
            Celda columna = fila;
            while (columna != null) {
                sb.append("(")
                  .append(columna.fila)
                  .append(",")
                  .append(columna.columna)
                  .append(")=")
                  .append(columna.valor)
                  .append("   ");
                columna = columna.derecha;
            }
            sb.append("\n");
            fila = fila.abajo;
        }
        return sb.toString();
    }
    
    public  void limpiar(){
        cabeza = null; 
    }
}

