/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class TelaTransportarController implements Initializable {

    @FXML
    private TableView<?> tbProdProx;
    @FXML
    private TableColumn<?, ?> colInfo;
    @FXML
    private TableColumn<?, ?> colLocalProd;
    @FXML
    private TableColumn<?, ?> colDestino;
    @FXML
    private TableColumn<?, ?> colDistancia;
    @FXML
    private TableColumn<?, ?> colValorFrete;
    @FXML
    private JFXButton btnAddListDesejo;
    @FXML
    private TableView<?> tbListDesejo;
    @FXML
    private TableColumn<?, ?> colInfo2;
    @FXML
    private JFXButton btnSalvar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void evtClickProdProx(SortEvent<Object> event) {
    }

    @FXML
    private void evtClickListDes(SortEvent<Object> event) {
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
    }
    
}
