/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii.bd;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "nodos", catalog = "arbolesdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Nodos.findAll", query = "SELECT n FROM Nodos n")})
public class Nodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNodo", nullable = false)
    private Integer idNodo;
    @Basic(optional = false)
    @Column(name = "valor", nullable = false)
    private int valor;
    @JoinColumn(name = "idArbol", referencedColumnName = "idArbol")
    @ManyToOne(fetch = FetchType.LAZY)
    private Arboles idArbol;

    public Nodos() {
    }

    public Nodos(Integer idNodo) {
        this.idNodo = idNodo;
    }

    public Nodos(Integer idNodo, int valor) {
        this.idNodo = idNodo;
        this.valor = valor;
    }

    public Integer getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(Integer idNodo) {
        this.idNodo = idNodo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Arboles getIdArbol() {
        return idArbol;
    }

    public void setIdArbol(Arboles idArbol) {
        this.idArbol = idArbol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNodo != null ? idNodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodos)) {
            return false;
        }
        Nodos other = (Nodos) object;
        if ((this.idNodo == null && other.idNodo != null) || (this.idNodo != null && !this.idNodo.equals(other.idNodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.miniexcelgrupo3prograiii.bd.Nodos[ idNodo=" + idNodo + " ]";
    }

    @Transient
    private Nodos izquierda;

    @Transient
    private Nodos derecha;

    @Transient
    private int altura = 1;

    public Nodos getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Nodos izquierda) {
        this.izquierda = izquierda;
    }

    public Nodos getDerecha() {
        return derecha;
    }

    public void setDerecha(Nodos derecha) {
        this.derecha = derecha;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
