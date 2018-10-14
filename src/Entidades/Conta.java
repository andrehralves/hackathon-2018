/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import JPA.Dao.JPADao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Matheus
 */
@Entity
@Table(name = "conta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conta.findAll", query = "SELECT c FROM Conta c")
    , @NamedQuery(name = "Conta.findByContaId", query = "SELECT c FROM Conta c WHERE c.contaId = :contaId")
    , @NamedQuery(name = "Conta.findByValorInicial", query = "SELECT c FROM Conta c WHERE c.valorInicial = :valorInicial")
    , @NamedQuery(name = "Conta.findByValorPago", query = "SELECT c FROM Conta c WHERE c.valorPago = :valorPago")
    , @NamedQuery(name = "Conta.findByDataGeracao", query = "SELECT c FROM Conta c WHERE c.dataGeracao = :dataGeracao")
    , @NamedQuery(name = "Conta.findByDataPagamento", query = "SELECT c FROM Conta c WHERE c.dataPagamento = :dataPagamento")})
public class Conta extends JPADao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "conta_id")
    private Integer contaId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_inicial")
    private BigDecimal valorInicial;
    @Column(name = "valor_pago")
    private BigDecimal valorPago;
    @Basic(optional = false)
    @Column(name = "data_geracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataGeracao;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    
    @OneToMany(mappedBy = "contaId")
    private Collection<Pedido> pedidoCollection = new ArrayList();

    public Conta() {
    }

    public Conta(Integer contaId) {
        this.contaId = contaId;
    }

    public Conta(BigDecimal valorInicial, BigDecimal valorPago, Date dataGeracao, Date dataPagamento) {
        this.valorInicial = valorInicial;
        this.valorPago = valorPago;
        this.dataGeracao = dataGeracao;
        this.dataPagamento = dataPagamento;
    }

    public Conta(Integer contaId, BigDecimal valorInicial, BigDecimal valorPago, Date dataGeracao, Date dataPagamento) {
        this.contaId = contaId;
        this.valorInicial = valorInicial;
        this.valorPago = valorPago;
        this.dataGeracao = dataGeracao;
        this.dataPagamento = dataPagamento;
    }

    public Conta(Integer contaId, BigDecimal valorInicial, Date dataGeracao) {
        this.contaId = contaId;
        this.valorInicial = valorInicial;
        this.dataGeracao = dataGeracao;
    }

    public Integer getContaId() {
        return contaId;
    }

    public void setContaId(Integer contaId) {
        this.contaId = contaId;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollection;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollection = pedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contaId != null ? contaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conta)) {
            return false;
        }
        Conta other = (Conta) object;
        if ((this.contaId == null && other.contaId != null) || (this.contaId != null && !this.contaId.equals(other.contaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return contaId.toString();
    }
    
}
