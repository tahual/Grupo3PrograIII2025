/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.miniexcelgrupo3prograiii;

import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author danyt
 */
public class MiniexcelGrupo3prograIII {
     public static void main(String[] args){
     Scanner scanner = new Scanner(System.in); 
     int opcion= 0; 

    do{
        
        System.out.println("Ingrese el trabajo que quiere realizar: ");
        System.out.println("1. Arbol Binario) ");
        System.out.println("2. AVL) ");
        System.out.println("3. Mini Excel");
        System.out.println("4. Salir");
        
        try{
        
        opcion = scanner.nextInt(); 
        scanner.nextLine(); 
        
    
    switch(opcion){
        case 1:
            break;
        case 2: 
            break;
        case 3:
               // Crear la ventana principal
        JFrame frame = new JFrame("Mini Excel");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Crear una instancia de MiniExcel y obtener el panel
        MiniExcel miniExcel = new MiniExcel();
        JPanel panel = miniExcel.crearPanel();

        // Agregar el panel a la ventana y hacerla visible
        frame.add(panel);
        frame.setVisible(true);
            break;
        case 4:
            System.out.println("gracias por usar nuestro sistema");
            break;
            
            default:
                System.out.println("la opcion que ingreso no es valida");
            
        }
        
        }catch(Exception e){
            System.out.println("Errror ingrese un numero valido. ");
            scanner.nextLine(); 
        }
    
    }while(opcion != 4); 
    
    
    }
}

