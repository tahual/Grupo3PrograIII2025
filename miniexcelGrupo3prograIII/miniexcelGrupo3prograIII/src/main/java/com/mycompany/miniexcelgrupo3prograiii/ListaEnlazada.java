/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;



/**
 *
 * @author danyt
 */
class ListaEnlazada {
        Celda nodo;

        public ListaEnlazada() {
            nodo = null;
        }

        // Método para agregar celdas a la lista enlazada
        public void agregarCelda(int fila, int columna, String valor) {
            Celda nuevaCelda = new Celda(fila, columna, valor);
            if (nodo == null) {
                nodo = nuevaCelda;
            } else {
                Celda temp = nodo;
                while (temp.derecha != null) {
                    temp = temp.derecha;
                }
                temp.derecha = nuevaCelda;
            }
        }

        // Método para recorrer la lista enlazada y mostrar los valores
        public String recorrerLista() {
            StringBuilder resultado = new StringBuilder();
            Celda temp = nodo;
            while (temp != null) {
                if (!temp.valor.isEmpty()) {
                    resultado.append(temp.valor).append(", "); // Agregar sin exceso de espacio
                }
                temp = temp.derecha;
            }
            return resultado.toString().trim(); // Quitar espacio al final
        }
        
        public void limpiarLista() {
            nodo = null;
        }
     
    }
