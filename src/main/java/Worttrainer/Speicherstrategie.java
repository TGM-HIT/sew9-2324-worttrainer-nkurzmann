package Worttrainer;

import java.util.List;

public interface Speicherstrategie {

    /**
     * Methode um die Woerterpaare und Statistik zu speichern
     * @param rechtschreibtrainer Der Rechtschreibtrainer
     */
    public void save(Rechtschreibtrainer rechtschreibtrainer);

    /**
     * Methode um die Woerterpaare und Statistik zu laden
     * @param rechtschreibtrainer Der Rechtschreibtrainer
     */
    public void load(Rechtschreibtrainer rechtschreibtrainer);
}
