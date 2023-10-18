package Worttrainer;

import org.testng.annotations.Test;

import static org.junit.Assert.*;
import org.junit.*;

public class TestRechtschreibtrainer {
    @Test
    public void testWortpaarhinzufuegen() {
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        Woerterpaare woerterpaare = new Woerterpaare("Krabbe", "https://image.essen-und-trinken.de/11901778/t/jC/v9/w1440/r1.7778/-/krabbe-jpg--44367-.jpg");
        rechtschreibtrainer.wortpaarhinzufuegen(woerterpaare);
        assertEquals(woerterpaare, rechtschreibtrainer.getWoerterpaare().get(0));
    }

    @Test
    public void testGetInsgesamt() {
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        assertEquals(0, rechtschreibtrainer.getInsgesamt());
    }

    @Test
    public void testGetRichtig() {
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        assertEquals(0, rechtschreibtrainer.getRichtig());
    }

    @Test
    public void testGetFalsch() {
        Rechtschreibtrainer rechtschreibtrainer = new Rechtschreibtrainer();
        assertEquals(0, rechtschreibtrainer.getFalsch());
    }
}
