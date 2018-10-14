/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class TelaController implements Initializable {

    @FXML
    private VBox PndadosOriginal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Variables._secaoInicial = new PilhadeSecao(PndadosOriginal);
        Variables._secaoInicial.push(this.getClass(), "/Interface/Login.fxml");
    }

}
