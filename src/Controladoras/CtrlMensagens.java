/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Mensagens;
import Entidades.Pessoa;
import Transacao.Transaction;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;

/**
 *
 * @author Carlos Matheus
 */
public class CtrlMensagens extends CtrlBase {

    private static CtrlMensagens ctrlmensagens;

    public static CtrlMensagens create() {
        if (ctrlmensagens == null) {
            ctrlmensagens = new CtrlMensagens();
        }
        return ctrlmensagens;
    }

    public CtrlMensagens() {
        super(Transaction.getEntityManagerFactory());
    }

    public static void setCampos(Object mensagens, TextArea taObs) {
        if (mensagens != null && mensagens instanceof Mensagens) {
            Mensagens f = (Mensagens) mensagens;
            //////taObs.setText(f.getDetalhes());
        }
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Mensagens) {
            return ((Mensagens) p).getMensagemId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Mensagens) {
            txBusca.setText(((Mensagens) p).getMensagemId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> mensagens = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mensagens> ResultMensagens;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultMensagens = em.createNamedQuery("Mensagens.findAll", Mensagens.class)
                        .getResultList();
            } else {
                ResultMensagens = em.createNamedQuery("Mensagens.findByMensagemId", Mensagens.class)
                        .setParameter("mensagemId", Filtro).getResultList();
            }
            for (Mensagens mensagenss : ResultMensagens) {
                mensagens.add(mensagenss);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return mensagens;
    }

    public Object Salvar(String descricao, Object pessoa) {
        Mensagens mensagens = new Mensagens(descricao);
        if (pessoa != null && pessoa instanceof Pessoa) {
            mensagens.setPessoaId((Pessoa) pessoa);
        }
        return super.Salvar(mensagens);
    }

    public Object Alterar(Object oMensagens, Integer Id, String descricao, Object pessoa) {
        Mensagens mensagens = null;
        if (oMensagens != null && oMensagens instanceof Mensagens) {
            mensagens = (Mensagens) oMensagens;
            mensagens.setDescricao(descricao);
            if (pessoa != null && pessoa instanceof Pessoa) {
                mensagens.setPessoaId((Pessoa) pessoa);
            }
            mensagens = (Mensagens) super.Alterar(mensagens);
        }
        return mensagens;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Mensagens.class);
    }

}
