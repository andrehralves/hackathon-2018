/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Acao;

import JPA.Dao.JPADao;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 *
 * @author Raizen
 * @param <T>
 */
public abstract class ButtonAcao<T extends ButtonAcao> extends JPADao {

    public Acao acao;
    private static InterfaceFxmlAcao TelaAcao;

    protected ButtonAcao() {
        acao = new Acao() {};
        acao.setReference(this);
        AddNodes();
        acao.setTelaAcao(TelaAcao);
        acao.Inicializa();
    }

    protected void AddNodes() {
        acao.AddNodes();
    }

    public static void setTelaAcao(InterfaceFxmlAcao aTelaAcao) {
        TelaAcao = aTelaAcao;
    }

    public HBox getCrud() {
        return acao.getCrud();
    }
    public Object findNode(String Param){
        return acao.findNode(Param);
    }

    protected void addNode(String NodeType, String Param, String Name) {
        acao.addNode(NodeType, Param, Name);
    }
}
