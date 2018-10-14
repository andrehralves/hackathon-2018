/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Conta;
import Transacao.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;

/**
 *
 * @author Carlos Matheus
 */
public class CtrlConta extends CtrlBase {

    private static CtrlConta ctrlconta;

    public static CtrlConta create() {
        if (ctrlconta == null) {
            ctrlconta = new CtrlConta();
        }
        return ctrlconta;
    }

    public CtrlConta() {
        super(Transaction.getEntityManagerFactory());
    }

    public static void setCampos(Object conta, TextArea taObs) {
        if (conta != null && conta instanceof Conta) {
            Conta f = (Conta) conta;
            //////taObs.setText(f.getDetalhes());
        }
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Conta) {
            return ((Conta) p).getContaId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Conta) {
            txBusca.setText(((Conta) p).getContaId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> conta = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Conta> ResultConta;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultConta = em.createNamedQuery("Conta.findAll", Conta.class)
                        .getResultList();
            } else {
                ResultConta = em.createNamedQuery("Conta.findByContaId", Conta.class)
                        .setParameter("contaId", Filtro).getResultList();
            }
            for (Conta contas : ResultConta) {
                conta.add(contas);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return conta;
    }

    public Object Salvar(BigDecimal valorInicial, BigDecimal valorPago, Date dataGeracao, Date dataPagamento) {
        Conta conta = new Conta(valorInicial, valorPago, dataGeracao, dataPagamento);
        return super.Salvar(conta);
    }

    public Object Alterar(Object oConta, Integer Id, BigDecimal valorInicial, BigDecimal valorPago,
            Date dataGeracao, Date dataPagamento) {
        Conta conta = null;
        if (oConta != null && oConta instanceof Conta) {
            conta = (Conta) oConta;
            conta.setDataGeracao(dataGeracao);
            conta.setDataPagamento(dataPagamento);
            conta.setValorInicial(valorInicial);
            conta.setValorPago(valorPago);
            conta = (Conta) super.Alterar(conta);
        }
        return conta;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Conta.class);
    }
    
}
