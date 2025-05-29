/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.mycompany.miniexcelgrupo3prograiii.Memoria.Bloque;
import com.mycompany.miniexcelgrupo3prograiii.Memoria.Proceso;


/**
 *
 * @author Marlon Cuco
 */


public class PeorAjuste {

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== Simulacion: Peor Ajuste en Administracion de Memoria ===");

        System.out.println("Ingrese la cantidad de bloques de memoria disponibles:");
        int numBloques = sc.nextInt();
        List<Bloque> bloques = new ArrayList<>();

        for (int i = 0; i < numBloques; i++) {
            System.out.print("Tamano del bloque #" + (i + 1) + " (KB): ");
            int tamano = sc.nextInt();
            bloques.add(new Bloque(tamano));
        }

        System.out.println("Ingrese la cantidad de procesos a asignar:");
        int numProcesos = sc.nextInt();
        List<Proceso> procesos = new ArrayList<>();

        for (int i = 0; i < numProcesos; i++) {
            System.out.print("Tamano del proceso #" + (i + 1) + " (KB): ");
            int tamano = sc.nextInt();
            procesos.add(new Proceso(tamano));
        }

        System.out.println("\nEstado inicial de los bloques de memoria:");
        mostrarBloques(bloques);

        System.out.println("\nAsignando procesos con Peor Ajuste...");
        for (Proceso proceso : procesos) {
            int peorIndice = -1;
            int mayorDiferencia = -1;

            for (int i = 0; i < bloques.size(); i++) {
                Bloque bloque = bloques.get(i);
                if (!bloque.ocupado && bloque.tamano >= proceso.tamano) {
                    int diferencia = bloque.tamano - proceso.tamano;
                    if (diferencia > mayorDiferencia) {
                        mayorDiferencia = diferencia;
                        peorIndice = i;
                    }
                }
            }

            if (peorIndice != -1) {
                Bloque seleccionado = bloques.get(peorIndice);
                seleccionado.ocupado = true;
                seleccionado.memoriaAsignada = new byte[proceso.tamano * 1024];
                System.out.println("Proceso de " + proceso.tamano + " KB asignado al bloque de " + seleccionado.tamano + " KB.");
            } else {
                System.out.println("Proceso de " + proceso.tamano + " KB no pudo ser asignado (sin bloque adecuado).");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nEstado final de los bloques de memoria:");
        mostrarBloques(bloques);

      // Evita que termine el programa para que VisualVM pueda seguir monitoreando
        System.out.println("\nPresiona Ctrl+C para terminar la simulación y ver en VisualVM...");
        while (true) {
        } // Espera infinita para que puedas analizarlo en VisualVM
    }


    private static void mostrarBloques(List<Bloque> bloques) {
        for (int i = 0; i < bloques.size(); i++) {
            System.out.println("Bloque " + (i + 1) + ": " + bloques.get(i));
        }
    }
}
