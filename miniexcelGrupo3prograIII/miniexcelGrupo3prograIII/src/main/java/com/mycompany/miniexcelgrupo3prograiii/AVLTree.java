/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii;

import com.mycompany.miniexcelgrupo3prograiii.bd.Arboles;
import com.mycompany.miniexcelgrupo3prograiii.bd.Nodos;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.ArbolesJpaController;
import com.mycompany.miniexcelgrupo3prograiii.persistencia.NodosJpaController;

/**
 *
 * @author USUARIO
 */
public class AVLTree {

    private Nodos raiz;
    private Arboles arbolActual;

    public AVLTree(Arboles arbol) {
        this.arbolActual = arbol;
        this.raiz = null;
    }

    public AVLTree() {
        this.arbolActual = new Arboles(); // Por defecto si no se pasa uno
        this.raiz = null;
    }

    public void insertarNodo(int valor, NodosJpaController nodosController) {
        if (existeNodo(raiz, valor)) {
            System.out.println("El valor " + valor + " ya existe en el arbol.");
            return;
        }

        Nodos nuevoNodo = new Nodos();
        nuevoNodo.setValor(valor);
        nuevoNodo.setIdArbol(arbolActual);
        nodosController.create(nuevoNodo);

        raiz = insertarEnArbolAVL(raiz, nuevoNodo);
        System.out.println("Nodo insertado: " + valor);
    }

    private boolean existeNodo(Nodos nodo, int valor) {
        if (nodo == null) return false;
        if (nodo.getValor() == valor) return true;
        return valor < nodo.getValor()
                ? existeNodo(nodo.getIzquierda(), valor)
                : existeNodo(nodo.getDerecha(), valor);
    }

    private int altura(Nodos nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private int getBalance(Nodos nodo) {
        return (nodo == null) ? 0 : altura(nodo.getIzquierda()) - altura(nodo.getDerecha());
    }

    private Nodos rotacionDerecha(Nodos y) {
        Nodos x = y.getIzquierda();
        Nodos T2 = x.getDerecha();

        x.setDerecha(y);
        y.setIzquierda(T2);

        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);

        return x;
    }

    private Nodos rotacionIzquierda(Nodos x) {
        Nodos y = x.getDerecha();
        Nodos T2 = y.getIzquierda();

        y.setIzquierda(x);
        x.setDerecha(T2);

        x.setAltura(Math.max(altura(x.getIzquierda()), altura(x.getDerecha())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierda()), altura(y.getDerecha())) + 1);

        return y;
    }

    private Nodos insertarEnArbolAVL(Nodos nodo, Nodos nuevoNodo) {
        if (nodo == null) {
            return nuevoNodo;
        }

        if (nuevoNodo.getValor() < nodo.getValor()) {
            nodo.setIzquierda(insertarEnArbolAVL(nodo.getIzquierda(), nuevoNodo));
        } else if (nuevoNodo.getValor() > nodo.getValor()) {
            nodo.setDerecha(insertarEnArbolAVL(nodo.getDerecha(), nuevoNodo));
        } else {
            return nodo;
        }

        nodo.setAltura(Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())) + 1);

        int balance = getBalance(nodo);

        if (balance > 1 && nuevoNodo.getValor() < nodo.getIzquierda().getValor()) {
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && nuevoNodo.getValor() > nodo.getDerecha().getValor()) {
            return rotacionIzquierda(nodo);
        }

        if (balance > 1 && nuevoNodo.getValor() > nodo.getIzquierda().getValor()) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && nuevoNodo.getValor() < nodo.getDerecha().getValor()) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    public void eliminarNodo(int valor, NodosJpaController nodosController) {
        Nodos nodoEliminar = buscarNodoPorValor(raiz, valor);

        if (nodoEliminar == null) {
            System.out.println("El nodo con valor " + valor + " no existe.");
            return;
        }

        raiz = eliminarEnArbolAVL(raiz, valor);

        try {
            nodosController.destroy(nodoEliminar.getIdNodo());
            System.out.println("Nodo eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar nodo de la base de datos: " + e.getMessage());
        }
    }

    private Nodos eliminarEnArbolAVL(Nodos nodo, int valor) {
        if (nodo == null) return null;

        if (valor < nodo.getValor()) {
            nodo.setIzquierda(eliminarEnArbolAVL(nodo.getIzquierda(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecha(eliminarEnArbolAVL(nodo.getDerecha(), valor));
        } else {
            if (nodo.getIzquierda() == null) return nodo.getDerecha();
            else if (nodo.getDerecha() == null) return nodo.getIzquierda();

            Nodos sucesor = minValue(nodo.getDerecha());
            nodo.setValor(sucesor.getValor());
            nodo.setDerecha(eliminarEnArbolAVL(nodo.getDerecha(), sucesor.getValor()));
        }

        nodo.setAltura(Math.max(altura(nodo.getIzquierda()), altura(nodo.getDerecha())) + 1);

        int balance = getBalance(nodo);

        if (balance > 1 && getBalance(nodo.getIzquierda()) >= 0)
            return rotacionDerecha(nodo);

        if (balance > 1 && getBalance(nodo.getIzquierda()) < 0) {
            nodo.setIzquierda(rotacionIzquierda(nodo.getIzquierda()));
            return rotacionDerecha(nodo);
        }

        if (balance < -1 && getBalance(nodo.getDerecha()) <= 0)
            return rotacionIzquierda(nodo);

        if (balance < -1 && getBalance(nodo.getDerecha()) > 0) {
            nodo.setDerecha(rotacionDerecha(nodo.getDerecha()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    private Nodos buscarNodoPorValor(Nodos nodo, int valor) {
        if (nodo == null) return null;
        if (valor == nodo.getValor()) return nodo;
        return (valor < nodo.getValor())
                ? buscarNodoPorValor(nodo.getIzquierda(), valor)
                : buscarNodoPorValor(nodo.getDerecha(), valor);
    }

    private Nodos minValue(Nodos nodo) {
        Nodos actual = nodo;
        while (actual.getIzquierda() != null)
            actual = actual.getIzquierda();
        return actual;
    }

    public void guardarAVL(ArbolesJpaController arbolesController) {
        try {
            if (arbolActual.getIdArbol() == null) {
                arbolActual.setNombreArbol("Árbol AVL guardado");
                arbolesController.create(arbolActual);
            } else {
                arbolesController.edit(arbolActual);
            }
            System.out.println("Árbol guardado en la base de datos.");
        } catch (Exception e) {
            System.out.println("Error al guardar árbol: " + e.getMessage());
        }
    }

    public Nodos getRaiz() {
        return raiz;
    }
}