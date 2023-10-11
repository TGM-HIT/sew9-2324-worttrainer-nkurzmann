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

    private String richtigoderFalsch;

    public Rechtschreibtrainer() {
        this.woerterpaareliste = new ArrayList<>();
        this.gesamtScore = 0;
        this.richtigerScore = 0;
        this.falscherScore = 0;
        this.woerterpaare = null;
        this.richtigoderFalsch = "";
    }

    public void wortpaarhinzufuegen(Woerterpaare woerterpaar) {
        this.woerterpaareliste.add(woerterpaar);
    }

    public List <Woerterpaare> getWoerterpaare() {
        return this.woerterpaareliste;
    }

    public int getInsgesamt() {
        return this.gesamtScore;
    }

    public int getRichtig() {
        return this.richtigerScore;
    }

    public int getFalsch() {
        return this.falscherScore;
    }

    public void setInsgesamt(int gesamtScore) {
        this.gesamtScore = gesamtScore;
    }

    public void setRichtig(int richtigerScore) {
        this.richtigerScore = richtigerScore;
    }

    public void setFalsch(int falscherScore) {
        this.falscherScore = falscherScore;
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
        Speicherstrategie json = new Json();
        json.load(this);
        String eingabe = null;
        boolean random = false;
        String entscheidung = null;
        while (eingabe != "") {
            if(random == false) {
                entscheidung = JOptionPane.showInputDialog(null, "Index des Wortpaares eingeben(Y) oder zufällig auswählen lassen.(N)");
                if (entscheidung.equals("Y") || entscheidung.equals("y")) {
                    String index = JOptionPane.showInputDialog(null, "Index des Wortpaares eingeben.");
                    woerterpaarauswählen(false, Integer.parseInt(index));
                } else if (entscheidung.equals("N") || entscheidung.equals("n")) {
                    random = true;
                    woerterpaarauswählen(true, 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Ungültige Eingabe.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
            String schätzung = zeigeBild(this.woerterpaare.getUrl());

            if(schätzung.equals("")) {
                json.equals(this);
                break;
            }

            if (schätzung.equals(this.woerterpaare.getWort())) {
                this.richtigerScore++;
                this.gesamtScore++;
                woerterpaarauswählen(true, 0);
                this.richtigoderFalsch = "Richtig!";
            } else {
                this.falscherScore++;
                this.gesamtScore++;
                this.richtigoderFalsch = "Falsch!";
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


            JLabel richtigoderFalsch = new JLabel(this.richtigoderFalsch);
            JLabel gesamtscore = new JLabel("Gesamtscore: " + this.gesamtScore);
            JLabel richtigscore = new JLabel("Richtigscore: " + this.richtigerScore);
            JLabel falschscore = new JLabel("Falschscore: " + this.falscherScore);

            panel.add(gesamtscore, BorderLayout.NORTH);
            panel.add(richtigscore, BorderLayout.SOUTH);
            panel.add(falschscore, BorderLayout.EAST);
            panel.add(richtigoderFalsch, BorderLayout.WEST);

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
