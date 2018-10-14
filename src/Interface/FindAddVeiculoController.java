/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Utils.Controladora.CtrlUtils;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class FindAddVeiculoController implements Initializable {

    @FXML
    private JFXTextField txbVeiculo;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<Object, Object> tcNome;
    @FXML
    private JFXTextArea taDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtPesquisar(MouseEvent event) {
    }

    @FXML
    private void evtSelecionar(MouseEvent event) {
        evtCancelar(event);
    }

    @FXML
    private void evtAdicionar(MouseEvent event) {
    }

    @FXML
    private void evtCancelar(MouseEvent event) {
        Variables._secaoPrincipal.pop();
    }
    
}
