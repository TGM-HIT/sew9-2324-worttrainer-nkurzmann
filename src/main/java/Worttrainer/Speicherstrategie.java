package Worttrainer;

import java.util.List;

public interface Speicherstrategie {

    public void save(Rechtschreibtrainer rechtschreibtrainer);

    public void load(Rechtschreibtrainer rechtschreibtrainer);


}
