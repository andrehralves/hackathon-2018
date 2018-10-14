/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlPedido;
import Utils.MaskFieldUtil;
import Utils.Mensagem;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class TelaTransportarController implements Initializable {

    @FXML
    private TableView<Object> tbProdProx;
    @FXML
    private TableColumn colDestino;
    @FXML
    private TableColumn colValorFrete;
    @FXML
    private JFXButton btnAddListDesejo;
    @FXML
    private TableView<Object> tbListDesejo;
    @FXML
    private JFXButton btnSalvar;
    @FXML
    private TableColumn colDestino2;
    @FXML
    private TableColumn colValorFrete2;
    @FXML
    private TableColumn colOrigem;
    @FXML
    private TableColumn colOrigem2;
    @FXML
    private TableColumn colMedida;
    @FXML
    private TableColumn colPeso;
    @FXML
    private TableColumn colMedida2;
    @FXML
    private TableColumn colPeso2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colOrigem.setCellValueFactory(new PropertyValueFactory("origemFormatada"));
        colDestino.setCellValueFactory(new PropertyValueFactory("destinoFormatada"));
        colMedida.setCellValueFactory(new PropertyValueFactory("medida"));
        colPeso.setCellValueFactory(new PropertyValueFactory("peso"));
        //colValorFrete.setCellValueFactory(new PropertyValueFactory("destino"));
        colOrigem2.setCellValueFactory(new PropertyValueFactory("origemFormatada"));
        colDestino2.setCellValueFactory(new PropertyValueFactory("destinoFormatada"));
        colMedida2.setCellValueFactory(new PropertyValueFactory("medida"));
        colPeso2.setCellValueFactory(new PropertyValueFactory("peso"));
        
        estadoInicial();
    }    
    
    public void estadoInicial()
    {
        CtrlPedido ctrPedido = CtrlPedido.create();
        ObservableList<Object> obsl = FXCollections.observableArrayList(ctrPedido.Pesquisar(""));
        tbProdProx.setItems(obsl);
    }       

    @FXML
    private void evtSalvar(ActionEvent event) {
        Mensagem.Exibir("Pedidos adicionados !", 1);
    }

    @FXML
    private void evtAddListDesejo(ActionEvent event) {
        if (tbProdProx.getSelectionModel().getSelectedItem() != null) {
            tbListDesejo.getItems().add(tbProdProx.getSelectionModel().getSelectedItem());
            tbProdProx.getItems().remove(tbProdProx.getSelectionModel().getSelectedItem());
        }
    }
}
