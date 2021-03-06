/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlCentrodedistribuicao;
import Controladoras.CtrlPessoa;
import Controladoras.CtrlVeiculo;
import Sessao.Sessao;
import Utils.Controladora.CtrlUtils;
import Utils.MaskFieldUtil;
import Utils.Mensagem;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
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
public class ModPessoaController implements Initializable {

    @FXML
    private JFXTextField txbnome;
    @FXML
    private JFXTextField txbtelefone;
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
    private JFXTextField txbveiculo;
    @FXML
    private JFXCheckBox chboxcentrodistribuicao;
    @FXML
    private JFXTextArea txbdetalhes;
    @FXML
    private VBox pndados;

    private static ModPessoaController Tela;
    private static Object veiculo;
    private Object Instancia;
    private Integer ID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Tela = this;
        MaskFieldUtil.cepField(txbcep);
        MaskFieldUtil.cpfCnpjField(txbcpf_cnpj);
        Instancia = Sessao.getIndividuo();
        EstadoOriginal();
    }

    private void setCampos(Object f) {
        if (f != null) {
            CtrlPessoa.setCampos(f, txbnome, txbtelefone, txbemail, txbsenha, txbconfirmasenha, txbendereco, txbcep, txbcomplemento,
                    txbcpf_cnpj, txbveiculo, chboxcentrodistribuicao, txbdetalhes);
            ID = CtrlPessoa.getId(f);
            Instancia = f;
            chboxcentrodistribuicao.setSelected(CtrlPessoa.getCentrodedistribuicao(f) != null);
            /*Imagem.setImagem(CtrlPessoa.getImagem(f));*/
        }
    }

    @FXML
    private void evtAddVeiculo(MouseEvent event) {
        Variables._secaoPrincipal.push(this.getClass(), "/Interface/FindAddVeiculo.fxml");
    }

    @FXML
    private void evtRemoveVeiculo(MouseEvent event) {
        veiculo = null;
        txbveiculo.setText("");
    }

    private void EstadoOriginal() {
        setAllErro(false);
        CtrlUtils.clear(pndados.getChildren());
        setCampos(Instancia);
    }

    @FXML
    private void evtAtualizar(MouseEvent event) {
        setAllErro(false);
        CtrlPessoa cf = CtrlPessoa.create();
        CtrlCentrodedistribuicao cd = CtrlCentrodedistribuicao.create();
        if (validaCampos()) {
            String Cpf = null, Cnpj = null;
            if (txbcpf_cnpj.getText().length() > 14) {
                Cnpj = txbcpf_cnpj.getText();
            } else {
                Cpf = txbcpf_cnpj.getText();
            }
            if (((Instancia = cf.Alterar(Instancia, ID, txbnome.getText(), txbemail.getText(), txbsenha.getText(), txbcep.getText(),
                    txbendereco.getText(), txbcomplemento.getText(), txbtelefone.getText(), Cnpj, Cpf, null, veiculo)) == null)) {
                Mensagem.Exibir(cf.getMsg(), 2);
            } else {
                if (chboxcentrodistribuicao.isSelected()) {
                    Instancia = cd.Adicionar(txbdetalhes.getText(), Instancia);
                } else {
                    Instancia = cf.Remover(Instancia);
                }
                EstadoOriginal();
                evtCancelar(event);
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

        CtrlUtils.setCor(st, txbnome);
        CtrlUtils.setCor(st, txbtelefone);
        CtrlUtils.setCor(st, txbveiculo);
        CtrlUtils.setCor(st, txbdetalhes);

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
        } else if (!txbsenha.getText().equals(txbconfirmasenha.getText())) {
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
        if (txbnome.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbnome);
            txbnome.setText("");
            txbnome.requestFocus();
            fl = false;
        }
        return fl;
    }

    @FXML
    private void evtCancelar(MouseEvent event) {
        Variables._secaoPrincipal.pop();
    }

    /**
     * @param aVeiculo the veiculo to set
     */
    public static void setVeiculo(Object aVeiculo) {
        if (aVeiculo != null) {
            CtrlVeiculo.setCampoBusca(aVeiculo, Tela.txbveiculo);
        }
        veiculo = aVeiculo;
    }

}
