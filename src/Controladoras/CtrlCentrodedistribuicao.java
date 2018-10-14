/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Centrodedistribuicao;
import Entidades.Pessoa;
import Transacao.Transacao;
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
public class CtrlCentrodedistribuicao extends CtrlBase {

    private static CtrlCentrodedistribuicao ctrlcentrodedistribuicao;

    public static CtrlCentrodedistribuicao create() {
        if (ctrlcentrodedistribuicao == null) {
            ctrlcentrodedistribuicao = new CtrlCentrodedistribuicao();
        }
        return ctrlcentrodedistribuicao;
    }

    public CtrlCentrodedistribuicao() {
        super(Transaction.getEntityManagerFactory());
    }

    public static void setCampos(Object centrodedistribuicao, TextArea taObs) {
        if (centrodedistribuicao != null && centrodedistribuicao instanceof Centrodedistribuicao) {
            Centrodedistribuicao f = (Centrodedistribuicao) centrodedistribuicao;
            taObs.setText(f.getDetalhes());
        }
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Centrodedistribuicao) {
            return ((Centrodedistribuicao) p).getCentroId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Centrodedistribuicao) {
            txBusca.setText(((Centrodedistribuicao) p).getCentroId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> centrodedistribuicao = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Centrodedistribuicao> ResultCentrodedistribuicao;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultCentrodedistribuicao = em.createNamedQuery("Centrodedistribuicao.findAll", Centrodedistribuicao.class)
                        .getResultList();
            } else {
                ResultCentrodedistribuicao = em.createNamedQuery("Centrodedistribuicao.findByCentroId", Centrodedistribuicao.class)
                        .setParameter("centroId", Filtro).getResultList();
            }
            for (Centrodedistribuicao centrodedistribuicaos : ResultCentrodedistribuicao) {
                centrodedistribuicao.add(centrodedistribuicaos);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return centrodedistribuicao;
    }

    public Object Salvar(String detalhes, Object pessoa) {
        Centrodedistribuicao centrodedistribuicao = new Centrodedistribuicao(detalhes);
        if (pessoa != null && pessoa instanceof Pessoa) {
            centrodedistribuicao.setPessoaId((Pessoa) pessoa);
        }
        return super.Salvar(centrodedistribuicao);
    }

    public Object Alterar(Object oCentrodedistribuicao, Integer Id, String detalhes, Object pessoa) {
        Centrodedistribuicao centrodedistribuicao = null;
        if (oCentrodedistribuicao != null && oCentrodedistribuicao instanceof Centrodedistribuicao) {
            centrodedistribuicao = (Centrodedistribuicao) oCentrodedistribuicao;
            centrodedistribuicao.setDetalhes(detalhes);
            if (pessoa != null && pessoa instanceof Pessoa) {
                centrodedistribuicao.setPessoaId((Pessoa) pessoa);
            }
            centrodedistribuicao = (Centrodedistribuicao) super.Alterar(centrodedistribuicao);
        }
        return centrodedistribuicao;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Centrodedistribuicao.class);
    }

    public Object Adicionar(String detalhes, Object pessoa) {
        Centrodedistribuicao centrodedistribuicao = new Centrodedistribuicao();
        if (pessoa != null && pessoa instanceof Pessoa) {
            return new Transacao() {
                @Override
                public Object Transacao(Object... Params) {
                    Pessoa p = (Pessoa) pessoa;
                    p.setCentrodedistribuicaoCollection(new ArrayList());
                    centrodedistribuicao.setDetalhes(detalhes);
                    centrodedistribuicao.setPessoaId(p);
                    p.getCentrodedistribuicaoCollection().add(centrodedistribuicao);
                    return p = em.merge(p);
                }
            }.Realiza();
        }
        return null;
    }

}
