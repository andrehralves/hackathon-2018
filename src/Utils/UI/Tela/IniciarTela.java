package Utils.UI.Tela;

import Utils.Mensagem;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IniciarTela {

    protected static String IMAGE_LOC = "";
    protected static Class Ref_Class = null;
    protected static String Style = "";

    public static void setStageIcon(Stage stage) {
        if (stage != null && IMAGE_LOC != null && !IMAGE_LOC.trim().isEmpty()) {
            try {
                if (Ref_Class != null) {
                    stage.getIcons().add(new Image(Ref_Class.getResourceAsStream(IMAGE_LOC)));
                } else {
                    stage.getIcons().add(new Image(new FileInputStream(IMAGE_LOC)));
                }
            } catch (Exception ex) {
                Mensagem.ExibirException(ex, "Erro ao Carregar Tela de Consulta:IniciarTela:28");
            }
        }
    }

    public static void setStageStyle(Scene scene, String... Params) {
        if (scene != null && Style != null && !Style.trim().isEmpty()) {
            if (Params != null && Params.length > 0) {
                String auxStyle = Params[0];
                if (auxStyle != null && !auxStyle.trim().isEmpty()) {
                    scene.getStylesheets().add(auxStyle);
                }
            } else {
                scene.getStylesheets().add(Style);
            }
        }
    }

    /*
    * Param[0] is a Style;
     */
    public static void loadWindow(Class classe, String loc, String title, Stage parentStage, String... Params) {
        try {
            URL url = classe.getResource(loc);
            FXMLLoader Loader = new FXMLLoader(url);
            Parent parent = Loader.load();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
                stage.setTitle(title);
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                setStageStyle(scene, Params);
                setStageIcon(stage);
                stage.showAndWait();
            } else {
                WindowController.Create(classe, title, parent, Params);
            }
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Carregar Tela");
        }
    }

    public static Tela Create(Class classe, Pane pndados, String Caminho) {
        Tela CTela = null;
        try {
            FXMLLoader Loader = new FXMLLoader(classe.getResource(Caminho));
            Parent root = Loader.load();
            CTela = (Tela) Loader.getController();
            root.getClass();
            pndados.getChildren().clear();
            pndados.getChildren().add(root);
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Carregar Tela");
        }
        return CTela;
    }

    public static Tela Create(Class classe, Pane pndados, String Caminho, String Style) {
        Tela CTela = null;
        try {
            FXMLLoader Loader = new FXMLLoader(classe.getResource(Caminho));
            Parent root = Loader.load();
            CTela = (Tela) Loader.getController();
            root.getClass();
            pndados.getChildren().clear();
            pndados.getChildren().add(root);
            /*pndados.getStylesheets().clear();*/
            pndados.getStylesheets().add(Style);
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Carregar Tela");
        }
        return CTela;
    }

    public static void Load(Class classe, Pane pndados, String Caminho, String... Params) {
        try {
            Parent parent = FXMLLoader.load(classe.getResource(Caminho));
            pndados.getChildren().clear();
            pndados.getChildren().add(parent);
            if (Params != null && Params.length > 0) {
                String Style = Params[0];
                if (Style != null && !Style.trim().isEmpty()) {
                    /*pndados.getScene().getStylesheets().clear();*/
                    pndados.getScene().getStylesheets().add(Style);
                }
            }
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Carregar Tela");
        }
    }

    /**
     * @param aIMAGE_LOC the IMAGE_LOC to set
     */
    public static void setIMAGE_LOC(String aIMAGE_LOC) {
        IMAGE_LOC = aIMAGE_LOC;
    }

    /**
     * @param aStyle the Style to set
     */
    public static void setStyle(String aStyle) {
        Style = aStyle;
    }

    /**
     * @param aRefClass the refClass to set
     */
    public static void setRef_Class(Class aRefClass) {
        Ref_Class = aRefClass;
    }

    public static Stage newStage() {
        Stage stage = new Stage();
        return change(stage);
    }

    public static Stage change(Stage stage) {
        setStageIcon(stage);
        return stage;
    }

    public static Scene newScene(Parent root, String... Params) {
        Scene scene = new Scene(root);
        return change(scene, Params);
    }

    public static Scene change(Scene scene, String... Params) {
        setStageStyle(scene, Params);
        return scene;
    }

}
