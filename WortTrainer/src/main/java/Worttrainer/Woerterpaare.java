package Worttrainer;

public class Woerterpaare {
    private String wort;
    private String url;

    public Woerterpaare(String wort, String url) {
        this.wort = wort;
        this.url = url;
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

    public boolean check() {
        if(this.wort == null || this.url == null) {
            return false;
        }
        return true;
    }
}
