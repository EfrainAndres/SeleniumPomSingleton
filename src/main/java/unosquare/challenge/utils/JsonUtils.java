package unosquare.challenge.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {
    JSONParser jsonParser = new JSONParser();

    public FileReader readJsonFile() throws FileNotFoundException {
        FileReader reader = new FileReader("src/main/resources/data.json");
        return reader;
    }

    public JSONObject parseJson() throws IOException, ParseException {
        return (JSONObject) jsonParser.parse(readJsonFile());
    }
}
