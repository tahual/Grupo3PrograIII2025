/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.miniexcelgrupo3prograiii.bd;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "tipoarbol", catalog = "arbolesdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Tipoarbol.findAll", query = "SELECT t FROM Tipoarbol t")})
public class Tipoarbol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipoArbol", nullable = false)
    private Integer idTipoArbol;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false)
    private int estado;
    @OneToMany(mappedBy = "idTipoArbol", fetch = FetchType.LAZY)
    private List<Arboles> arbolesList;

    public Tipoarbol() {
    }

    public Tipoarbol(Integer idTipoArbol) {
        this.idTipoArbol = idTipoArbol;
    }

    public Tipoarbol(Integer idTipoArbol, String nombre, int estado) {
        this.idTipoArbol = idTipoArbol;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getIdTipoArbol() {
        return idTipoArbol;
    }

    public void setIdTipoArbol(Integer idTipoArbol) {
        this.idTipoArbol = idTipoArbol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<Arboles> getArbolesList() {
        return arbolesList;
    }

    public void setArbolesList(List<Arboles> arbolesList) {
        this.arbolesList = arbolesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoArbol != null ? idTipoArbol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoarbol)) {
            return false;
        }
        Tipoarbol other = (Tipoarbol) object;
        if ((this.idTipoArbol == null && other.idTipoArbol != null) || (this.idTipoArbol != null && !this.idTipoArbol.equals(other.idTipoArbol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.miniexcelgrupo3prograiii.bd.Tipoarbol[ idTipoArbol=" + idTipoArbol + " ]";
    }
    
}
