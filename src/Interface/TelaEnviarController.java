/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlPessoa;
import Utils.BuscaCEP;
import Utils.Controladora.CtrlUtils;
import Utils.Endereço;
import Utils.MaskFieldUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

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
    @FXML
    private VBox pndados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        MaskFieldUtil.cepField(txCepDest);
        MaskFieldUtil.numericField(txNumDest);
        MaskFieldUtil.numericField(txPeso);
    }    
    
    private void EstadoOriginal() {
        setAllErro(false);
        CtrlUtils.clear(pndados.getChildren());
    }
    
    private void setAllErro(boolean valor) {
        String st = valor ? "red" : "#4059a9";
        setCor(st, txNomeDest);
        setCor(st, txCepDest);
        setCor(st, txNumDest);
        setCor(st, txRuaDest);
        setCor(st, txBairroDest);
        setCor(st, txCidadeDest);
        setCor(st, txUFDest);
        
        setCor(st, txPeso);
        setCor(st, txMedidas);     
    }
    
    private void setCor(String cor, Node node) {
        if (node != null) {
            StringBuilder st = new StringBuilder();
            st.append("-jfx-focus-color: ").append(cor).append(";");
            st.append("-jfx-unfocus-color: ").append(cor).append(";");
            /*st.append("-fx-prompt-text-fill: ").append(cor).append(";");*/
            /*st.append("-fx-text-fill: ").append(cor).append(";");*/
            node.setStyle(st.toString());
        }
    }

    @FXML
    private void evtBuscarDestinatario(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso Busca Destinatário!!!");
        alert.setHeaderText("Aviso Busca Destinatário!!!");
        alert.setContentText("Função não implementada.");

        alert.showAndWait();
    }

    @FXML
    private void evtCarregarFotos(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso Fotos!!!");
        alert.setHeaderText("Aviso Fotos!!!");
        alert.setContentText("Função não implementada.");

        alert.showAndWait();
    }

    @FXML
    private void evtVisualizarMotoristas(ActionEvent event) 
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso!!!");
        alert.setHeaderText("Aviso!!!");
        alert.setContentText("Função não implementada.");

        alert.showAndWait();
    }

    @FXML
    private void evtSalvar(ActionEvent event) 
    {
        setAllErro(false);
        if (validaCampos()) {
            System.out.println("Valido");
        }
    }
    
    private boolean validaCampos() {

        boolean fl = true;
        CtrlPessoa cf = CtrlPessoa.create();
        if (txNomeDest.getText().trim().isEmpty()) {
            setCor("red", txNomeDest);
            txNomeDest.setText("");
            txNomeDest.requestFocus();
            fl = false;
        }
        if (txCepDest.getText().trim().isEmpty()) {
            setCor("red", txCepDest);
            txCepDest.setText("");
            txCepDest.requestFocus();
            fl = false;
        }
        if (txNumDest.getText().trim().isEmpty()) {
            setCor("red", txNumDest);
            txNumDest.setText("");
            txNumDest.requestFocus();
            fl = false;
        }
        if (txRuaDest.getText().trim().isEmpty()) {
            setCor("red", txRuaDest);
            txRuaDest.setText("");
            txRuaDest.requestFocus();
            fl = false;
        }
        if (txBairroDest.getText().trim().isEmpty()) {
            setCor("red", txBairroDest);
            txBairroDest.setText("");
            txBairroDest.requestFocus();
            fl = false;
        }
        if (txCidadeDest.getText().trim().isEmpty()) {
            setCor("red", txCidadeDest);
            txCidadeDest.setText("");
            txCidadeDest.requestFocus();
            fl = false;
        }
        if (txUFDest.getText().trim().isEmpty()) {
            setCor("red", txUFDest);
            txUFDest.setText("");
            txUFDest.requestFocus();
            fl = false;
        }
        if (txUFDest.getText().trim().isEmpty()) {
            setCor("red", txUFDest);
            txUFDest.setText("");
            txUFDest.requestFocus();
            fl = false;
        }
        if (txPeso.getText().trim().isEmpty()) {
            setCor("red", txPeso);
            txPeso.setText("");
            txPeso.requestFocus();
            fl = false;
        }
        if (txMedidas.getText().trim().isEmpty()) {
            setCor("red", txMedidas);
            txMedidas.setText("");
            txMedidas.requestFocus();
            fl = false;
        }
        return fl;
    }

    private void evtBuscaCEP(KeyEvent event) 
    {
        if(txCepDest.getText().length() == 9)
        {
            Thread t = new Thread(() -> {
                try
                {
                    Endereço end = BuscaCEP.buscaCep(txCepDest.getText());
                    
                    if(end != null)
                    {
                        txBairroDest.setText(end.getBairro());
                        txCidadeDest.setText(end.getCidade());
                        txRuaDest.setText(end.getRua());
                        txUFDest.setText(end.getEstado());
                    }
                } catch (Exception e1)
                {
                    System.out.println(e1);
                }
            });
            t.start();
        }
        else
        {
            txBairroDest.setText("");
            txCidadeDest.setText("");
            txRuaDest.setText("");
            txUFDest.setText("");
        }
    }
    
}
