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
    if (cabeza == null) {
        cabeza = new Celda(fila, columna, valor);
        return;
    }

    Celda filaActual = cabeza;
    Celda anteriorFila = null;

    // Buscar o crear la fila
    while (filaActual != null && filaActual.fila < fila) {
        anteriorFila = filaActual;
        filaActual = filaActual.abajo;
    }

    if (filaActual == null || filaActual.fila != fila) {
        Celda nuevaFila = new Celda(fila, -1, "");
        if (anteriorFila != null) {
            nuevaFila.abajo = anteriorFila.abajo;
            anteriorFila.abajo = nuevaFila;
        } else {
            nuevaFila.abajo = cabeza;
            cabeza = nuevaFila;
        }
        filaActual = nuevaFila;
    }

    // Buscar la celda en la fila
    Celda celdaActual = filaActual;
    Celda anteriorCelda = null;

    while (celdaActual != null && celdaActual.columna < columna) {
        anteriorCelda = celdaActual;
        celdaActual = celdaActual.derecha;
    }

    if (celdaActual != null && celdaActual.columna == columna) {
        celdaActual.valor = valor; // ya existe, solo actualizar
    } else {
        Celda nueva = new Celda(fila, columna, valor);
        if (anteriorCelda != null) {
            nueva.derecha = anteriorCelda.derecha;
            anteriorCelda.derecha = nueva;
        } else {
            nueva.derecha = filaActual.derecha;
            filaActual.derecha = nueva;
        }
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
    StringBuilder resultado = new StringBuilder();
    Celda filaActual = cabeza;

    while (filaActual != null) {
        Celda celdaActual = filaActual;
        while (celdaActual != null) {
            if (celdaActual.valor != null && !celdaActual.valor.isEmpty()) {
                resultado.append(celdaActual.valor).append(", ");
            }
            celdaActual = celdaActual.derecha;
        }
        filaActual = filaActual.abajo;
    }
    return resultado.toString();
}
        
    public  void limpiar(){
        cabeza = null; 
    }
}

