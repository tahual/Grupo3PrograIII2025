/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

/**
 *
 * @author danyt
 */
public class ListaEnlazada {
    private Celda primerCelda; 
    
    public ListaEnlazada (){
        this.primerCelda = null; 
    }
    
     // Método para insertar un valor en una celda específica
    public void insertar(int fila, int columna, Object valor ){
        if(primerCelda == null ){
            primerCelda = new Celda(valor);
            return;
        }
        
        Celda actual = primerCelda; 
        for(int i=0; i< fila ; i++){
            if(actual.getAbajo() == null){
                actual.setAbajo(new Celda(null));
            }
            actual = actual.getAbajo(); 
        }
        
        for(int j =0 ; j < columna; j++){
            if(actual.getDerecha()==null){
                actual.setDerecha(new Celda(null));
            }
            actual = actual.getDerecha(); 
        }
        
        actual.setValor(valor);
    }        
    
    
    // Método para buscar una celda en una posición dada
    public Celda buscar(int fila, int columan){
        Celda actual = primerCelda;
        for (int i=0; i< fila && actual != null ; i++){
            actual = actual.getAbajo(); 
        }
        for(int j = 0; j< columan && actual != null; j++){
            actual= actual.getDerecha(); 
        }
        return actual; 
    }
    
    // Método para eliminar una celda (poniendo su valor en null y ajustando enlaces)
    
    public void eliminar (int fila, int columna){
        Celda actual = buscar(fila, columna); 
        if(actual != null){
            actual.setValor(null);
        }
    }
        
    // Método para mostrar la hoja de cálculo en consola
    // este es para hacer pruebas sin el usa de la interfas grafica que le corresponde a mi compañero
    
    public void mostrar(){
        Celda filaActual = primerCelda;
        while(filaActual != null){
            Celda columnaActual = filaActual; 
            while(columnaActual != null){
                System.out.print((columnaActual.getValor() != null ? columnaActual.getValor() : "")+ "\t");
                columnaActual = columnaActual.getDerecha();
            }
            System.out.println();
            filaActual = filaActual.getAbajo(); 
            
            }        
        
        }

    }
    
    
    

