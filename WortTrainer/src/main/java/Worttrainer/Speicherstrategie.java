package Worttrainer;

import java.util.List;

public interface Speicherstrategie {

    public void save(List<Woerterpaare> woerterpaareList);

    public List<Woerterpaare> load();


}
