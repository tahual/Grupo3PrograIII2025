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
    private Object valor;
    private Celda izquierda;
    private Celda derecha;
    private Celda arriba;
    private Celda abajo;

    public Celda(Object valor) {
        this.valor = valor;
        this.izquierda = null;
        this.derecha = null;
        this.arriba = null;
        this.abajo = null;
    }

    public Celda(Object valor, Celda izquierda, Celda derecha, Celda arriba, Celda abajo) {
        this.valor = valor;
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.arriba = arriba;
        this.abajo = abajo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Celda getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Celda izquierda) {
        this.izquierda = izquierda;
    }

    public Celda getDerecha() {
        return derecha;
    }

    public void setDerecha(Celda derecha) {
        this.derecha = derecha;
    }

    public Celda getArriba() {
        return arriba;
    }

    public void setArriba(Celda arriba) {
        this.arriba = arriba;
    }

    public Celda getAbajo() {
        return abajo;
    }

    public void setAbajo(Celda abajo) {
        this.abajo = abajo;
    }
}
