/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Poltrinho
 */
public class BuscaCEP 
{
    public static Endereço buscaCep(String CEP)
    {
        String result="";
        Endereço end = null;
        String cep = CEP.replace("-", "");

        try 
        {
        // Create the connection
        SOAPConnectionFactory scf = SOAPConnectionFactory.newInstance();
        SOAPConnection conn = scf.createConnection();
        // Create message
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage msg = mf.createMessage();
        // Object for message parts
        SOAPPart sp = msg.getSOAPPart();
        SOAPEnvelope env = sp.getEnvelope();
        env.addNamespaceDeclaration("cli", "http://cliente.bean.master.sigep.bsb.correios.com.br/");
        SOAPBody bd = env.getBody();
        // Populate body
        // Main element
        SOAPElement be = bd.addChildElement(env.createName("cli:consultaCEP"));
        // Add content
        be.addChildElement("cep").addTextNode(cep);
        // Save message
        msg.saveChanges();
        // Send
        String urlval = "https://apps.correios.com.br/SigepMasterJPA/AtendeClienteService/AtendeCliente?wsdl";
        SOAPMessage rp = conn.call(msg, urlval);

        // slice SOAP response
        NodeList returnNodes = rp.getSOAPBody().getElementsByTagName("return");

        if (returnNodes.getLength() > 0) 
        {
            end =  new Endereço();
            Node returnNode = returnNodes.item(0);
            NodeList elements = returnNode.getChildNodes();
            for (int i = 0; i < elements.getLength(); i++) 
            {
                Node node = elements.item(i);
                switch (node.getNodeName()) 
                {
                    case "bairro":
                        end.setBairro(node.getTextContent());
                        break;
                    case "cidade":
                        end.setCidade(node.getTextContent());
                        break;
                    case "end":
                        end.setRua(node.getTextContent());
                        break;
                    case "uf":
                        end.setEstado(node.getTextContent());
                        break;
                    default:
                        break;
                }
            }
        }
        else 
        {
            returnNodes = rp.getSOAPBody().getElementsByTagName("detail");
            Node returnNode = returnNodes.item(0);
            NodeList elements = returnNode.getChildNodes();

            for (int i = 0; i < elements.getLength(); i++) 
            {
                Node node = elements.item(i);
                System.out.println(node.getTextContent());
            }
        }
            conn.close();
        } 
        catch (SOAPException ex) 
        {
            System.out.println("connection error - " + ex.getMessage());
        }
        return end;
    }
}
