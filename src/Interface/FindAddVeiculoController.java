/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controladoras.CtrlPessoa;
import Controladoras.CtrlVeiculo;
import Entidades.Veiculo;
import Utils.Acao.InterfaceFxmlAcao;
import Utils.Controladora.CtrlUtils;
import Utils.Mensagem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Luish
 */
public class FindAddVeiculoController implements Initializable, InterfaceFxmlAcao {

    @FXML
    private JFXTextField txbVeiculo;
    @FXML
    private TableView<Object> tabela;
    @FXML
    private TableColumn<Object, Object> tcNome;
    @FXML
    private JFXTextArea taDescricao;
    @FXML
    private VBox pndados;
    @FXML
    private JFXButton btnSelecionar;
    @FXML
    private JFXButton btnAdicionar;
    @FXML
    private TableColumn<Object, Object> tcAcoes;

    private boolean FlagAdicionar;
    private Object Instancia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SetTelaAcao();
        ModPessoaController.setVeiculo(null);
        tcNome.setCellValueFactory(new PropertyValueFactory("Nome"));
        tcAcoes.setCellValueFactory(new PropertyValueFactory("Crud"));
        EstadoOriginal();
    }

    @FXML
    private void evtPesquisar(MouseEvent event) {
        CarregaTabela(txbVeiculo.getText());
    }

    private void CarregaTabela(String filtro) {
        CtrlVeiculo ctm = CtrlVeiculo.create();
        try {
            tabela.getItems().clear();
            tabela.setItems(FXCollections.observableList(ctm.Pesquisar(filtro)));
            if (tabela.getItems() != null && tabela.getItems().size() > 0) {
                FlagAdicionar = true;
                taDescricao.setDisable(true);
                btnAdicionar.setDisable(true);
                btnSelecionar.setDisable(false);
            } else {
                FlagAdicionar = false;
                taDescricao.setDisable(false);
                btnAdicionar.setDisable(false);
                btnSelecionar.setDisable(true);
            }
        } catch (Exception ex) {
            Mensagem.ExibirException(ex, "Erro AO Carregar Tabela");
        }
    }

    @FXML
    private void evtSelecionar(MouseEvent event) {
        ModPessoaController.setVeiculo(Instancia);
        evtCancelar(event);
    }

    private void EstadoOriginal() {
        setAllErro(false);
        FlagAdicionar = false;
        taDescricao.setDisable(true);
        btnAdicionar.setDisable(true);
        btnSelecionar.setDisable(true);
        CtrlUtils.clear(pndados.getChildren());
        CarregaTabela("");
    }

    @FXML
    private void evtAdicionar(MouseEvent event) {
        setAllErro(false);
        CtrlVeiculo cf = CtrlVeiculo.create();
        if (validaCampos()) {
            if ((Instancia = cf.Salvar(txbVeiculo.getText(), taDescricao.getText())) == null) {
                Mensagem.Exibir(cf.getMsg(), 2);
            } else {
                EstadoOriginal();
                evtSelecionar(event);
            }
        } else {
            Mensagem.Exibir("Campos Inválidos!", 2);
        }
    }

    @FXML
    private void evtCancelar(MouseEvent event) {
        Variables._secaoPrincipal.pop();
    }

    @FXML
    private void clicknatabela(MouseEvent event) {
        int lin = tabela.getSelectionModel().getSelectedIndex();
        if (lin > -1) {
            Instancia = tabela.getItems().get(lin);
            CtrlVeiculo.setCampos(Instancia, txbVeiculo, taDescricao);
        }
    }

    private void setDefault(Node node) {
        CtrlUtils.setCor("#4059a9", node);
    }

    private void setAllErro(boolean valor) {
        String st = valor ? "red" : "#4059a9";
        CtrlUtils.setCor(st, txbVeiculo);
        CtrlUtils.setCor(st, taDescricao);
    }

    private boolean validaCampos() {

        boolean fl = true;
        CtrlPessoa cf = CtrlPessoa.create();
        if (txbVeiculo.getText().trim().isEmpty()) {
            CtrlUtils.setCor("red", txbVeiculo);
            txbVeiculo.setText("");
            txbVeiculo.requestFocus();
            fl = false;
        }
        return fl;
    }

    @Override
    public void SelectEventAcao(Object Reference, String Action, Object element) {
        if (Action.equalsIgnoreCase("rem")) {
            evtRemover(Reference);
        }
    }

    private void evtRemover(Object Reference) {
        if (CtrlVeiculo.create().Remover(CtrlVeiculo.getId(Reference))) {
            CarregaTabela("");
        }
    }

    @Override
    public void SetTelaAcao() {
        Veiculo.setTelaAcao(this);
    }

}
