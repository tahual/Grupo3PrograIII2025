/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimerAjuste {

    private static final List<Memoria.Bloque> bloques = new ArrayList<>();

    public static void ejecutar() {
        inicializarBloques();

        Scanner scanner = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
            System.out.println("\n--- Estado actual de memoria ---");
            mostrarBloques();

            System.out.print("Ingrese el tamano del proceso en KB (0 para salir): ");
            int tamProceso = scanner.nextInt();

            if (tamProceso <= 0) {
                seguir = false;
                break;
            }

            Memoria.Proceso proceso = new Memoria.Proceso(tamProceso);
            boolean asignado = asignarPrimerAjuste(proceso);

            if (!asignado) {
                System.out.println("No hay suficiente memoria para asignar el proceso.");
            }
        }

        System.out.println("Saliendo del submenu de Primer Ajuste.");
    }

    private static void inicializarBloques() {
        bloques.clear();
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cuantos bloques de memoria desea ingresar? ");
        int numBloques = scanner.nextInt();

        for (int i = 0; i < numBloques; i++) {
            System.out.print("Tamano del bloque #" + (i + 1) + " en KB: ");
            int tam = scanner.nextInt();
            bloques.add(new Memoria.Bloque(tam));
        }
    }

    private static boolean asignarPrimerAjuste(Memoria.Proceso proceso) {
        for (Memoria.Bloque bloque : bloques) {
            if (!bloque.ocupado && bloque.tamano >= proceso.tamano) {
                bloque.memoriaAsignada = new byte[proceso.tamano * 1024];
                bloque.ocupado = true;
                System.out.println("Proceso de " + proceso.tamano + " KB asignado al bloque de " + bloque.tamano + " KB.");
                return true;
            }
        }
        return false;
    }

    private static void mostrarBloques() {
        for (int i = 0; i < bloques.size(); i++) {
            System.out.println("Bloque " + (i + 1) + ": " + bloques.get(i));
        }
    }
}
