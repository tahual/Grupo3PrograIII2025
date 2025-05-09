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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "arboles", catalog = "arbolesdb", schema = "")
@NamedQueries({
    @NamedQuery(name = "Arboles.findAll", query = "SELECT a FROM Arboles a")})
public class Arboles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArbol", nullable = false)
    private Integer idArbol;
    @Basic(optional = false)
    @Column(name = "nombreArbol", nullable = false, length = 100)
    private String nombreArbol;
    @JoinColumn(name = "idTipoArbol", referencedColumnName = "idTipoArbol")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tipoarbol idTipoArbol;
    @OneToMany(mappedBy = "idArbol", fetch = FetchType.LAZY)
    private List<Nodos> nodosList;

    public Arboles() {
    }

    public Arboles(Integer idArbol) {
        this.idArbol = idArbol;
    }

    public Arboles(Integer idArbol, String nombreArbol) {
        this.idArbol = idArbol;
        this.nombreArbol = nombreArbol;
    }

    public Integer getIdArbol() {
        return idArbol;
    }

    public void setIdArbol(Integer idArbol) {
        this.idArbol = idArbol;
    }

    public String getNombreArbol() {
        return nombreArbol;
    }

    public void setNombreArbol(String nombreArbol) {
        this.nombreArbol = nombreArbol;
    }

    public Tipoarbol getIdTipoArbol() {
        return idTipoArbol;
    }

    public void setIdTipoArbol(Tipoarbol idTipoArbol) {
        this.idTipoArbol = idTipoArbol;
    }

    public List<Nodos> getNodosList() {
        return nodosList;
    }

    public void setNodosList(List<Nodos> nodosList) {
        this.nodosList = nodosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArbol != null ? idArbol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arboles)) {
            return false;
        }
        Arboles other = (Arboles) object;
        if ((this.idArbol == null && other.idArbol != null) || (this.idArbol != null && !this.idArbol.equals(other.idArbol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.miniexcelgrupo3prograiii.bd.Arboles[ idArbol=" + idArbol + " ]";
    }
    
}
