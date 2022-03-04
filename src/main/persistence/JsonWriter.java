package persistence;

import model.ItemList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    // Represents a writer that writes JSON representation of ItemList to file
    private PrintWriter writer;
    private String destination;

    //EFFECTS: constructs writer to write JSON representation of ItemList to destination file
    public JsonWriter(String destinationName) {
        this.destination = destinationName;
    }

    //MODIFIES: this
    //EFFECTS: opens writer, throw FileNotFoundException if destination file can be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: write JSON representation of ItemList as String to file
    //todo: ask for indentFactor?
    public void write(ItemList itemList) {
        JSONObject json = itemList.toJson();
        saveToFile(json.toString());
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    //MODIFIES: this
    //EFFECTS: close writer
    public void close() {
        writer.close();
    }
}
