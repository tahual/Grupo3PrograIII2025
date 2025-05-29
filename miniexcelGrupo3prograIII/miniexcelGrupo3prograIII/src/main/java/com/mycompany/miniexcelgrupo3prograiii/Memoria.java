/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

/**
 *
 * @author Marlon Cuco
 */
public class Memoria {
    public static class Bloque {
        int tamano;
        boolean ocupado;
        byte[] memoriaAsignada = null;

        public Bloque(int tamano) {
            this.tamano = tamano;
            this.ocupado = false;
        }

        @Override
        public String toString() {
            if (ocupado) {
                return "[" + tamano + " KB, Asignado]";
            } else {
                return "[" + tamano + " KB, Disponible]";
            }
        }
    }

    public static class Proceso {
        int tamano;

        public Proceso(int tamano) {
            this.tamano = tamano;
        }
    }
}
