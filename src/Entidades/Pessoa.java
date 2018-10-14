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
import javax.persistence.Lob;
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
@Table(name = "pessoa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoa.findAll", query = "SELECT p FROM Pessoa p")
    , @NamedQuery(name = "Pessoa.findByPessoaId", query = "SELECT p FROM Pessoa p WHERE p.pessoaId = :pessoaId")
    , @NamedQuery(name = "Pessoa.findByNome", query = "SELECT p FROM Pessoa p WHERE p.nome = :nome")
    , @NamedQuery(name = "Pessoa.findByEmail", query = "SELECT p FROM Pessoa p WHERE p.email = :email")
    , @NamedQuery(name = "Pessoa.findBySenha", query = "SELECT p FROM Pessoa p WHERE p.senha = :senha")
    , @NamedQuery(name = "Pessoa.findByCep", query = "SELECT p FROM Pessoa p WHERE p.cep = :cep")
    , @NamedQuery(name = "Pessoa.findByEndereco", query = "SELECT p FROM Pessoa p WHERE p.endereco = :endereco")
    , @NamedQuery(name = "Pessoa.findByComplemento", query = "SELECT p FROM Pessoa p WHERE p.complemento = :complemento")
    , @NamedQuery(name = "Pessoa.findByTelefone", query = "SELECT p FROM Pessoa p WHERE p.telefone = :telefone")
    , @NamedQuery(name = "Pessoa.findByCnpj", query = "SELECT p FROM Pessoa p WHERE p.cnpj = :cnpj")
    , @NamedQuery(name = "Pessoa.findByCpf", query = "SELECT p FROM Pessoa p WHERE p.cpf = :cpf")
    , @NamedQuery(name = "Pessoa.findByEmailSenha", query = "SELECT p FROM Pessoa p WHERE p.email = :email AND p.senha = :senha")})
public class Pessoa extends JPADao implements Serializable, Individuo {

    @Lob
    @Column(name = "imagem")
    private byte[] imagem;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pessoa_id")
    private Integer pessoaId;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "complemento")
    private String complemento;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "cnpj")
    private String cnpj;
    @Column(name = "cpf")
    private String cpf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaMotoristaId")
    private Collection<Movimentacao> movimentacaoCollection;
    @JoinColumn(name = "veiculo_id", referencedColumnName = "veiculo_id")
    @ManyToOne
    private Veiculo veiculoId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaOrigemId")
    private Collection<Pedido> pedidoCollectionOrigem = new ArrayList();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaDestinoId")
    private Collection<Pedido> pedidoCollectionDestino = new ArrayList();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaId")
    private Collection<Centrodedistribuicao> centrodedistribuicaoCollection = new ArrayList();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaId")
    private Collection<Mensagens> mensagensCollection = new ArrayList();

    public Pessoa() {
    }

    public Pessoa(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Pessoa(String nome, String email, String senha, String cep, String endereco, String complemento, String telefone, String cnpj, String cpf, byte[] imagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.endereco = endereco;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.imagem = imagem;
    }

    public Pessoa(Integer pessoaId, String nome, String email, String senha, String cep, String endereco, String complemento, String telefone, String cnpj, String cpf, byte[] imagem) {
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.endereco = endereco;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.imagem = imagem;
    }

    public Pessoa(Integer pessoaId, String nome, String email, String senha, String cep) {
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    @XmlTransient
    public Collection<Movimentacao> getMovimentacaoCollection() {
        return movimentacaoCollection;
    }

    public void setMovimentacaoCollection(Collection<Movimentacao> movimentacaoCollection) {
        this.movimentacaoCollection = movimentacaoCollection;
    }

    public Veiculo getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Veiculo veiculoId) {
        this.veiculoId = veiculoId;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection() {
        return pedidoCollectionOrigem;
    }

    public void setPedidoCollection(Collection<Pedido> pedidoCollection) {
        this.pedidoCollectionOrigem = pedidoCollection;
    }

    @XmlTransient
    public Collection<Pedido> getPedidoCollection1() {
        return pedidoCollectionDestino;
    }

    public void setPedidoCollection1(Collection<Pedido> pedidoCollection1) {
        this.pedidoCollectionDestino = pedidoCollection1;
    }

    @XmlTransient
    public Collection<Centrodedistribuicao> getCentrodedistribuicaoCollection() {
        return centrodedistribuicaoCollection;
    }

    public void setCentrodedistribuicaoCollection(Collection<Centrodedistribuicao> centrodedistribuicaoCollection) {
        this.centrodedistribuicaoCollection = centrodedistribuicaoCollection;
    }

    @XmlTransient
    public Collection<Mensagens> getMensagensCollection() {
        return mensagensCollection;
    }

    public void setMensagensCollection(Collection<Mensagens> mensagensCollection) {
        this.mensagensCollection = mensagensCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaId != null ? pessoaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.pessoaId == null && other.pessoaId != null) || (this.pessoaId != null && !this.pessoaId.equals(other.pessoaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return email;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
}
