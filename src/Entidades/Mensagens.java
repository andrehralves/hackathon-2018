/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import JPA.Dao.JPADao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "mensagens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mensagens.findAll", query = "SELECT m FROM Mensagens m")
    , @NamedQuery(name = "Mensagens.findByMensagemId", query = "SELECT m FROM Mensagens m WHERE m.mensagemId = :mensagemId")
    , @NamedQuery(name = "Mensagens.findByDescricao", query = "SELECT m FROM Mensagens m WHERE m.descricao = :descricao")})
public class Mensagens extends JPADao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mensagem_id")
    private Integer mensagemId;
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "pessoa_id")
    @ManyToOne(optional = false)
    private Pessoa pessoaId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mensagemId")
    private Collection<Pedido> pedidoCollection = new ArrayList();

    public Mensagens() {
    }

    public Mensagens(Integer mensagemId) {
        this.mensagemId = mensagemId;
    }

    public Mensagens(String descricao) {
        this.descricao = descricao;
    }

    public Mensagens(Integer mensagemId, String descricao) {
        this.mensagemId = mensagemId;
        this.descricao = descricao;
    }

    public Integer getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Integer mensagemId) {
        this.mensagemId = mensagemId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
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
        hash += (mensagemId != null ? mensagemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mensagens)) {
            return false;
        }
        Mensagens other = (Mensagens) object;
        if ((this.mensagemId == null && other.mensagemId != null) || (this.mensagemId != null && !this.mensagemId.equals(other.mensagemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mensagemId.toString() ;
    }
    
}
