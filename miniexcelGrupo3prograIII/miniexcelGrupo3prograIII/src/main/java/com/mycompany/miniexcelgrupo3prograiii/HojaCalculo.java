/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

/**
 *
 * @author Marlon Cuco
 */
class HojaCalculo {
    private ListaEnlazada datos;

    public HojaCalculo() {
        this.datos = new ListaEnlazada();
    }

    public void insertar(int fila, int columna, String valor) {
        datos.insertar(fila, columna, valor);
    }

    public String obtener(int fila, int columna) {
        return datos.obtener(fila, columna);
    }

    public void eliminar(int fila, int columna) {
        datos.eliminar(fila, columna);
    }

        public String mostrar() {
        return datos.recorrerLista();  // Llama al método de la otra clase
    }

    
}
    
