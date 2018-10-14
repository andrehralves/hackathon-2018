/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class TelaEnviarController implements Initializable {

    @FXML
    private JFXButton btnBuscarDestinatario;
    @FXML
    private JFXTextField txNomeDest;
    @FXML
    private JFXTextField txCepDest;
    @FXML
    private JFXTextField txNumDest;
    @FXML
    private JFXTextField txRuaDest;
    @FXML
    private JFXTextField txBairroDest;
    @FXML
    private JFXTextField txCidadeDest;
    @FXML
    private JFXTextField txUFDest;
    @FXML
    private JFXTextField txPeso;
    @FXML
    private JFXTextField txMedidas;
    @FXML
    private JFXTextArea txObs;
    @FXML
    private JFXButton btnCarregarFotos;
    @FXML
    private JFXRadioButton radioPostarPess;
    @FXML
    private ToggleGroup grupo1;
    @FXML
    private JFXRadioButton radioSolicitar;
    @FXML
    private JFXButton btnVisualizarMotoristas;
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
    private void evtBuscarDestinatario(ActionEvent event) {
    }

    @FXML
    private void evtCarregarFotos(ActionEvent event) {
    }

    @FXML
    private void evtVisualizarMotoristas(ActionEvent event) {
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
    }
    
}
