/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlVeiculo;
import Utils.Mensagem;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class FindPedidoController implements Initializable {

    @FXML
    private JFXTextField txbPedido;
    @FXML
    private TableView<Object> tbProdProx;
    @FXML
    private TableColumn<Object, Object> colOrigem;
    @FXML
    private TableColumn<Object, Object> colDestino;
    @FXML
    private TableColumn<Object, Object> colMedida;
    @FXML
    private TableColumn<Object, Object> colPeso;
    @FXML
    private TableColumn<Object, Object> colValorFrete;
    
    private Object Instancia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colOrigem.setCellValueFactory(new PropertyValueFactory("origemFormatada"));
        colDestino.setCellValueFactory(new PropertyValueFactory("destinoFormatada"));
        colMedida.setCellValueFactory(new PropertyValueFactory("medida"));
        colPeso.setCellValueFactory(new PropertyValueFactory("peso"));
        EstadoOriginal();
    }    

    @FXML
    private void evtPesquisarPedido(MouseEvent event) {
        CarregaTabela(txbPedido.getText());
    }

    private void CarregaTabela(String filtro) {
        CtrlVeiculo ctm = CtrlVeiculo.create();
        try {
            tbProdProx.getItems().clear();
            tbProdProx.setItems(FXCollections.observableList(ctm.Pesquisar(filtro)));
        } catch (Exception ex) {
            Mensagem.ExibirException(ex, "Erro AO Carregar Tabela");
        }
    }

    @FXML
    private void evtSelecionar(MouseEvent event) {
        
    }

    @FXML
    private void evtCancelar(MouseEvent event) {
        Variables._secaoPrincipal.pop();
    }

    private void EstadoOriginal() {
        CarregaTabela("");
    }

    @FXML
    private void clicknatabela(MouseEvent event) {
        int lin = tbProdProx.getSelectionModel().getSelectedIndex();
        if (lin > -1) {
            Instancia = tbProdProx.getItems().get(lin);
        }
    }
    
}
