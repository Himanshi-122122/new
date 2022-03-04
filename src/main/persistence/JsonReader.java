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
        String jsonData = readFile(sourceFileName); // call readFile() to read source file, return appended string from source file
        // jsonData is appended string gotten from source file
        JSONObject jsonObject = new JSONObject(jsonData); // construct new JSONObject from source JSON text string
        return parseItemList(jsonObject); // call parseItemList, parses JSONObject to ItemList
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

    //MODIFIES: itemList
    //EFFECTS: parses Item List from JSON and returns it
    private ItemList parseItemList(JSONObject jsonObject) { //jsonObject in String type
        String name = jsonObject.getString("listName"); //get the string associated with a key "listName"
        ItemList itemList = new ItemList(name);
        addAllItems(itemList, jsonObject); // call addItems, add all items to new list
        return itemList;
    }

    //MODIFIES: itemList
    //EFFECTS: parses Items from JSON object and adds them to Item List
    private void addAllItems(ItemList itemList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allItems");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(itemList, nextItem);
        }
    }

    //MODIFIES: itemList
    //EFFECTS: parses Item from JSONArray Object and adds it to itemList
    private void addItem(ItemList itemList, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        int count = jsonObject.getInt("count");
        String position = jsonObject.getString("position");
        Double inPrice = jsonObject.getDouble("inprice");
        Double outPrice = jsonObject.getDouble("outprice");
        Item item = new Item(id, name, count, position, inPrice, outPrice);
        itemList.addItemToList(item);
    }

}
