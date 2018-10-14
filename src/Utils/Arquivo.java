/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Utils.Mensagem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Raizen
 */
public class Arquivo {
    private RandomAccessFile arquivo;

    public Arquivo(String Nome,String param) {
        try {
            arquivo = new RandomAccessFile(Nome, param);
        } catch (FileNotFoundException ex) {
            Mensagem.ExibirException(ex);
        }
    }
    
    public void close()
    {
        try
        {
            arquivo.close();
        } catch (IOException ex){
            Mensagem.ExibirException(ex);
        }
    }
    
    public void seekArq(int pos,int registroTam)
    {
        try
        {
            arquivo.seek(pos * registroTam);
        } catch (IOException ex){
            Mensagem.ExibirException(ex);
        }
    }

    public void truncate(long TamArquivo) //desloca eof
    {
        try
        {
            arquivo.setLength(TamArquivo);
        } catch (IOException ex){ 
            Mensagem.ExibirException(ex);
        }
    }

    public static void truncate(RandomAccessFile arquivo,long TamArquivo) //desloca eof
    {
        try
        {
            arquivo.setLength(TamArquivo);
        } catch (IOException ex){ 
            Mensagem.ExibirException(ex);
        }
    }
    
    public static void truncate(File file,long TamArquivo) //desloca eof
    {
        RandomAccessFile arquivo;
        try
        {
            arquivo = new RandomAccessFile(file,"rw");
            arquivo.setLength(TamArquivo);
            arquivo.close();
        } catch (IOException ex){ 
            Mensagem.ExibirException(ex);
        }
    }
    
    public boolean eof()  
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;                               
        } catch (IOException ex){
            Mensagem.ExibirException(ex);
        }
        return (retorno);
    }
    
    public static boolean eof(RandomAccessFile Arquivo)  
    {
        boolean retorno = false;
        try
        {
            if (Arquivo.getFilePointer() == Arquivo.length())
                retorno = true;                               
        } catch (IOException ex){
            Mensagem.ExibirException(ex);
        }
        return (retorno);
    }

    public String leString() {
        int tl = leInt();
        String aux = null;
        try {
            while(tl>0){
                aux = aux+arquivo.readChar();
                tl--;
            }
            return aux;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return null;
    }

    public int leInt() {
        try {
            return arquivo.readInt();
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return -1;
    }

    public String leStringUTF() {
        try {
            return arquivo.readUTF();
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return null;
    }
    
    public ArrayList<String> leArquivoDeStringUTF() {
        ArrayList<String> string = new ArrayList<>();
        try {
            while(!eof()){
                string.add(arquivo.readUTF());
            }
            return string;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return null;
    }
    
    public static ArrayList<String> leArquivoDeStringUTF(String Arquivo,int Tam) {
        ArrayList<String> string = new ArrayList<>();
        RandomAccessFile arquivo;
        try {
            arquivo = new RandomAccessFile(Arquivo, "rw");
            arquivo.seek(0);
            while(!eof(arquivo)&&Tam > 0){
                string.add(arquivo.readUTF());
                Tam -- ;
            }
            arquivo.close();
            return string;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return null;
    }
    
    public static boolean gravaArquivoDeStringTexto(File Arquivo,ArrayList<String> string) {
        RandomAccessFile arquivo;
        try {
            FileWriter fw = new FileWriter(Arquivo,true);
            PrintWriter pw = new PrintWriter (fw);
            for(String aux : string){
                pw.println (aux);
            }
            pw.close();
            return true;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return false;
    }
    
    public static boolean gravaArquivoDeStringUTF(String Arquivo,ArrayList<String> string) {
        RandomAccessFile arquivo;
        try {
            int Count = 0;
            arquivo = new RandomAccessFile(Arquivo, "rw");
            arquivo.seek(0);
            for(String aux : string){
                arquivo.writeUTF(aux);
                Count += aux.length() + 8;
            }
            truncate(arquivo,Count);
            arquivo.close();
            return true;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
        return false;
    }
    
    public void GravaStringUTF(String string){
        try {
            arquivo.writeUTF(string);
        } catch (IOException ex) {
            Mensagem.ExibirException(ex);
        }
    }

}
