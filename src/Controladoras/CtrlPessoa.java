/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Centrodedistribuicao;
import Entidades.Individuo;
import Entidades.Pessoa;
import Entidades.Veiculo;
import Transacao.Transacao;
import Transacao.Transaction;
import Utils.Imagem;
import Utils.Mensagem;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;

/**
 *
 * @author Carlos Matheus
 */
public class CtrlPessoa extends CtrlBase {

    private static CtrlPessoa ctrlpessoa;

    public static CtrlPessoa create() {
        if (ctrlpessoa == null) {
            ctrlpessoa = new CtrlPessoa();
        }
        return ctrlpessoa;
    }

    public static String getEmail(Object pessoa) {
        if (pessoa != null && pessoa instanceof Pessoa) {
            Pessoa f = (Pessoa) pessoa;
            return f.getEmail();
        }
        return null;

    }
    
    public static Object getIdVeiculo(Object pessoa)
    {
        Pessoa p = (Pessoa)pessoa;
        if (p.getVeiculoId() != null) {
            return p.getVeiculoId();
        }
        return null;
    }

    public static Object getCentrodedistribuicao(Object pessoa) {
        if (pessoa != null && pessoa instanceof Pessoa) {
            Pessoa f = (Pessoa) pessoa;
            if (f.getCentrodedistribuicaoCollection() != null && !f.getCentrodedistribuicaoCollection().isEmpty()) {
                Centrodedistribuicao cd = (Centrodedistribuicao) new ArrayList(f.getCentrodedistribuicaoCollection()).get(0);
                return cd;
            }
        }
        return null;
    }

    public static Individuo Atualiza(Individuo pessoa) {
        if (pessoa != null && pessoa instanceof Pessoa) {
            Pessoa f = (Pessoa) pessoa;
            EntityManager em = null;
            try {
                em = getEntityManager();
                em.getTransaction().begin();
                f = em.find(Pessoa.class, f.getPessoaId());
            } finally {
                if (em != null) {
                    em.close();
                }
            }
            return f;
        }
        return pessoa;
    }

    public CtrlPessoa() {
        super(Transaction.getEntityManagerFactory());
    }

    public static void setCampos(Object pessoa, JFXTextField txbnome, JFXTextField txbtelefone, JFXTextField txbemail, JFXPasswordField txbsenha,
            JFXPasswordField txbconfirmasenha, JFXTextField txbendereco, JFXTextField txbcep, JFXTextField txbcomplemento,
            JFXTextField txbcpf_cnpj, JFXTextField txbveiculo, JFXCheckBox chboxcentrodistribuicao, JFXTextArea txbdetalhes) {
        if (pessoa != null && pessoa instanceof Pessoa) {
            Pessoa f = (Pessoa) pessoa;
            txbnome.setText(f.getNome());
            txbcep.setText(f.getCep());
            txbcomplemento.setText(f.getComplemento());
            txbsenha.setText(f.getSenha());
            txbconfirmasenha.setText(f.getSenha());
            txbemail.setText(f.getEmail());
            txbtelefone.setText(f.getTelefone());
            txbcpf_cnpj.setText(f.getCpf() + f.getCnpj());
            txbendereco.setText(f.getEndereco());
            txbveiculo.setText(f.getVeiculoId() != null ? f.getVeiculoId().getNome() : null);

            if (f.getCentrodedistribuicaoCollection() != null && !f.getCentrodedistribuicaoCollection().isEmpty()) {
                Centrodedistribuicao cd = (Centrodedistribuicao) new ArrayList(f.getCentrodedistribuicaoCollection()).get(0);
                txbdetalhes.setText(cd.getDetalhes());
                chboxcentrodistribuicao.setSelected(true);
            } else {
                chboxcentrodistribuicao.setSelected(false);
            }

        }
    }
    
    public static void setCampos(Object pessoa, JFXTextField txbcep, JFXTextField txbcomplemento) {
        if (pessoa != null && pessoa instanceof Pessoa) {
            Pessoa f = (Pessoa) pessoa;
            txbcep.setText(f.getCep());
            txbcomplemento.setText(f.getComplemento());
            /*txbsenha.setText(f.getSenha());
            txbconfirmasenha.setText(f.getSenha());
            txbemail.setText(f.getEmail());
            txbtelefone.setText(f.getTelefone());
            txbcpf_cnpj.setText(f.getCpf() + f.getCnpj());
            txbendereco.setText(f.getEndereco());
            txbveiculo.setText(f.getVeiculoId() != null ? f.getVeiculoId().toString() : null);

            if (f.getCentrodedistribuicaoCollection() != null && !f.getCentrodedistribuicaoCollection().isEmpty()) {
                Centrodedistribuicao cd = (Centrodedistribuicao) new ArrayList(f.getCentrodedistribuicaoCollection()).get(0);
                txbdetalhes.setText(cd.getDetalhes());
                chboxcentrodistribuicao.setSelected(true);
            } else {
                chboxcentrodistribuicao.setSelected(false);
            }
            */
        }
    }

    public static BufferedImage getImagem(Object p) {
        if (p != null && p instanceof Pessoa) {
            return Imagem.ByteArrayToBufferedImage(((Pessoa) p).getImagem());
        }
        return null;
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Pessoa) {
            return ((Pessoa) p).getPessoaId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Pessoa) {
            txBusca.setText(((Pessoa) p).getPessoaId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> pessoa = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pessoa> ResultPessoa;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultPessoa = em.createNamedQuery("Pessoa.findAll", Pessoa.class)
                        .getResultList();
            } else {
                ResultPessoa = em.createNamedQuery("Pessoa.findByPessoaId", Pessoa.class)
                        .setParameter("pessoaId", Filtro).getResultList();
            }
            for (Pessoa pessoas : ResultPessoa) {
                pessoa.add(pessoas);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return pessoa;
    }

    public Object ValidaSecao(String Email, String Senha) {
        ArrayList<Object> pessoa = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pessoa> ResultPessoa = em.createNamedQuery("Pessoa.findByEmailSenha", Pessoa.class)
                    .setParameter("email", Email)
                    .setParameter("senha", Senha).getResultList();
            for (Pessoa pessoas : ResultPessoa) {
                pessoa.add(pessoas);
            }

        } catch (Exception ex) {
            Mensagem.ExibirException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return pessoa.isEmpty() ? null : pessoa.get(0);
    }

    public Object Salvar(String nome, String email, String senha, String cep, String endereco, String complemento,
            String telefone, String cnpj, String cpf, byte[] imagem, Object veiculo) {
        Pessoa pessoa = new Pessoa(nome, email, senha, cep, endereco, complemento, telefone, cnpj, cpf, imagem);
        if (veiculo != null && veiculo instanceof Veiculo) {
            pessoa.setVeiculoId((Veiculo) veiculo);
        }
        return super.Salvar(pessoa);
    }

    public Object Alterar(Object oPessoa, Integer Id, String nome, String email, String senha, String cep, String endereco,
            String complemento, String telefone, String cnpj, String cpf, byte[] imagem, Object veiculo) {
        Pessoa pessoa = null;
        if (oPessoa != null && oPessoa instanceof Pessoa) {
            pessoa = (Pessoa) oPessoa;
            pessoa.setCep(cep);
            pessoa.setCnpj(cnpj);
            pessoa.setComplemento(complemento);
            pessoa.setCpf(cpf);
            pessoa.setEmail(email);
            pessoa.setImagem(imagem);
            pessoa.setEndereco(endereco);
            pessoa.setNome(nome);
            pessoa.setSenha(senha);
            pessoa.setTelefone(telefone);
            if (veiculo != null && veiculo instanceof Veiculo) {
                pessoa.setVeiculoId((Veiculo) veiculo);
            }else{
                pessoa.setVeiculoId(null);
            }
            pessoa = (Pessoa) super.Alterar(pessoa);
        }
        return pessoa;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    public Object Remover(Object pessoa) {
        Pessoa p = null;
        if (pessoa != null && pessoa instanceof Pessoa) {
            return new Transacao() {
                @Override
                public Object Transacao(Object... Params) {
                    Pessoa p = (Pessoa) pessoa;
                    p.setCentrodedistribuicaoCollection(new ArrayList());
                    return p = em.merge(p);
                }
            }.Realiza();
        }
        return p;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Pessoa.class);
    }

}
