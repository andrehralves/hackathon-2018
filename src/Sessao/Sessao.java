package Sessao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controladoras.CtrlPessoa;
import Entidades.Individuo;
import Utils.Imagem;
import java.awt.image.BufferedImage;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 * @author Raizen
 */
public class Sessao {

    private static Individuo individuo;

    public static void GeraPainelLogin(Label Nivel, Label Nome, Circle Image, Object Usuario) {
        if (Nivel != null && Nome != null) {
            Nome.setText(CtrlPessoa.getEmail(Usuario));
        }
        /*Circle Image*/
        if (Image != null) {
            Circle circle = Image;
            circle.setStroke(Color.SEAGREEN);
            circle.setEffect(new DropShadow(+15d, 0d, +2d, Color.DARKSEAGREEN));
            BufferedImage Foto = CtrlPessoa.getImagem(Usuario);
            if (Foto != null) {
                Image image = Imagem.BufferedImageToImage(Foto);
                circle.setFill(new ImagePattern(image));
            }
        }
    }

    private Sessao() {
    }

    public static boolean CriarSessao(String Login, String Senha) {
        boolean Resultado = false;
        return (individuo = (Individuo) CtrlPessoa.create().ValidaSecao(Login, Senha)) != null;
        
    }

    public static boolean FinalizarSessao() {
        individuo = null;
        return true;
    }

    /**
     * @return the individuo
     */
    public static Individuo getIndividuo() {
        return individuo = CtrlPessoa.Atualiza(individuo);
    }

    /**
     * @param aIndividuo the individuo to set
     */
    public static void setIndividuo(Individuo aIndividuo) {
        individuo = aIndividuo;
    }

}
