package Worttrainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Woerterpaare woerterpaare1 = new Woerterpaare("Hund", "https://www.planet-wissen.de/natur/haustiere/hunde/introhundewolfgjpg104~_v-HintergrundL.jpg");
        Woerterpaare woerterpaare = new Woerterpaare("Katze", "https://www.kindernetz.de/wissen/tierlexikon/1655279778114,steckbrief-katze-102~_v-16x9@2dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg");
        Woerterpaare woerterpaare2 = new Woerterpaare("Affe", "https://image.stern.de/7561920/t/5H/v4/w1440/r1/-/affen-selfie-peta-david-slater.jpg");
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        rechtschreibtrainer.wortpaarhinzufuegen(woerterpaare);
        rechtschreibtrainer.wortpaarhinzufuegen(woerterpaare1);
        rechtschreibtrainer.wortpaarhinzufuegen(woerterpaare2);

        rechtschreibtrainer.raten();

        Speicherstrategie json = new Json();
        json.save(rechtschreibtrainer);



    }
}
