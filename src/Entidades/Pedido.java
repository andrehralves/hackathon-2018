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
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByPedidoId", query = "SELECT p FROM Pedido p WHERE p.pedidoId = :pedidoId")
    , @NamedQuery(name = "Pedido.findByMedida", query = "SELECT p FROM Pedido p WHERE p.medida = :medida")
    , @NamedQuery(name = "Pedido.findByPeso", query = "SELECT p FROM Pedido p WHERE p.peso = :peso")
    , @NamedQuery(name = "Pedido.findByStatus", query = "SELECT p FROM Pedido p WHERE p.status = :status")})
public class Pedido extends JPADao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pedido_id")
    private Integer pedidoId;
    @Column(name = "medida")
    private String medida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private BigDecimal peso;
    @Column(name = "status")
    private Boolean status;
    @JoinColumn(name = "conta_id", referencedColumnName = "conta_id")
    @ManyToOne
    private Conta contaId;
    @JoinColumn(name = "mensagem_id", referencedColumnName = "mensagem_id")
    @ManyToOne(optional = false)
    private Mensagens mensagemId;
    @JoinColumn(name = "pessoa_origem_id", referencedColumnName = "pessoa_id")
    @ManyToOne(optional = false)
    private Pessoa pessoaOrigemId;
    @JoinColumn(name = "pessoa_destino_id", referencedColumnName = "pessoa_id")
    @ManyToOne(optional = false)
    private Pessoa pessoaDestinoId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidoId")
    private Collection<Movimentacao> movimentacaoCollection = new ArrayList();

    public Pedido() {
    }

    public Pedido(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Pedido(String medida, BigDecimal peso, Boolean status) {
        this.medida = medida;
        this.peso = peso;
        this.status = status;
    }

    public Pedido(Integer pedidoId, String medida, BigDecimal peso, Boolean status) {
        this.pedidoId = pedidoId;
        this.medida = medida;
        this.peso = peso;
        this.status = status;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Movimentacao> getMovimentacaoCollection() {
        return movimentacaoCollection;
    }

    public void setMovimentacaoCollection(Collection<Movimentacao> movimentacaoCollection) {
        this.movimentacaoCollection = movimentacaoCollection;
    }

    public Conta getContaId() {
        return contaId;
    }

    public void setContaId(Conta contaId) {
        this.contaId = contaId;
    }

    public Mensagens getMensagemId() {
        return mensagemId;
    }

    public void setMensagemId(Mensagens mensagemId) {
        this.mensagemId = mensagemId;
    }

    public Pessoa getPessoaOrigemId() {
        return pessoaOrigemId;
    }

    public void setPessoaOrigemId(Pessoa pessoaOrigemId) {
        this.pessoaOrigemId = pessoaOrigemId;
    }
    
    public String getOrigem()
    {
        return pessoaOrigemId.getCep();
    }
    
    public String getOrigemFormatada()
    {
        String cep = pessoaOrigemId.getCep();
        
        if (cep.equals("0")) {
            return "";
        }
        String c = cep;
        if (c.length() != 8) {
            return "";
        }
        return c.substring(0, 5) + "-" + c.substring(5, c.length());    
    }
    
    public String getDestino()
    {
        return pessoaDestinoId.getCep();
    }
    
    public String getDestinoFormatada()
    {
        String cep = pessoaDestinoId.getCep();
        
        if (cep.equals("0")) {
            return "";
        }
        String c = cep;
        if (c.length() != 8) {
            return "";
        }
        return c.substring(0, 5) + "-" + c.substring(5, c.length());  
    }

    public Pessoa getPessoaDestinoId() {
        return pessoaDestinoId;
    }

    public void setPessoaDestinoId(Pessoa pessoaDestinoId) {
        this.pessoaDestinoId = pessoaDestinoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoId != null ? pedidoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.pedidoId == null && other.pedidoId != null) || (this.pedidoId != null && !this.pedidoId.equals(other.pedidoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pedidoId.toString();
    }
    
}
