package Worttrainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Rechtschreibtrainer {
    private List<Woerterpaare> woerterpaareliste;
    private Woerterpaare woerterpaare;
    private int gesamtScore;
    private int richtigerScore;
    private int falscherScore;

    public Rechtschreibtrainer() {
        this.woerterpaareliste = new ArrayList<>();
        this.gesamtScore = 0;
        this.richtigerScore = 0;
        this.falscherScore = 0;
        this.woerterpaare = null;
    }

    public void wortpaarhinzufuegen(Woerterpaare woerterpaar) {
        this.woerterpaareliste.add(woerterpaar);
    }

    public void woerterpaarauswählen(boolean zufall, int index) {

        if(zufall == true) {
            Random zufallszahl = new Random();
            int zufallIndex = zufallszahl.nextInt(this.woerterpaareliste.size());
            this.woerterpaare = this.woerterpaareliste.get(zufallIndex);
        } else {
            this.woerterpaare = this.woerterpaareliste.get(index);
        }
    }

    public void raten() {
        while (true) {
            woerterpaarauswählen(true, 0);
            String schätzung = zeigeBild(this.woerterpaare.getUrl());

            if (schätzung.equals(this.woerterpaare.getWort())) {
                this.richtigerScore++;
                this.gesamtScore++;
                JOptionPane.showMessageDialog(null, "Richtig!", "Richtig", JOptionPane.INFORMATION_MESSAGE);
            } else {
                this.falscherScore++;
                this.gesamtScore++;
                JOptionPane.showMessageDialog(null, "Falsch!", "Falsch", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    public String zeigeBild(String urlbild) {
        try {
            URL url = new URL(urlbild);
            Image icon = ImageIO.read(url);
            Image scaledImage = icon.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(imageLabel, BorderLayout.CENTER);

            JLabel scoreLabel1 = new JLabel("Gesamtscore: " + String.valueOf(this.gesamtScore));
            JLabel scoreLabel2 = new JLabel("Richtigscore: " + String.valueOf(this.richtigerScore));

            panel.add(scoreLabel1, BorderLayout.NORTH);
            panel.add(scoreLabel2, BorderLayout.SOUTH);

            String schätzung = JOptionPane.showInputDialog(null, panel, "Bild anzeigen", JOptionPane.PLAIN_MESSAGE);
            return schätzung;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ungültige URL.", "Fehler", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Fehler beim Laden des Bildes.", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
