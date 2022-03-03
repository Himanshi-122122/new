package model;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<Item> itemArrayList;
    private ArrayList<Item> itemUnderCountList;

    //construct constructor
    public ItemList() {
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

}
