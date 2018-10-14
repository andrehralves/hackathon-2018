/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlMovimentacao;
import Controladoras.CtrlPedido;
import Controladoras.CtrlPessoa;
import Entidades.Individuo;
import Sessao.Sessao;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Carlos Matheus
 */
public class TelaPagamentoController implements Initializable {

    @FXML
    private VBox pndados;
    @FXML
    private JFXDatePicker dtpData;
    @FXML
    private JFXComboBox<Object> cbProd;
    @FXML
    private JFXTextField txPeso;
    @FXML
    private JFXTextField txMedidas;
    @FXML
    private JFXButton btnEntregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estadoOriginal();
    }    
    
    public void estadoOriginal()
    {
        dtpData.setValue(LocalDate.now());
        txPeso.setText("");
        txMedidas.setText("");
        ArrayList<Object> pedidos = CtrlMovimentacao.create().Pesquisar("");
        int codMotorista = CtrlPessoa.create().getIdPessoa(Sessao.getIndividuo());
        for(int i = 0; i < pedidos.size(); i++)
        {
            if (CtrlMovimentacao.create().getIdMotorista(pedidos.get(i)) != codMotorista) {
                pedidos.remove(pedidos.get(i));
            }
        }
        cbProd.getItems().addAll(pedidos);
    }
    
    private void evtVisualizarMotoristas(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso!!!");
        alert.setHeaderText("Aviso!!!");
        alert.setContentText("Evento não foi implementado !");

        alert.showAndWait();
    }

    @FXML
    private void evtEscolha(Event event) {
        if (cbProd.getSelectionModel().getSelectedItem() != null) {
            txPeso.setText(CtrlMovimentacao.create().getPeso(cbProd.getSelectionModel().getSelectedItem()));
            txMedidas.setText(CtrlMovimentacao.create().getMedidas(cbProd.getSelectionModel().getSelectedItem()));
        }
    }

    @FXML
    private void evtEntregar(ActionEvent event) 
    {
        evtVisualizarMotoristas(null);
    }
    
}
