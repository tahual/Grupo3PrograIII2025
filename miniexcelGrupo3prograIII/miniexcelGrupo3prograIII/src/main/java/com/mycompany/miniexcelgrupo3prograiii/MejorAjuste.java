package com.mycompany.miniexcelgrupo3prograiii;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class MejorAjuste {

    static class Bloque {

        int tamaño;
        boolean ocupado;
        byte[] memoriaAsignada = null;

        Bloque(int tamaño) {
            this.tamaño = tamaño;
            this.ocupado = false;
        }

        @Override
        public String toString() {
            return "[" + tamaño + " KB, " + (ocupado ? "Ocupado" : "Libre") + "]";
        }
    }

    static class Proceso {

        int tamaño;

        Proceso(int tamaño) {
            this.tamaño = tamaño;
        }
    }

    public static void ejecutar() {
        System.out.println("\n=== Mejor Ajuste (Simulacion de memoria) ===");

        List<Bloque> bloques = new ArrayList<>(List.of(
                new Bloque(100),
                new Bloque(500),
                new Bloque(200),
                new Bloque(300),
                new Bloque(600)
        ));

        List<Proceso> procesos = new ArrayList<>(List.of(
                new Proceso(212),
                new Proceso(417),
                new Proceso(112),
                new Proceso(426)
        ));

        System.out.println("== ESTADO INICIAL DE LOS BLOQUES DE MEMORIA ==");
        mostrarBloques(bloques);

        System.out.println("\n== ASIGNACION DE PROCESOS UTILIZANDO MEJOR AJUSTE ==");
        for (Proceso proceso : procesos) {
            int mejorIndice = -1;
            int menorDiferencia = Integer.MAX_VALUE;

            for (int i = 0; i < bloques.size(); i++) {
                Bloque bloque = bloques.get(i);
                if (!bloque.ocupado && bloque.tamaño >= proceso.tamaño) {
                    int diferencia = bloque.tamaño - proceso.tamaño;
                    if (diferencia < menorDiferencia) {
                        menorDiferencia = diferencia;
                        mejorIndice = i;
                    }
                }
            }

            if (mejorIndice != -1) {
                Bloque seleccionado = bloques.get(mejorIndice);
                seleccionado.ocupado = true;

                // Simula la asignación de memoria (KB * 1024 bytes)
                seleccionado.memoriaAsignada = new byte[proceso.tamaño * 1024];

                System.out.println("Proceso de " + proceso.tamaño + " KB → bloque de " + seleccionado.tamaño + " KB");
            } else {
                System.out.println("Proceso de " + proceso.tamaño + " KB → ❌ No se pudo asignar");
            }

            // Pequeña pausa para ver en VisualVM en tiempo real
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n== ESTADO FINAL DE LOS BLOQUES DE MEMORIA ==");
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
