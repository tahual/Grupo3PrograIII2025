/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

/**
 *
 * @author Marlon Cuco
 */
public class HojaCalculo {
    private Celda inicio;

    public HojaCalculo() {
        this.inicio = null;
    }

    // Método para insertar un valor en una celda específica
    public void insertarValor(int fila, int columna, String valor) {
        Celda nuevaCelda = new Celda(fila, columna, valor);
        if (inicio == null) {
            inicio = nuevaCelda;
        } else {
            Celda temp = inicio;
            while (temp.abajo != null) {
                temp = temp.abajo;
            }
            temp.abajo = nuevaCelda;
        }
    }

    // Método para recuperar un valor de una celda específica
    public String obtenerValor(int fila, int columna) {
        Celda actual = inicio;
        while (actual != null) {
            if (actual.fila == fila && actual.columna == columna) {
                return actual.valor;
            }
            actual = actual.abajo;
        }
        return ""; // Retorna cadena vacía si no se encuentra la celda
    }

    // Método para mostrar todos los valores almacenados
    public String mostrarValores() {
        StringBuilder resultado = new StringBuilder();
        Celda actual = inicio;
        while (actual != null) {
            if (!actual.valor.isEmpty()) {
                resultado.append(actual.valor).append(", ");
            }
            actual = actual.abajo;
        }
        return resultado.toString().trim();
    }
}
    
