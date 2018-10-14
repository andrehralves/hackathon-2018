/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Raizen
 */
public class Mensagem {
    private static Alert msg;
    private static File arquivo;
    
    public static void Exibir(String Mensagem,int Tipo){
        GravaLog(Mensagem);
        selecionaTipo(Tipo);
        Mostrar(Mensagem);
    }
    
    public static void ExibirException(Exception ex){
        System.out.println(ex);
        GravaLog(ex.getMessage());
    }
    
    private static void Mostrar(String Mensagem){
        System.out.println(Mensagem);
        msg.setContentText(Mensagem);
        msg.showAndWait();
    }
    private static void selecionaTipo(int Tipo){
        msg = new Alert(Tipo == 1? Alert.AlertType.INFORMATION :
                        Tipo == 2? Alert.AlertType.WARNING :
                        Tipo == 3? Alert.AlertType.ERROR :
                        Tipo == 4? Alert.AlertType.NONE :
                        Tipo == 5? Alert.AlertType.CONFIRMATION:
                                   Alert.AlertType.WARNING);
    }

    public static void ExibirException(Exception ex, String erro) {
        System.out.println(ex.getMessage());
        GravaLog(erro +" "+ ex.getMessage());
    }

    public static void ExibirLog(String Mensagem) {
        System.out.println(Mensagem);
        GravaLog(Mensagem);
    }

    public static boolean ExibirConfimacao(String Mensagem) {
        selecionaTipo(5);
        msg.setContentText(Mensagem);
        return msg.showAndWait().get() == ButtonType.OK;
    }

    public static void Armazenar(String Mensagem) {
        System.out.println(Mensagem);
        selecionaTipo(2);
        msg.setContentText(Mensagem);
        GravaLog(Mensagem);
    }
    
    private static void GravaLog(String Mensagem){
        ArrayList<String> arr = new ArrayList<>();
        arr.add((new Date(System.currentTimeMillis()).toString()+":  ")+Mensagem);
        if(arquivo == null){
            arquivo = new File("log.txt");
            Arquivo.truncate(arquivo, 0);
        }
        Arquivo.gravaArquivoDeStringTexto(arquivo, arr);
    }
}
