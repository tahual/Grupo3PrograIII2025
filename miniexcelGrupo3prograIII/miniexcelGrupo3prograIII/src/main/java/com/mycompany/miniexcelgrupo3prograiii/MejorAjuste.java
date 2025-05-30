package com.mycompany.miniexcelgrupo3prograiii;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USUARIO
 */
public class MejorAjuste {

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
            boolean asignado = asignarMejorAjuste(proceso);

            if (!asignado) {
                System.out.println("No hay suficiente memoria para asignar el proceso.");
            } else {
                System.out.println("Proceso asignado exitosamente.");
            }
        }

        System.out.println("Saliendo del submenú de Mejor Ajuste.");
    }

    private static void inicializarBloques() {
        bloques.clear();
        bloques.add(new Memoria.Bloque(512));
        bloques.add(new Memoria.Bloque(1024));
        bloques.add(new Memoria.Bloque(256));
        bloques.add(new Memoria.Bloque(2048));
        bloques.add(new Memoria.Bloque(768));
    }

    private static boolean asignarMejorAjuste(Memoria.Proceso proceso) {
        Memoria.Bloque mejorBloque = null;

        for (Memoria.Bloque bloque : bloques) {
            if (!bloque.ocupado && bloque.tamano >= proceso.tamano) {
                if (mejorBloque == null || bloque.tamano < mejorBloque.tamano) {
                    mejorBloque = bloque;
                }
            }
        }

        if (mejorBloque != null) {
            mejorBloque.memoriaAsignada = new byte[proceso.tamano * 1024]; // Reservamos espacio real en memoria
            mejorBloque.ocupado = true;
            return true;
        }

        return false;
    }

    private static void mostrarBloques() {
        for (int i = 0; i < bloques.size(); i++) {
            System.out.println("Bloque " + (i + 1) + ": " + bloques.get(i));
        }
    }
}