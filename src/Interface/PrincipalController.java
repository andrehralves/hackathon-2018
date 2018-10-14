/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Utils.UI.Tela.IniciarTela;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class PrincipalController implements Initializable {

    @FXML
    private JFXDrawer DrawerMenu;
    @FXML
    private VBox MenuLateral;
    @FXML
    private VBox MenuUsuario;
    @FXML
    private Circle imgLogoUsuario;
    @FXML
    private Label txUsuarioInterface;
    @FXML
    private Label txNivelInterface;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private HBox pndados;
    @FXML
    private Label opcEditarPerfil;
    @FXML
    private Label opcSolicitarEntrega;
    @FXML
    private Label opcSair;
    @FXML
    private VBox OptionsPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Variables._pndados = pndados;
        Variables._secaoPrincipal = new PilhadeSecao(pndados);
        initDrawer();
    }    


    private void initDrawer() {
        DrawerMenu.getChildren().remove(3);
        DrawerMenu.setPrefSize(0, 0);
        DrawerMenu.setSidePane(new VBox());
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_ENTERED, (Event event) -> {
            task.setRate(task.getRate() * -1);
            task.play();
            if (DrawerMenu.isHidden()) {
                DrawerMenu.setSidePane(MenuLateral);
                DrawerMenu.open();
            } else {
                DrawerMenu.setSidePane(new VBox());
                DrawerMenu.setPrefSize(0, 0);
                DrawerMenu.close();
            }
        });
    }

    @FXML
    private void evtEditarPerfil(Event event) {
        Variables._secaoPrincipal.push(this.getClass(), "/Interface/ModPessoa.fxml");
    }

    @FXML
    private void evtSolicitarEntrega(Event event) {
        /*Variables._secaoPrincipal.push(this.getClass(), "/Interface/Login.fxml");*/
    }

    @FXML
    private void evtSair(Event event) {
        Variables._secaoInicial.pop();
    }

    
}
