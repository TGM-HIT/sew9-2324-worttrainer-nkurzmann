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

    public void save(List<Woerterpaare> woerterpaareList) {
        JSONArray jsonArray = new JSONArray();

        for (Woerterpaare woerterpaar : woerterpaareList) {
            JSONObject json = new JSONObject();
            json.put("Wort", woerterpaar.getWort());
            json.put("URL", woerterpaar.getUrl());
            jsonArray.put(json);
        }

        try {
            FileWriter file = new FileWriter("/home/noah/IdeaProjects/WorttrainerReloaded/woerterpaare.json");
            file.write(jsonArray.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + jsonArray);
    }

    public List<Woerterpaare> load() {
        List<Woerterpaare> woerterpaareList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/home/noah/IdeaProjects/WorttrainerReloaded/woerterpaare.json"));

            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            bufferedReader.close();

            JSONArray jsonArray = new JSONArray(jsonStringBuilder.toString());

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String wort = jsonObject.getString("Wort");
                String url = jsonObject.getString("URL");
                Woerterpaare woerterpaar = new Woerterpaare(wort, url);
                woerterpaareList.add(woerterpaar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return woerterpaareList;
    }
}
