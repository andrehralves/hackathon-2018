/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Controladora;

import Utils.Mensagem;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Raizen
 */
public class CtrlUtils {

    public static Object getPrimeiroObjeto(List<Object> Objetos) {
        return (Objetos != null && Objetos.size() > 0) ? Objetos.get(0) : null;
    }

    public static void clear(ObservableList<Node> pn) {
        ObservableList<Node> componentes = pn; //”limpa” os componentes
        for (Node n : componentes) {
            if (n instanceof Pane) {
                clear(((Pane) n).getChildren());
            }
            if (n instanceof TextInputControl) // textfield, textarea e htmleditor
            {
                ((TextInputControl) n).setText("");
            }
            if (n instanceof ComboBox) {
                ((ComboBox) n).getItems().clear();
            }
            if (n instanceof ImageView) {
                ((ImageView) n).setImage(null);
            }
        }
    }

    public static void setErro(Label lb, String Msg) {
        lb.setVisible(true);
        lb.setText(Msg);
        Mensagem.ExibirLog(Msg);
    }

    public static void CloseStage(Event event) {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.close();/////fexa janela
    }

    public static boolean CloseStage(Event event, boolean EOriginal) {
        boolean flag = false;
        if (EOriginal) {
            Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
            stage.close();/////fexa janela
        } else {
            flag = true;
        }
        return flag;
    }

    public static boolean CloseChildren(Pane pane, boolean EOriginal) {

        boolean flag = false;
        if (EOriginal && pane != null) {
            pane.getChildren().clear();
        } else {
            flag = true;
        }
        return flag;
    }

    public static boolean ClosePane(Node root, boolean EOriginal) {

        boolean flag = false;
        if (EOriginal && root != null && root instanceof Pane) {
            Pane n = (Pane) root;
            n.getChildren().clear();
        } else {
            flag = true;
        }
        return flag;
    }

    public static void setCor(String cor, Node node) {
        if (node != null) {
            StringBuilder st = new StringBuilder();
            st.append("-jfx-focus-color: ").append(cor).append(";");
            st.append("-jfx-unfocus-color: ").append(cor).append(";");
            /*st.append("-fx-prompt-text-fill: ").append(cor).append(";");*/
 /*st.append("-fx-text-fill: ").append(cor).append(";");*/
            node.setStyle(st.toString());
        }
    }

}
