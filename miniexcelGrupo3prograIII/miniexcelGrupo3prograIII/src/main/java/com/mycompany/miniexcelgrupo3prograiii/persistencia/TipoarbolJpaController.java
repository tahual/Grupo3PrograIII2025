/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii.persistencia;

import com.mycompany.miniexcelgrupo3prograiii.bd.Tipoarbol;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class TipoarbolJpaController {

    private EntityManager em;

    public TipoarbolJpaController(EntityManager em) {
        this.em = em;
    }

    // Método para crear un TipoArbol
    public void create(Tipoarbol tipoarbol) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tipoarbol);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    // Método para editar un TipoArbol
    public void edit(Tipoarbol tipoarbol) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(tipoarbol);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    // Método para eliminar un TipoArbol
    public void remove(Tipoarbol tipoarbol) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(tipoarbol) ? tipoarbol : em.merge(tipoarbol));
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    // Método para encontrar un TipoArbol por su ID
    public Tipoarbol find(Integer id) {
        return em.find(Tipoarbol.class, id);
    }

    // Método para obtener todos los TipoArboles
    public List<Tipoarbol> findAll() {
        return em.createQuery("SELECT t FROM Tipoarbol t", Tipoarbol.class).getResultList();
    }
}
