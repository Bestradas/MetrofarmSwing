/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.farmatech.metrofarm.utils;

import co.com.farmatech.metrofarm.resources.Propiedades;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author Softcaribbean
 */
public class MP3 {

    private String filename;
    private Player player;

// constructor que tom ael nombre el arhivo MP3
    public MP3(String filename) {

        this.filename = filename;
    }

    public void close() {
        if (player != null) {
            player.close();
        }
    }

// reproduce le mp3 en la tarjeta de sonido predterminada
    public void play() {
        try {
            InputStream in = Propiedades.class.getResourceAsStream(filename);

            BufferedInputStream bis = new BufferedInputStream(in);
            player = new Player(bis);
        } catch (JavaLayerException e) {
            System.out.println("No se pudo reproducir " + filename);
            System.out.println(e);
        }

// correo el proceso en un nuevo hilo para deterner la ejecucion del programa
        new Thread() {
            @Override
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();

    }
}
