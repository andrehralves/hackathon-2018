/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class TelaRotasFuturasController implements Initializable {

    @FXML
    private HBox paneCalendario;
    @FXML
    private DatePicker dtData;
    @FXML
    private HBox paneCalendario1;
    @FXML
    private JFXTextField txCepOri;
    @FXML
    private JFXButton btnAbrirMapaOrigem;
    @FXML
    private JFXTextField txRuaOri;
    @FXML
    private JFXTextField txBairroOri;
    @FXML
    private JFXTextField txCidadeOri;
    @FXML
    private JFXTextField txUFOri;
    @FXML
    private HBox paneCalendario11;
    @FXML
    private JFXTextField txCepDest;
    @FXML
    private JFXButton btnAbrirMapaDest;
    @FXML
    private JFXTextField txRuaDest;
    @FXML
    private JFXTextField txBairroDest;
    @FXML
    private JFXTextField txCidadeDest;
    @FXML
    private JFXTextField txUFDest;
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
    private void evtAbirMapaOrigem(ActionEvent event) {
    }

    @FXML
    private void evtAbrirMapaDest(ActionEvent event) {
    }

    @FXML
    private void evtSalvar(ActionEvent event) {
    }
    
}
