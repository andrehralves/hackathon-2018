/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Conta;
import Entidades.Mensagens;
import Entidades.Pedido;
import Entidades.Pessoa;
import Transacao.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;

/**
 *
 * @author Carlos Matheus
 */
public class CtrlPedido extends CtrlBase {

    private static CtrlPedido ctrlpedido;

    public static CtrlPedido create() {
        if (ctrlpedido == null) {
            ctrlpedido = new CtrlPedido();
        }
        return ctrlpedido;
    }

    public CtrlPedido() {
        super(Transaction.getEntityManagerFactory());
    }

    public static void setCampos(Object pedido, TextArea taObs) {
        if (pedido != null && pedido instanceof Pedido) {
            Pedido f = (Pedido) pedido;
            //////taObs.setText(f.getDetalhes());
        }
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Pedido) {
            return ((Pedido) p).getPedidoId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Pedido) {
            txBusca.setText(((Pedido) p).getPedidoId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> pedido = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> ResultPedido;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultPedido = em.createNamedQuery("Pedido.findAll", Pedido.class)
                        .getResultList();
            } else {
                ResultPedido = em.createNamedQuery("Pedido.findByPedidoId", Pedido.class)
                        .setParameter("pedidoId", Filtro).getResultList();
            }
            for (Pedido pedidos : ResultPedido) {
                pedido.add(pedidos);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return pedido;
    }

    public ArrayList<Object> PesquisarStatus(boolean Filtro) {
        ArrayList<Object> pedido = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> ResultPedido;
            ResultPedido = em.createNamedQuery("Pedido.findByStatus", Pedido.class)
                    .setParameter("status", Filtro).getResultList();
            for (Pedido pedidos : ResultPedido) {
                pedido.add(pedidos);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return pedido;
    }

    public Object Salvar(String medida, BigDecimal peso, Boolean status, Object pessoaOrigem, Object pessoaDestino,
            Object conta, Object mensagens) {
        Pedido pedido = new Pedido(medida, peso, status);
        if (pessoaOrigem != null && pessoaOrigem instanceof Pessoa) {
            pedido.setPessoaOrigemId((Pessoa) pessoaOrigem);
        }
        if (pessoaDestino != null && pessoaDestino instanceof Pessoa) {
            pedido.setPessoaDestinoId((Pessoa) pessoaDestino);
        }
        if (conta != null && conta instanceof Conta) {
            pedido.setContaId((Conta) conta);
        }
        if (mensagens != null && mensagens instanceof Mensagens) {
            pedido.setMensagemId((Mensagens) mensagens);
        }
        return super.Salvar(pedido);
    }

    public Object Alterar(Object oPedido, Integer Id, String medida, BigDecimal peso, Boolean status, Object pessoaOrigem,
            Object pessoaDestino, Object conta, Object mensagens) {
        Pedido pedido = null;
        if (oPedido != null && oPedido instanceof Pedido) {
            pedido = (Pedido) oPedido;
            pedido.setMedida(medida);
            pedido.setPeso(peso);
            pedido.setStatus(status);
            if (pessoaOrigem != null && pessoaOrigem instanceof Pessoa) {
                pedido.setPessoaOrigemId((Pessoa) pessoaOrigem);
            }
            if (pessoaDestino != null && pessoaDestino instanceof Pessoa) {
                pedido.setPessoaDestinoId((Pessoa) pessoaDestino);
            }
            if (conta != null && conta instanceof Conta) {
                pedido.setContaId((Conta) conta);
            }
            if (mensagens != null && mensagens instanceof Mensagens) {
                pedido.setMensagemId((Mensagens) mensagens);
            }
            pedido = (Pedido) super.Alterar(pedido);
        }
        return pedido;
    }

    public Object AlterarStatus(Object oPedido, boolean Status) {
        Pedido pedido = null;
        if (oPedido != null && oPedido instanceof Pedido) {
            pedido = (Pedido) oPedido;
            pedido.setStatus(Status);
            pedido = (Pedido) super.Alterar(pedido);
        }
        return pedido;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Pedido.class);
    }

}
