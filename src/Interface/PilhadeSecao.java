/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Utils.Mensagem;
import java.io.IOException;
import java.util.Stack;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 *
 * @author Luish
 */
public class PilhadeSecao {

    private Pane PndadosOriginal;
    private Stack<Node> Pilha = new Stack();

    public PilhadeSecao(Pane PndadosOriginal) {
        setPndadosOriginal(PndadosOriginal);
    }

    public void pop() {
        getPilha().pop();
        change();
    }

    public void push(Node children) {
        getPilha().push(children);
        change();
    }

    public void push(Object Objeto, String childrenPath) {
        Parent parent;
        try {
            parent = FXMLLoader.load(Objeto.getClass().getResource(childrenPath));
            push(parent);
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
    }

    protected void change() {
        PndadosOriginal.getChildren().clear();
        if (!getPilha().isEmpty()) {
            PndadosOriginal.getChildren().add((Node) getPilha().peek());
        }
    }

    /**
     * @return the Pilha
     */
    public Stack<Node> getPilha() {
        return Pilha;
    }

    /**
     * @param aPndadosOriginal the PndadosOriginal to set
     */
    public void setPndadosOriginal(Pane aPndadosOriginal) {
        PndadosOriginal = aPndadosOriginal;
    }
}
