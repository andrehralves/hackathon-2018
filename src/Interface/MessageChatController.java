/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlMensagens;
import Controladoras.CtrlPedido;
import Sessao.Sessao;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class MessageChatController implements Initializable {

    @FXML
    private JFXTextField txbPedido;
    @FXML
    private JFXTextArea taChat;
    @FXML
    private JFXTextField txbMensagem;
    private static Object pedido;
    private static MessageChatController Tela;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Tela = this;
        CtrlPedido.create().CarregarMensagens(pedido, Tela.taChat);
    }    

    @FXML
    private void evtPesquisarPedido(MouseEvent event) {
        Variables._secaoPrincipal.push(this.getClass(), "/Interface/FindPedido.fxml");
    }

    @FXML
    private void evtSubmit(MouseEvent event) {
        CtrlPedido.create().AdicionarMensagem(pedido, txbMensagem.getText(), Sessao.getIndividuo());
        CtrlPedido.create().CarregarMensagens(pedido, Tela.taChat);
    }

    /**
     * @param aPedido the pedido to set
     */
    public static void setPedido(Object aPedido) {
        if(aPedido != null){
            CtrlPedido.setCampoBusca(pedido, Tela.txbPedido);
            CtrlPedido.create().CarregarMensagens(pedido, Tela.taChat);
        }
        pedido = aPedido;
    }
    
}
