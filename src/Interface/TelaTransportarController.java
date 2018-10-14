/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlMovimentacao;
import Controladoras.CtrlPedido;
import Controladoras.CtrlPessoa;
import Sessao.Sessao;
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
        ObservableList<Object> obsl = FXCollections.observableArrayList(ctrPedido.PesquisarStatus(false));
        tbProdProx.setItems(obsl);
    }       

    @FXML
    private void evtSalvar(ActionEvent event) {
        if (CtrlPessoa.getIdVeiculo(Sessao.getIndividuo()) != null) {
            for (int i = 0; i < tbListDesejo.getItems().size(); i++) {
                CtrlMovimentacao.create().Salvar(null, Sessao.getIndividuo(), CtrlPessoa.getIdVeiculo(Sessao.getIndividuo()),
                                             tbListDesejo.getItems().get(i), null);
                CtrlPedido.create().AlterarStatus(tbListDesejo.getItems().get(i), true);
                tbListDesejo.getItems().clear();
            }
            Mensagem.Exibir("Seleção de pedidos salvo com sucesso !", 1);
        }
        else
            Mensagem.Exibir("Você não possui um veículo cadastrado !", 2);
    }

    @FXML
    private void evtAddListDesejo(ActionEvent event) {
        if (tbProdProx.getSelectionModel().getSelectedItem() != null) {
            tbListDesejo.getItems().add(tbProdProx.getSelectionModel().getSelectedItem());
            tbProdProx.getItems().remove(tbProdProx.getSelectionModel().getSelectedItem());
        }
    }
}
