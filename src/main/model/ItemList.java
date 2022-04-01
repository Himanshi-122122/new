package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.MainGUI;

import java.util.ArrayList;

public class ItemList implements Writable {
    private ArrayList<Item> itemArrayList;
    private ArrayList<Item> itemUnderCountList;
    private static MainGUI mainGUI;
//    private Item item;
    private String listName;

    //construct constructor: create a new empty item list with specific name
    public ItemList(String name) {
        this.listName = name;
        itemArrayList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add item into the list
    public void addItemToList(Item item) {
        EventLog.getInstance().logEvent(
                new Event("Added item has name: " + item.getItemName()));
        itemArrayList.add(item);
    }

    //MODIFIES: this
    //EFFECTS: delete item with specific index from the list
    public void deleteItemInList(int index) {
//        EventLog.getInstance().logEvent(
//                new Event("Deleted item with name: " + getItemNameAtIndex(index) + " from " + this.listName));
        itemArrayList.remove(index);
    }

    //MODIFIES: this
    //EFFECTS: return item list after specific item deleted
    public ArrayList<Item> listItemDeletedItem(int order) {
        EventLog.getInstance().logEvent(
                new Event("Deleted item has name: " + getItemNameAtIndex(order)));
        itemArrayList.remove(order);
        return itemArrayList;
    }

    //MODIFIES: this
    //EFFECTS: add item which is under specific count to the list
    public ArrayList<Item> listItemUnderCount(int count) {
        EventLog.getInstance().logEvent(
                new Event("Filtered all items which are under: " + count + " count."));
        itemUnderCountList = new ArrayList<>();
        for (Item item : itemArrayList) {
            if (item.getItemCount() <= count) {
                itemUnderCountList.add(item);
            }
        }
        return itemUnderCountList;
    }

    //MODIFIES: this
    //EFFECTS: replace new data for item
    public void replaceNewInput(int itemOrder, int id, String name, int count, String pos, Double ip, Double op) {
        EventLog.getInstance().logEvent(
                new Event("Edited item has order: " + itemOrder));
        Item getItemFromList = getItemFromList(itemOrder - 1);
        getItemFromList.changeItemID(id);
        getItemFromList.changeItemName(name);
        getItemFromList.changeItemCount(count);
        getItemFromList.changeItemPosition(pos);
        getItemFromList.changeItemInPrice(ip);
        getItemFromList.changeItemOutPrice(op);
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

    //EFFECTS: get name
    public String getNameOfList() {
        return listName;
    }

    //EFFECTS: get item name at specific index
    public String getItemNameAtIndex(int index) {
        return itemArrayList.get(index).getItemName();
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
