/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladoras;

import Controladora.Base.CtrlBase;
import Entidades.Veiculo;
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
public class CtrlVeiculo extends CtrlBase {

    private static CtrlVeiculo ctrlveiculo;

    public static CtrlVeiculo create() {
        if (ctrlveiculo == null) {
            ctrlveiculo = new CtrlVeiculo();
        }
        return ctrlveiculo;
    }

    public CtrlVeiculo() {
        super(Transaction.getEntityManagerFactory());
    }

    public static void setCampos(Object veiculo, TextArea taObs) {
        if (veiculo != null && veiculo instanceof Veiculo) {
            Veiculo f = (Veiculo) veiculo;
            //////taObs.setText(f.getDetalhes());
        }
    }

    public static Integer getId(Object p) {
        if (p != null && p instanceof Veiculo) {
            return ((Veiculo) p).getVeiculoId();
        }
        return -1;
    }

    public static void setCampoBusca(Object p, TextField txBusca) {
        if (p != null && p instanceof Veiculo) {
            txBusca.setText(((Veiculo) p).getVeiculoId().toString());
        }
    }

    public ArrayList<Object> Pesquisar(String Filtro) {
        ArrayList<Object> veiculo = new ArrayList();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Veiculo> ResultVeiculo;
            if (Filtro != null && Filtro.isEmpty()) {
                ResultVeiculo = em.createNamedQuery("Veiculo.findAll", Veiculo.class)
                        .getResultList();
            } else {
                ResultVeiculo = em.createNamedQuery("Veiculo.findByVeiculoId", Veiculo.class)
                        .setParameter("veiculoId", Filtro).getResultList();
            }
            for (Veiculo veiculos : ResultVeiculo) {
                veiculo.add(veiculos);
            }

        } finally {
            if (em != null) {
                em.close();
            }
        }

        return veiculo;
    }

    public Object Salvar(String nome, String descricao) {
        Veiculo veiculo = new Veiculo(nome, descricao);
        return super.Salvar(veiculo);
    }

    public Object Alterar(Object oVeiculo, Integer Id, String nome, String descricao) {
        Veiculo veiculo = null;
        if (oVeiculo != null && oVeiculo instanceof Veiculo) {
            veiculo = (Veiculo) oVeiculo;
            veiculo.setNome(nome);
            veiculo.setDescricao(descricao);
            veiculo = (Veiculo) super.Alterar(veiculo);
        }
        return veiculo;
    }

    public boolean Remover(Integer Id) {
        return super.Remover(Id) != null;
    }

    @Override
    protected void setEntityReference() {
        setEntityReference(Veiculo.class);
    }
    
}
