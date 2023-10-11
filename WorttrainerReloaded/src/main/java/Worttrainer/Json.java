package Worttrainer;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.testng.reporters.Files.readFile;

public class Json implements Speicherstrategie{
    public Json() {
    }

    public void save(Rechtschreibtrainer rechtschreibtrainer) {
        List <Woerterpaare> woerterpaareList = rechtschreibtrainer.getWoerterpaare();

        JSONArray jsonArray = new JSONArray();

        for (Woerterpaare woerterpaar : woerterpaareList) {
            JSONObject json = new JSONObject();
            json.put("Wort", woerterpaar.getWort());
            json.put("URL", woerterpaar.getUrl());
            jsonArray.put(json);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Insgesamt", rechtschreibtrainer.getInsgesamt());
        jsonArray.put(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("Richtig", rechtschreibtrainer.getRichtig());
        jsonArray.put(jsonObject);
        jsonObject = new JSONObject();
        jsonObject.put("Falsch", rechtschreibtrainer.getFalsch());
        jsonArray.put(jsonObject);


        try {
            FileWriter file = new FileWriter("/home/noah/IdeaProjects/WorttrainerReloaded/woerterpaare.json");
            file.write(jsonArray.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonArray);
    }

    public void load(Rechtschreibtrainer rechtschreibtrainer) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/noah/IdeaProjects/WorttrainerReloaded/woerterpaare.json"));

            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            bufferedReader.close();

            JSONArray jsonArray = new JSONArray(jsonStringBuilder.toString());

            for (int i = 0; i < jsonArray.length()-3; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String wort = jsonObject.getString("Wort");
                String url = jsonObject.getString("URL");
                Woerterpaare woerterpaar = new Woerterpaare(wort, url);
                rechtschreibtrainer.wortpaarhinzufuegen(woerterpaar);
            }

            JSONObject jsonObject = jsonArray.getJSONObject(jsonArray.length()-3);
            rechtschreibtrainer.setInsgesamt(jsonObject.getInt("Insgesamt"));
            jsonObject = jsonArray.getJSONObject(jsonArray.length()-2);
            rechtschreibtrainer.setRichtig(jsonObject.getInt("Richtig"));
            jsonObject = jsonArray.getJSONObject(jsonArray.length()-1);
            rechtschreibtrainer.setFalsch(jsonObject.getInt("Falsch"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
