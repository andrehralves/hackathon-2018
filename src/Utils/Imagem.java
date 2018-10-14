/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Raizen
 */
public class Imagem {

    public Imagem() {
    }

    public static Image capturaImagem() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG  GIF & PNG Images", "jpg", "gif", "png");  //Cria um filtro  
        fileChooser.setFileFilter(filter);  //Altera o filtro do JFileChooser
        try {
            if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                URI u = fileChooser.getSelectedFile().toURI();
                return new Image(u.toString());
            }
        } catch (Exception ex) {
            Mensagem.ExibirException(ex, "Erro ao Abrir ImAgeM");
        }
        return null;
    }

    public static WritableImage BufferedImageToImage(BufferedImage im) {
        if (im != null) {
            return SwingFXUtils.toFXImage(im, null);
        } else {
            return null;
        }
    }

    public static byte[] BufferedImageToByteArray(BufferedImage im) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(im, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        } catch (Exception ex) {
            Mensagem.ExibirException(ex, "Erro ao Converter Imagem Buffered Image Para ByteArray");
        }
        return null;
    }

    public static BufferedImage ByteArrayToBufferedImage(byte[] im) {
        try {
            return ImageIO.read(new ByteArrayInputStream(im));
        } catch (Exception ex) {
            Mensagem.ExibirException(ex, "Erro ao Converter Imagem ByteArray Para Buffered Image");
        }
        return null;
    }

    public static ByteArrayOutputStream BufferedImageToByteArrayOutputStream(RenderedImage im) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(im, "jpg", baos);
            return baos;
        } catch (IOException ex) {
            Mensagem.ExibirException(ex, "Erro ao Converter Imagem Buffered Image Para ByteArrayOutputStream");
        }
        return null;
    }

    public static InputStream BufferedImageToInputStream(RenderedImage im) {
        if (im != null) {
            return new ByteArrayInputStream(Imagem.BufferedImageToByteArrayOutputStream(im).toByteArray());
        }
        return null;
    }

    public static BufferedImage ImageToBufferedImage(Image im) {
        return SwingFXUtils.fromFXImage(im, null);/////Imagem.ByteArrayToBufferedImage(Imagem.BufferedImageToByteArrayOutputStream((RenderedImage) im).toByteArray());
    }
}
