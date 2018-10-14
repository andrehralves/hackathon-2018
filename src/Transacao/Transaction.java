/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transacao;

import Utils.Arquivo;
import Utils.Mensagem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Raizen
 */
public class Transaction {

    protected static String PU = getFirstPersistenceUs();
    private static EntityManagerFactory emf = startConnection();
    private static ArrayList<String> PUs = null;

    public static EntityManagerFactory startConnection() {
        EntityManagerFactory emfNew;
        try {
            emfNew = Persistence.createEntityManagerFactory(PU);
            if (emfNew != null) {
                writeLastPersistenceUs(PU);
            }
        } catch (Exception ex) {
            emfNew = null;
        }
        return emfNew;
    }

    public static boolean Connect() {
        try {
            emf = Persistence.createEntityManagerFactory(PU);
            if (emf != null) {
                writeLastPersistenceUs(PU);
            }
        } catch (Exception ex) {
            emf = null;
        }
        return isStarted();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    protected static void writeLastPersistenceUs(String PU) {
        ArrayList<String> PUs = new ArrayList();
        PUs.add(PU);
        Arquivo.gravaArquivoDeStringUTF("LastPersistenceUnit.db", PUs);
    }

    protected static String readLastPersistenceUs() {
        ArrayList<String> PUs = null;
        PUs = Arquivo.leArquivoDeStringUTF("LastPersistenceUnit.db", 1);
        return PUs != null && !PUs.isEmpty() ? PUs.get(0) : "";
    }

    public static ArrayList<String> getPersistenceUs() {
        ArrayList<String> PUs = new ArrayList();
        PUs.add("FalconDeliveryPU");
        return PUs;
    }

    public static String getFirstPersistenceUs() {
        PU = readLastPersistenceUs();
        if (PU == null || PU.isEmpty()) {
            return (getPersistenceUs() != null && !getPersistenceUs().isEmpty())
                    ? getPersistenceUs().get(0) : "";
        } else {
            return PU;
        }
    }

    public static Boolean isStarted() {
        return emf != null;
    }

    public static boolean criarBD() {
        try {
            /*Connection conn = Persistence.createEntityManagerFactory("PostgresStart")
                    .createEntityManager().unwrap(java.sql.Connection.class);*/
            Connection conn = 
                    DriverManager.getConnection("jdbc:postgresql://localhost:5432/","postgres","postgres123");
            Statement stmt = conn.createStatement();
            int Linhas =  stmt.executeUpdate("CREATE DATABASE " + "petshop"
                    + " WITH OWNER = postgres ENCODING = 'UTF8' CONNECTION LIMIT = -1;");
            System.out.println(Linhas);
            startConnection();
        } catch (Exception e) {
            Mensagem.ExibirException(e);
            return false;
        }
        return true;
    }

    public static boolean realizaBackupRestauracao(String arqlote, TextArea ta) {
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec("backup\\" + arqlote);
            if (p != null) {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);
                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println(linha);
                    final String lin = linha;
                    Platform.runLater(()
                            -> {
                        ta.appendText(lin + "\n");
                    });
                }
                ta.appendText("Completo....." + "\n");
            }
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Realizar Backup");
            return false;
        }
        return true;
    }

    public static boolean realizaBackupRestauracao(String arqlote) {
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec("backup\\" + arqlote);
            if (p != null) {
                InputStreamReader str = new InputStreamReader(p.getErrorStream());
                BufferedReader reader = new BufferedReader(str);
                String linha;
                while ((linha = reader.readLine()) != null) {
                    System.out.println(linha);
                }
                System.out.println("Completo....." + "\n");
            }
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Realizar Restauração");
            return false;
        }
        return true;
    }

    /**
     * @param aPU the PU to set
     */
    public static void setPU(String aPU) {
        PU = aPU;
    }

    public static Boolean isCreated() {
        return true;
    }

}
