package fr.zunf1x.mc2d.utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtils {

    public static JSONArray readFromJSON(String fileName) {
        JSONParser parser = new JSONParser();
        JSONArray array = new JSONArray();

        try {
            FileReader reader = new FileReader(fileName);
            Object obj = parser.parse(reader);
            array = (JSONArray) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return array;
    }

    public static void writeToJSON(JSONArray array, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(array.toJSONString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
