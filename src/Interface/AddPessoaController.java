/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlPessoa;
import Utils.Controladora.CtrlUtils;
import Utils.MaskFieldUtil;
import Utils.Mensagem;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class AddPessoaController implements Initializable {

    @FXML
    private JFXTextField txbemail;
    @FXML
    private JFXPasswordField txbsenha;
    @FXML
    private JFXPasswordField txbconfirmasenha;
    @FXML
    private JFXTextField txbendereco;
    @FXML
    private JFXTextField txbcep;
    @FXML
    private JFXTextField txbcomplemento;
    @FXML
    private JFXTextField txbcpf_cnpj;
    @FXML
    private VBox pndados;

    private Object veiculo;
    private Object Instancia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MaskFieldUtil.cpfCnpjField(txbcpf_cnpj);
        EstadoOriginal();
    }

    private void EstadoOriginal() {
        setAllErro(false);
        CtrlUtils.clear(pndados.getChildren());
    }

    @FXML
    private void evtRegistrar(MouseEvent event) {
        setAllErro(false);
        CtrlPessoa cf = CtrlPessoa.create();
        if (validaCampos()) {
            String Cpf = null, Cnpj = null;
            if (txbcpf_cnpj.getText().length() > 14) {
                Cnpj = txbcpf_cnpj.getText();
            } else {
                Cpf = txbcpf_cnpj.getText();
            }
            if ((Instancia = cf.Salvar("", txbemail.getText(), txbsenha.getText(), txbcep.getText().replace("-", ""), txbendereco.getText(), txbcomplemento.getText(),
                    "", Cnpj, Cpf, null, veiculo)) == null) {
                Mensagem.Exibir(cf.getMsg(), 2);
            } else {
                EstadoOriginal();
                Variables._secaoInicial.pop();
            }
        } else {
            Mensagem.Exibir("Campos Inválidos!", 2);
        }
    }

    private void setDefault(Node node) {
        CtrlUtils.setCor("#4059a9", node);
    }

    private void setAllErro(boolean valor) {
        String st = valor ? "red" : "#4059a9";
        CtrlUtils.setCor(st, txbemail);
        CtrlUtils.setCor(st, txbsenha);
        CtrlUtils.setCor(st, txbconfirmasenha);
        CtrlUtils.setCor(st, txbendereco);
        CtrlUtils.setCor(st, txbcomplemento);
        CtrlUtils.setCor(st, txbcpf_cnpj);
        CtrlUtils.setCor(st, txbcep);
    }

    private boolean validaCampos() {

        boolean fl = true;
        CtrlPessoa cf = CtrlPessoa.create();
        if (txbemail.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbemail);
            txbemail.setText("");
            txbemail.requestFocus();
            fl = false;
        }
        if (txbcep.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbcep);
            txbcep.setText("");
            txbcep.requestFocus();
            fl = false;
        }
        if (txbsenha.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbsenha);
            txbsenha.setText("");
            txbsenha.requestFocus();
            fl = false;
        }else if(!txbsenha.getText().equals(txbconfirmasenha.getText())){
            CtrlUtils.setCor("red", txbconfirmasenha);
            txbconfirmasenha.setText("");
            txbconfirmasenha.requestFocus();
            fl = false;
        }
        if (txbendereco.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbendereco);
            txbendereco.setText("");
            txbendereco.requestFocus();
            fl = false;
        }
        if (txbcomplemento.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbcomplemento);
            txbcomplemento.setText("");
            txbcomplemento.requestFocus();
            fl = false;
        }
        if (txbcpf_cnpj.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbcpf_cnpj);
            txbcpf_cnpj.setText("");
            txbcpf_cnpj.requestFocus();
            fl = false;
        }
        return fl;
    }

}
