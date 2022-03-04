package persistence;

import model.Item;
import model.ItemList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    //Represent a reader that read ItemList from JSON data stored in file
    private String sourceFileName;

    //EFFECTS: constructs reader to read JSON data from source file
    public JsonReader(String source) {
        this.sourceFileName = source;
    }

    //EFFECTS: read Item List from file and return it
    //throws IOException if an error reading data from file
    //todo: review again
    public ItemList read() throws IOException {
        String jsonData = readFile(sourceFileName); // call readFile(), return appended string from source file
        JSONObject jsonObject = new JSONObject(jsonData); // construct new JSONObject from source JSON text string
        return parseItemList(jsonObject); // parses
    }

    //EFFECTS: reads source file as string and returns it
    //Todo: review this method to fully comprehend
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses Item List from JSON and returns it
    private ItemList parseItemList(JSONObject jsonObject) {
        String name = jsonObject.getString("listName");
        ItemList itemList = new ItemList(name);
        addItems(itemList, jsonObject); // add all items to new list
        return itemList;
    }

    //EFFECTS: parses Items from JSON object and adds them to Item List
    private void addItems(ItemList itemList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allItems");

    }

}
