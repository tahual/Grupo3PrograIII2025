/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.miniexcelgrupo3prograiii;

import com.mycompany.miniexcelgrupo3prograiii.bd.Arboles;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.ArbolesJpaController;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.NodosJpaController;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.TipoarbolJpaController;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
/**
 *
 * @author danyt
 */
public class MiniexcelGrupo3prograIII {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("com.mycompany_miniexcelGrupo3prograIII_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        // Crear controladores para la base de datos
        TipoarbolJpaController tipoArbolController = new TipoarbolJpaController(em);
        ArbolesJpaController arbolesController = new ArbolesJpaController(em);
        NodosJpaController nodosController = new NodosJpaController(em);
      /*    
        // Crear la ventana principal
        JFrame frame = new JFrame("Mini Excel");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear una instancia de MiniExcel y obtener el panel
        MiniExcel miniExcel = new MiniExcel();
        JPanel panel = miniExcel.crearPanel();

        // Agregar el panel a la ventana y hacerla visible
        frame.add(panel);
        frame.setVisible(true);
        */
//------------------------------------------------------------------------------
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Excel");
        System.out.println("2. AVL");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        if (opcion == 2) {
            // Crear y guardar el árbol AVL en la base de datos
            System.out.print("Ingrese el nombre del arbol AVL: ");
            scanner.nextLine();  // Limpiar buffer
            String nombreArbol = scanner.nextLine();

            Arboles nuevoArbol = new Arboles();
            nuevoArbol.setNombreArbol(nombreArbol);
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
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }
}
