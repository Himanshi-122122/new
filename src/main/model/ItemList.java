package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class ItemList implements Writable {
    private ArrayList<Item> itemArrayList;
    private ArrayList<Item> itemUnderCountList;
    private String listName;

    //construct constructor
    public ItemList(String name) {
        this.listName = name;
        itemArrayList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add item into the list
    public void addItemToList(Item item) {
        itemArrayList.add(item);
    }

    //MODIFIES: this
    //EFFECTS: delete item with specific index from the list
    public void deleteItemInList(int index) {
        itemArrayList.remove(index);
    }

    //MODIFIES: this
    //EFFECTS: add item which is under specific count to the list
    public ArrayList<Item> listItemUnderCount(int count) {
        itemUnderCountList = new ArrayList<>();
        for (Item item : itemArrayList) {
            if (item.getItemCount() <= count) {
                itemUnderCountList.add(item);
            }
        }
        return itemUnderCountList;
    }

    //EFFECTS: getter
    public ArrayList<Item> getList() {
        return itemArrayList;
    }

    //EFFECTS: get specific item from list
    public Item getItemFromList(int index) {
        return itemArrayList.get(index);
    }

    //EFFECTS: get size
    public int getSize() {
        return itemArrayList.size();
    }


    //EFFECTS: construct ItemList JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listName", listName);
        json.put("allItems", itemsToJson());
        return json;
    }
    
    //EFFECTS: return all items in this ItemList to a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Item i : itemArrayList) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }
}
