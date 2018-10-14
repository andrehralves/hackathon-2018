/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Utils.Acao.ButtonAcao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "veiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Veiculo.findAll", query = "SELECT v FROM Veiculo v")
    , @NamedQuery(name = "Veiculo.findByVeiculoId", query = "SELECT v FROM Veiculo v WHERE v.veiculoId = :veiculoId")
    , @NamedQuery(name = "Veiculo.findByNome", query = "SELECT v FROM Veiculo v WHERE v.nome = :nome")
    , @NamedQuery(name = "Veiculo.findByDescricao", query = "SELECT v FROM Veiculo v WHERE v.descricao = :descricao")})
public class Veiculo extends ButtonAcao<Veiculo> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "veiculo_id")
    private Integer veiculoId;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    
    @OneToMany(mappedBy = "veiculoId")
    private Collection<Movimentacao> movimentacaoCollection = new ArrayList();
    @OneToMany(mappedBy = "veiculoId")
    private Collection<Pessoa> pessoaCollection = new ArrayList();

    public Veiculo() {
    }

    public Veiculo(Integer veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Veiculo(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Veiculo(Integer veiculoId, String nome, String descricao) {
        this.veiculoId = veiculoId;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Veiculo(Integer veiculoId, String nome) {
        this.veiculoId = veiculoId;
        this.nome = nome;
    }

    public Integer getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Integer veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public Collection<Movimentacao> getMovimentacaoCollection() {
        return movimentacaoCollection;
    }

    public void setMovimentacaoCollection(Collection<Movimentacao> movimentacaoCollection) {
        this.movimentacaoCollection = movimentacaoCollection;
    }

    @XmlTransient
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (veiculoId != null ? veiculoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veiculo)) {
            return false;
        }
        Veiculo other = (Veiculo) object;
        if ((this.veiculoId == null && other.veiculoId != null) || (this.veiculoId != null && !this.veiculoId.equals(other.veiculoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return veiculoId.toString();
    }

    @Override
    protected void AddNodes() {
        addNode("Button", "rem", "Remover");
    }
    
}
