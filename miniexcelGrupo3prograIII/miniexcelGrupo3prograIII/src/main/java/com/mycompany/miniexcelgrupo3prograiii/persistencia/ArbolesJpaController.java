/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii.persistencia;

import com.mycompany.miniexcelgrupo3prograiii.bd.Arboles;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author USUARIO
 */
public class ArbolesJpaController implements Serializable {

    private EntityManager em;

    public ArbolesJpaController(EntityManager em) {
        this.em = em;
    }

    public void create(Arboles arbol) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(arbol);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public void edit(Arboles arbol) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(arbol);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public void remove(Arboles arbol) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(arbol) ? arbol : em.merge(arbol));
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public Arboles find(Integer id) {
        return em.find(Arboles.class, id);
    }

    public List<Arboles> findAll() {
        return em.createQuery("SELECT a FROM Arboles a", Arboles.class).getResultList();
    }
}
