/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Sessao.Sessao;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txbEmail;
    @FXML
    private JFXPasswordField txbSenha;
    @FXML
    private VBox pndados;
    private Object pndadosInicial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtAddPessoa(MouseEvent event) {
        Variables._secaoInicial.push(this.getClass(), "/Interface/AddPessoa.fxml");
    }

    @FXML
    private void evtLogin(MouseEvent event) 
    {
        if(Sessao.CriarSessao(txbEmail.getText(), txbSenha.getText()))
        {
            Variables._secaoInicial.push(this.getClass(), "/Interface/Principal.fxml");
            setCor("#4059a9", txbEmail);
            setCor("#4059a9", txbSenha);
        }
        else
        {
            setCor("red", txbEmail);
            setCor("red", txbSenha); 
        }
    }

    private void setCor(String cor, Node node) {
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
