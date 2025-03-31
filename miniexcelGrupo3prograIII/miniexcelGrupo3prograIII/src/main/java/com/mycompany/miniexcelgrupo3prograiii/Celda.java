/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

/**
 *
 * @author Marlon Cuco
 */
    public class Celda {
        int fila, columna;
        String valor;
        Celda derecha, abajo;

        public Celda(int fila, int columna, String valor) {
            this.fila = fila;
            this.columna = columna;
            this.valor = valor;
            this.derecha = null;
            this.abajo = null;
        }
    }