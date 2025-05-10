/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii.persistencia;

import com.mycompany.miniexcelgrupo3prograiii.bd.Nodos;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author USUARIO
 */
public class NodosJpaController {

    private EntityManager em;

    public NodosJpaController(EntityManager em) {
        this.em = em;
    }

    // Método para encontrar un nodo por su valor
    public Nodos findNodos(int valor) {
        try {
            TypedQuery<Nodos> query = em.createQuery("SELECT n FROM Nodos n WHERE n.valor = :valor", Nodos.class);
            query.setParameter("valor", valor);
            return query.getSingleResult();  // Retorna el nodo si se encuentra
        } catch (Exception e) {
            return null;
        }
    }

    public void create(Nodos nodo) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(nodo);  // Persistir el nuevo nodo
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public void destroy(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Nodos nodo = em.find(Nodos.class, id);  // Buscar el nodo por id
            if (nodo != null) {
                em.remove(nodo);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    // Método para encontrar un nodo por su id
    public Nodos findNodosById(int id) {
        return em.find(Nodos.class, id);  // Buscar nodo por id
    }
}