package Worttrainer;

import javax.swing.*;

public class Woerterpaare {
    private String wort;
    private String url;

    public Woerterpaare(String wort, String url) {
        if(wort == null || url == null) {
            JOptionPane.showMessageDialog(null, "Wort oder URL ist null");
        } else {
            this.wort = wort;
            this.url = url;
        }
    }

    public String getWort() {
        return wort;
    }

    public String getUrl() {
        return url;
    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
