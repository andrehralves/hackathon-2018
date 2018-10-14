/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Centrodedistribuicao;
import Entidades.Movimentacao;
import Entidades.Pedido;
import Entidades.Pessoa;
import Entidades.Veiculo;
import Transacao.Transaction;
import Utils.Imagem;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;

/**
 *
 * @author Carlos Matheus
 */
public class CtrlMovimentacao extends CtrlBase {

    private static CtrlMovimentacao ctrlmovimentacao;

    public static CtrlMovimentacao create() {
        if (ctrlmovimentacao == null) {
            ctrlmovimentacao = new CtrlMovimentacao();
        }
        return ctrlmovimentacao;
    }

    public CtrlMovimentacao() {
        super(Transaction.getEntityManagerFactory());
    }
    
    public int getIdMotorista(Object movimentacao)
    {
        return ((Movimentacao)movimentacao).getPessoaMotoristaId().getPessoaId();
    }

    public static void setCampos(Object movimentacao, TextArea taObs) {
        if (movimentacao != null && movimentacao instanceof Movimentacao) {
            Movimentacao f = (Movimentacao) movimentacao;
            //////taObs.setText(f.getDetalhes());
        }
    }
    
    public String getMedidas(Object movimentacao)
    {
        return ((Movimentacao)movimentacao).getPedidoId().getMedida();
    }
    
    public String getPeso(Object movimentacao)
    {
        return ((Movimentacao)movimentacao).getPedidoId().getPeso().toString();
    }

    public static BufferedImage getImagem(Object p) {
        if (p != null && p instanceof Movimentacao) {
            return Imagem.ByteArrayToBufferedImage(((Movimentacao) p).getImagem());
        }
        return null;
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Movimentacao) {
            return ((Movimentacao) p).getMovimentacaoId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Movimentacao) {
            txBusca.setText(((Movimentacao) p).getMovimentacaoId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> movimentacao = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Movimentacao> ResultMovimentacao;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultMovimentacao = em.createNamedQuery("Movimentacao.findAll", Movimentacao.class)
                        .getResultList();
            } else {
                ResultMovimentacao = em.createNamedQuery("Movimentacao.findByMovimentacaoId", Movimentacao.class)
                        .setParameter("movimentacaoId", Filtro).getResultList();
            }
            for (Movimentacao movimentacaos : ResultMovimentacao) {
                movimentacao.add(movimentacaos);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return movimentacao;
    }

    public Object Salvar(byte[] imagem, Object pessoa, Object veiculo, Object pedido, Object centrodedistribuicao) {
        Movimentacao movimentacao = new Movimentacao(imagem);
        if (pessoa != null && pessoa instanceof Pessoa) {
            movimentacao.setPessoaMotoristaId((Pessoa) pessoa);
        }
        if (veiculo != null && veiculo instanceof Veiculo) {
            movimentacao.setVeiculoId((Veiculo) veiculo);
        }
        if (pedido != null && pedido instanceof Pedido) {
            movimentacao.setPedidoId((Pedido) pedido);
        }
        if (centrodedistribuicao != null && centrodedistribuicao instanceof Centrodedistribuicao) {
            movimentacao.setCentroId((Centrodedistribuicao) centrodedistribuicao);
        }
        return super.Salvar(movimentacao);
    }

    public Object Alterar(Object oMovimentacao, Integer Id, byte[] imagem, Object pessoa, Object veiculo,
            Object pedido, Object centrodedistribuicao) {
        Movimentacao movimentacao = null;
        if (oMovimentacao != null && oMovimentacao instanceof Movimentacao) {
            movimentacao = (Movimentacao) oMovimentacao;
            movimentacao.setImagem(imagem);
            if (pessoa != null && pessoa instanceof Pessoa) {
                movimentacao.setPessoaMotoristaId((Pessoa) pessoa);
            }
            if (veiculo != null && veiculo instanceof Veiculo) {
                movimentacao.setVeiculoId((Veiculo) veiculo);
            }
            if (pedido != null && pedido instanceof Pedido) {
                movimentacao.setPedidoId((Pedido) pedido);
            }
            if (centrodedistribuicao != null && centrodedistribuicao instanceof Centrodedistribuicao) {
                movimentacao.setCentroId((Centrodedistribuicao) centrodedistribuicao);
            }
            movimentacao = (Movimentacao) super.Alterar(movimentacao);
        }
        return movimentacao;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Movimentacao.class);
    }

}
