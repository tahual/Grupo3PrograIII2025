package com.mycompany.miniexcelgrupo3prograiii;

import com.mycompany.miniexcelgrupo3prograiii.bd.Arboles;
import com.mycompany.miniexcelgrupo3prograiii.bd.Tipoarbol;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.ArbolesJpaController;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.NodosJpaController;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.TipoarbolJpaController;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author danyt
 */
public class MiniexcelGrupo3prograIII {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_miniexcelGrupo3prograIII_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        TipoarbolJpaController tipoArbolController = new TipoarbolJpaController(em);
        ArbolesJpaController arbolesController = new ArbolesJpaController(em);
        NodosJpaController nodosController = new NodosJpaController(em);

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n1. Mini Excel");
            System.out.println("2. AVL");
            System.out.println("3. Administracion de Memoria");
            System.out.println("0. Salir");
            System.out.print("Ingrese el trabajo que quiere realizar: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        //Lógica para Mini Excel
                        JFrame miniExcelFrame = new JFrame("Mini Excel");
                        miniExcelFrame.setSize(600, 400);
                        miniExcelFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        MiniExcel miniExcel = new MiniExcel();
                        JPanel panel = miniExcel.crearPanel();

                        miniExcelFrame.add(panel);
                        miniExcelFrame.setVisible(true);
                        break;
                    case 2:
                        //Lógica para el Árbol AVL
                        System.out.print("Ingrese el nombre del arbol AVL: ");
                        String nombreArbol = scanner.nextLine();

                        Tipoarbol tipoAVL = new Tipoarbol();
                        tipoAVL.setNombre(nombreArbol);
                        tipoAVL.setEstado(1);//-----------------------------------

                        try {
                            tipoArbolController.create(tipoAVL); // guarda el tipo
                        } catch (Exception e) {
                            System.out.println("Error al crear Tipoarbol: " + e.getMessage());
                            return;
                        }

                        // 2. Crear y guardar el árbol con su tipo
                        Arboles nuevoArbol = new Arboles();
                        nuevoArbol.setNombreArbol(nombreArbol);
                        nuevoArbol.setIdTipoArbol(tipoAVL);

                        try {
                            arbolesController.create(nuevoArbol);
                            System.out.println("Arbol '" + nombreArbol + "' creado con exito.");
                        } catch (Exception e) {
                            System.out.println("Error al crear el árbol: " + e.getMessage());
                            return;
                        }

                        // Instanciar AVLTree con el árbol creado
                        AVLTree avlTree = new AVLTree(nuevoArbol);

                        // Crear el panel gráfico
                        GraficoAVL graficoAVL = new GraficoAVL(avlTree.getRaiz());

                        JFrame frame = new JFrame("Gráfico del Árbol AVL");
                        frame.setSize(600, 400);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(graficoAVL);
                        frame.setVisible(true);

                        // Submenú AVL
                        boolean continuar = true;
                        while (continuar) {
                            System.out.println("\n====== AVL ======");
                            System.out.println("a. Insertar nodo");
                            System.out.println("b. Eliminar nodo");
                            System.out.println("c. Salir");
                            System.out.print("Seleccione una opcion: ");
                            char subOpcion = scanner.next().charAt(0);

                            switch (subOpcion) {
                                case 'a':
                                    System.out.print("Ingrese el valor del nodo: ");
                                    int valorInsertar = scanner.nextInt();
                                    avlTree.insertarNodo(valorInsertar, nodosController);
                                    graficoAVL.setRaiz(avlTree.getRaiz());
                                    break;
                                case 'b':
                                    System.out.print("Ingrese el valor del nodo a eliminar: ");
                                    int valorEliminar = scanner.nextInt();
                                    avlTree.eliminarNodo(valorEliminar, nodosController);
                                    graficoAVL.setRaiz(avlTree.getRaiz());
                                    break;
                                case 'c':
                                    continuar = false;
                                    break;
                                default:
                                    System.out.println("Opcion no valida. Intente de nuevo.");
                            }
                        }
                        break;
                    case 3:
                        boolean enSubmenu = true;
                        while (enSubmenu) {
                            System.out.println("\n== Administracion de Memoria ==");
                            System.out.println("a. Primer ajuste");
                            System.out.println("b. Mejor ajuste");
                            System.out.println("c. Peor ajuste");
                            System.out.println("d. Volver al menu principal");
                            System.out.print("Seleccione una opcion: ");
                            char subopcion = scanner.next().toLowerCase().charAt(0);

                            switch (subopcion) {
                                case 'a':
                                    System.out.println("Opcion aun no implementada!!");
                                    break;
                                case 'b':
                                    MejorAjuste.ejecutar();
                                    break;
                                case 'c':

                                    PeorAjuste.ejecutar();
                                    break;

                                case 'd':
                                    enSubmenu = false;
                                    break;
                                default:
                                    System.out.println("Opcion invalida!! Intente de nuevo");
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Gracias por usar nuestro sistema.");
                        break;
                    default:
                        System.out.println("La opción ingresada no es válida.");
                }

            } catch (Exception e) {
                System.out.println("Error. Ingrese un número válido.");
                scanner.nextLine();
            }
        } while (opcion != 0);
    }
}
