/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

/**
 *
 * @author tahual
 */
import com.mycompany.miniexcelgrupo3prograiii.Memoria.Bloque;
import com.mycompany.miniexcelgrupo3prograiii.Memoria.Proceso;
import java.util.*;

public class PrimerAjuste {

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== Simulación: Primer Ajuste en Administración de Memoria ===");

        // Ejemplo práctico
        System.out.println("\nEjemplo práctico:");
        System.out.println("Suponga que un sistema operativo debe asignar procesos a bloques de memoria.");
        System.out.println("Se utilizará el algoritmo de Primer Ajuste: se asigna el proceso al primer bloque libre suficientemente grande.\n");

        System.out.print("Ingrese la cantidad de bloques de memoria disponibles: ");
        int numBloques = sc.nextInt();
        List<Bloque> bloques = new ArrayList<>();

        for (int i = 0; i < numBloques; i++) {
            System.out.print("Tamaño del bloque #" + (i + 1) + " (KB): ");
            int tamano = sc.nextInt();
            bloques.add(new Bloque(tamano));
        }

        System.out.print("Ingrese la cantidad de procesos a asignar: ");
        int numProcesos = sc.nextInt();
        List<Proceso> procesos = new ArrayList<>();

        for (int i = 0; i < numProcesos; i++) {
            System.out.print("Tamaño del proceso #" + (i + 1) + " (KB): ");
            int tamano = sc.nextInt();
            procesos.add(new Proceso(tamano));
        }

        System.out.println("\n=== Estado INICIAL de los bloques de memoria ===");
        mostrarBloques(bloques);

        System.out.println("\nAsignando procesos con Primer Ajuste...");
        for (Proceso proceso : procesos) {
            boolean asignado = false;

            for (Bloque bloque : bloques) {
                if (!bloque.ocupado && bloque.tamano >= proceso.tamano) {
                    bloque.ocupado = true;
                    bloque.memoriaAsignada = new byte[proceso.tamano * 1024];
                    System.out.println("Proceso de " + proceso.tamano + " KB asignado al bloque de " + bloque.tamano + " KB.");
                    asignado = true;
                    break;
                }
            }

            if (!asignado) {
                System.out.println("Proceso de " + proceso.tamano + " KB no pudo ser asignado (sin bloque adecuado).");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n=== Estado FINAL de los bloques de memoria ===");
        mostrarBloques(bloques);

        System.out.println("\nPresiona Ctrl+C para terminar la simulación y ver en VisualVM...");
        while (true) {
        } // Espera infinita para análisis con VisualVM
    }

    private static void mostrarBloques(List<Bloque> bloques) {
        for (int i = 0; i < bloques.size(); i++) {
            System.out.println("Bloque " + (i + 1) + ": " + bloques.get(i));
        }
    }
}

