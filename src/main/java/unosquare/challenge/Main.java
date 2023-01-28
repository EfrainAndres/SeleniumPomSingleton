package unosquare.challenge;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("src/main/resources/data.json");
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        String name = (String) jsonObject.get("url");

        System.out.println(name);
    }
}