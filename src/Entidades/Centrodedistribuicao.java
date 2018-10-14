/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import JPA.Dao.JPADao;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Matheus
 */
@Entity
@Table(name = "centrodedistribuicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Centrodedistribuicao.findAll", query = "SELECT c FROM Centrodedistribuicao c")
    , @NamedQuery(name = "Centrodedistribuicao.findByCentroId", query = "SELECT c FROM Centrodedistribuicao c WHERE c.centroId = :centroId")
    , @NamedQuery(name = "Centrodedistribuicao.findByDetalhes", query = "SELECT c FROM Centrodedistribuicao c WHERE c.detalhes = :detalhes")})
public class Centrodedistribuicao extends JPADao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "centro_id")
    private Integer centroId;
    @Column(name = "detalhes")
    private String detalhes;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id")
    @ManyToOne(optional = false)
    private Pessoa pessoaId;
    
    @OneToMany(mappedBy = "centroId")
    private Collection<Movimentacao> movimentacaoCollection;

    public Centrodedistribuicao() {
    }

    public Centrodedistribuicao(Integer centroId) {
        this.centroId = centroId;
    }

    public Centrodedistribuicao(String detalhes) {
        this.detalhes = detalhes;
    }

    public Centrodedistribuicao(Integer centroId, String detalhes) {
        this.centroId = centroId;
        this.detalhes = detalhes;
    }

    public Integer getCentroId() {
        return centroId;
    }

    public void setCentroId(Integer centroId) {
        this.centroId = centroId;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    @XmlTransient
    public Collection<Movimentacao> getMovimentacaoCollection() {
        return movimentacaoCollection;
    }

    public void setMovimentacaoCollection(Collection<Movimentacao> movimentacaoCollection) {
        this.movimentacaoCollection = movimentacaoCollection;
    }

    public Pessoa getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Pessoa pessoaId) {
        this.pessoaId = pessoaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (centroId != null ? centroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centrodedistribuicao)) {
            return false;
        }
        Centrodedistribuicao other = (Centrodedistribuicao) object;
        if ((this.centroId == null && other.centroId != null) || (this.centroId != null && !this.centroId.equals(other.centroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return centroId.toString();
    }
    
}
