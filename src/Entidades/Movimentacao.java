/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import JPA.Dao.JPADao;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Matheus
 */
@Entity
@Table(name = "movimentacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimentacao.findAll", query = "SELECT m FROM Movimentacao m")
    , @NamedQuery(name = "Movimentacao.findByMovimentacaoId", query = "SELECT m FROM Movimentacao m WHERE m.movimentacaoId = :movimentacaoId")})
public class Movimentacao extends JPADao implements Serializable {

    @Lob
    @Column(name = "imagem")
    private byte[] imagem;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "movimentacao_id")
    private Integer movimentacaoId;
    @JoinColumn(name = "centro_id", referencedColumnName = "centro_id")
    @ManyToOne
    private Centrodedistribuicao centroId;
    @JoinColumn(name = "pedido_id", referencedColumnName = "pedido_id")
    @ManyToOne(optional = false)
    private Pedido pedidoId;
    @JoinColumn(name = "pessoa_motorista_id", referencedColumnName = "pessoa_id")
    @ManyToOne(optional = false)
    private Pessoa pessoaMotoristaId;
    @JoinColumn(name = "veiculo_id", referencedColumnName = "veiculo_id")
    @ManyToOne
    private Veiculo veiculoId;

    public Movimentacao() {
    }

    public Movimentacao(Integer movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    public Movimentacao(byte[] imagem) {
        this.imagem = imagem;
    }

    public Movimentacao(Integer movimentacaoId, byte[] imagem) {
        this.movimentacaoId = movimentacaoId;
        this.imagem = imagem;
    }

    public Integer getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(Integer movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }


    public Centrodedistribuicao getCentroId() {
        return centroId;
    }

    public void setCentroId(Centrodedistribuicao centroId) {
        this.centroId = centroId;
    }

    public Pedido getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Pedido pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Pessoa getPessoaMotoristaId() {
        return pessoaMotoristaId;
    }

    public void setPessoaMotoristaId(Pessoa pessoaMotoristaId) {
        this.pessoaMotoristaId = pessoaMotoristaId;
    }

    public Veiculo getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Veiculo veiculoId) {
        this.veiculoId = veiculoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (movimentacaoId != null ? movimentacaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimentacao)) {
            return false;
        }
        Movimentacao other = (Movimentacao) object;
        if ((this.movimentacaoId == null && other.movimentacaoId != null) || (this.movimentacaoId != null && !this.movimentacaoId.equals(other.movimentacaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return movimentacaoId.toString();
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
}
