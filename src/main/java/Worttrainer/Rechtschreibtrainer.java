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

    /**
     * Default Konstruktor
     */
    public Rechtschreibtrainer() {
        this.woerterpaareliste = new ArrayList<>();
        this.gesamtScore = 0;
        this.richtigerScore = 0;
        this.falscherScore = 0;
        this.woerterpaare = null;
        this.richtigoderFalsch = "";
    }

    /**
     * Methode um ein Woerterpaar hinzuzufügen
     * @param woerterpaar Die Wörterpaare
     */
    public void wortpaarhinzufuegen(Woerterpaare woerterpaar) {
        this.woerterpaareliste.add(woerterpaar);
    }

    /**
     * Methode um die Wörterpaare zurückzugeben
     * @return
     */
    public List <Woerterpaare> getWoerterpaare() {
        return this.woerterpaareliste;
    }

    /**
     * Methode um die ingesammten Versuche zurückzugeben
     * @return Die Anzahl an insgesamten Versuchen
     */
    public int getInsgesamt() {
        return this.gesamtScore;
    }

    /**
     * Methode um die richtigen Versuche zurückzugeben
     * @return Die richtigen Versuche
     */
    public int getRichtig() {
        return this.richtigerScore;
    }

    /**
     * Methode um die falschen Versuche zurückzugeben
     * @return Die falschen Versuche
     */
    public int getFalsch() {
        return this.falscherScore;
    }

    /**
     * Methode um die Anzahl der insgesamten Versuche zu setzen
     * @param gesamtScore Die Anzahl der insgesamten Versuche
     */
    public void setInsgesamt(int gesamtScore) {
        this.gesamtScore = gesamtScore;
    }

    /**
     * Methode um die Anzahl der richtigen Versuche zu setzen
     * @param richtigerScore Die Anzahl der richtigen Versuche
     */
    public void setRichtig(int richtigerScore) {
        this.richtigerScore = richtigerScore;
    }

    /**
     * Methode um die Anzahl der falschen Versuche zu setzen
     * @param falscherScore Die Anzahl der falschen Versuche
     */
    public void setFalsch(int falscherScore) {
        this.falscherScore = falscherScore;
    }

    /**
     * Methode um ein Wörterpaar auszuwählen
     * @param zufall Gibt an ob ein zufälliges Wortpaar ausgewählt werden soll
     * @param index Der Index des Wortpaares
     */
    public void woerterpaarauswählen(boolean zufall, int index) {
        if(zufall == true) {
            Random zufallszahl = new Random();
            int zufallIndex = zufallszahl.nextInt(this.woerterpaareliste.size());
            this.woerterpaare = this.woerterpaareliste.get(zufallIndex);
        } else {
            this.woerterpaare = this.woerterpaareliste.get(index);
        }
    }

    /**
     * Methode damit der User die Wörterpaare erraten kann
     */
    public void raten() {
        Speicherstrategie json = new Json();
        json.load(this);
        String eingabe = null;
        boolean random = false;
        String entscheidung = null;
        while (eingabe != "") {
            if(random == false) {
                boolean isValidInput = false;
                while (!isValidInput) {
                    if (random == false) {
                        entscheidung = JOptionPane.showInputDialog(null, "Index des Wortpaares eingeben(Y) oder zufällig auswählen lassen.(N)");

                        if (entscheidung.equals("Y") || entscheidung.equals("y")) {
                            String index = JOptionPane.showInputDialog(null, "Index des Wortpaares eingeben.");
                            woerterpaarauswählen(false, Integer.parseInt(index));
                            isValidInput = true;
                        } else if (entscheidung.equals("N") || entscheidung.equals("n")) {
                            random = true;
                            woerterpaarauswählen(true, 0);
                            isValidInput = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Ungültige Eingabe.");
                        }
                    }
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


    /**
     * Methode um das Bild anzuzeigen
     * @param urlbild Die URL des Bildes
     * @return Die Eingabe des Users
     */
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
