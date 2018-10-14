package Utils.Acao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author Raizen
 */
public abstract class Acao {

    /**
     * Para usar é necessário herdar na classe do carrinho E Utilizar a
     * InterfacefxmlCarrinho na tela fxml
     */
    protected InterfaceFxmlAcao TelaAcao;
    protected ArrayList<NodeAcao> Nodes;
    protected HBox Crud;
    protected Object Reference;

    protected Acao() {
        /*Class classe = (Class<T>) ((ParameterizedType) getClass()
                            .getGenericSuperclass()).getActualTypeArguments()[0];
        System.out.println(classe.getName());*/
        /////AddNodes();
        /////Inicializa();
    }

    /**
     * Sobrescrever este método para Adicionar Novos Nodes;
     */
    public void AddNodes() {
        if (Nodes != null && Nodes.isEmpty()) {
            Default();
        }
    }

    protected void Default() {
        addNode("Button", "mod", "Modificar");
        addNode("Button", "rem", "Remover");
    }

    protected void addNode(String NodeType, String Param, String Name) {
        if (Nodes == null) {
            Nodes = new ArrayList();
        }
        Nodes.add(new NodeAcao(NodeType, Param, Name));
    }

    public void Inicializa() {
        Crud = new HBox(8);
        Nodes.forEach((Node) -> {
            InicializaHandler(Node, Node.getParam());
            if (Node.getNode() instanceof Button) {
                InicializaEventoMouse(Node.getNode(), Node.getEvent());
            }else if (Node.getNode() instanceof CheckBox) {
                InicializaEventoMouse(Node.getNode(), Node.getEvent());
            }
            getCrud().getChildren().add(Node.getNode());
        });
    }

    protected void InicializaEventoMouse(Node Botao, EventHandler eventHandler) {
        Botao.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
    }

    protected void InicializaHandler(NodeAcao oNode, String Param) {
        oNode.setEvent(MouseEvent -> TelaAcao.SelectEventAcao(getReference(), Param, oNode.getNode()));
    }

    protected Object getReference(){
        return Reference;
    }

    protected void setReference(Object theReference){
        Reference = theReference;
    }
    
    public Object findNode(String Param){
        int Pos = 0;
        while(Pos < Nodes.size() && !Nodes.get(Pos).Param.equalsIgnoreCase(Param))
            Pos++;
        return Pos < Nodes.size() ? Nodes.get(Pos).oNode : null;
    }

    /**
     * @return the Crud
     */
    public HBox getCrud() {
        return Crud;
    }

    /**
     * @param aTelaAcao
     */
    public void setTelaAcao(InterfaceFxmlAcao aTelaAcao) {
        TelaAcao = aTelaAcao;
    }

    /**
     * Add.addEventHandler(MouseEvent.MOUSE_CLICKED, new
     * EventHandler<MouseEvent>() {
     *
     * @Override public void handle(MouseEvent event) {
     * FXMLDocumentController.mouse(firstName); } });
     */
    public class NodeAcao {

        private EventHandler Event;
        private Node oNode;
        private final String Param;
        private final String Name;

        public NodeAcao(String NodeType, String Param, String Name) {
            if (NodeType.equalsIgnoreCase("Button")) {
                this.oNode = new Button(Name);
            }else if(NodeType.equalsIgnoreCase("CheckBox")){
                 this.oNode = new CheckBox(Name);
            }
            this.Param = Param;
            this.Name = Name;
        }

        public EventHandler getEvent() {
            return Event;
        }

        public Node getNode() {
            return oNode;
        }

        public String getParam() {
            return Param;
        }

        public String getName() {
            return Name;
        }

        public void setEvent(EventHandler Evento) {
            this.Event = Evento;
        }
    }
}
