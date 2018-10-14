/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.UI.Tela;

import Utils.Mensagem;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Raizen
 */
public class WindowController implements Initializable {

    @FXML
    private VBox pndados;
    @FXML
    private Label lblTitulo;
    protected Stage stage;
    public Boolean flag;
    public String Title;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flag = true;
    }


    private void evtMax(MouseEvent event) {
        if (flag) {
            WindowStyle.fullScreen(stage);
            System.out.println("Max");
        } else {
            WindowStyle.restoreScreen(stage);
            System.out.println("Min");
        }
        flag = !flag;
    }

    @FXML
    private void evtClose(MouseEvent event) {
        stage.close();
    }

    public void setChildren(Node e) {
        if (e != null) {
            pndados.getChildren().clear();
            pndados.getChildren().add(e);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        WindowStyle.allowDrag(stage.getScene().getRoot(), stage);
        WindowStyle.stageDimension(stage.getWidth(), stage.getHeight());
    }

    public void setTitle(String Title) {
        this.Title = Title;
        lblTitulo.setText(Title);
    }

    public static Boolean Create(Class Classe, String Title, Parent parent, String... Params) {
        Parent root = null;
        WindowController wc = null;
        try {
            Stage oStage = IniciarTela.newStage();
            oStage.initStyle(StageStyle.UNDECORATED);
            URL url = Classe.getResource("/Utils/UI/Tela/Window.fxml");
            FXMLLoader Loader = new FXMLLoader(url);
            root = Loader.load();
            wc = Loader.getController();
            Scene aScene = IniciarTela.newScene(root, Params);
            oStage.setScene(aScene);
            wc.setStage(oStage);
            wc.setChildren(parent);
            wc.setTitle(Title);
            oStage.showAndWait();
            return true;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Carregar Tela");
        }
        return false;
    }

}
