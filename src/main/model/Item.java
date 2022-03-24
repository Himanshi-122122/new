package model;


import netscape.javascript.JSObject;
import org.json.JSONObject;
import persistence.Writable;

// represent the item has parameters: ID, Name, Count, Position, inPrice, outPrice
public class Item implements Writable {

    private int itemID;
    private String itemName;
    private int itemCount;
    private String itemPosition;
    private double itemInPrice; //price per count
    private double itemOutPrice; //price per count


    //construct the item
    //input the ID, name, count, position and in price, out price for item
    public Item(int id, String name, int count, String position,double inPrice, double outPrice) {
        this.itemID = id;
        this.itemName = name;
        this.itemCount = count;
        this.itemPosition = position;
        this.itemInPrice = inPrice;
        this.itemOutPrice = outPrice;
    }

    //MODIFIES: this
    //EFFECTS: after tax
    public double priceAfterBCTax() {
        return (itemOutPrice + itemOutPrice * 0.12);
    }

    //method of change -----------------------------------------------------

    //MODIFIES: this
    //EFFECTS: change item's ID
    public int changeItemID(int newID) {
        this.itemID = newID;
        return itemID;
    }

    //MODIFIES: this
    //EFFECTS: change item's name
    public String changeItemName(String newName) {
        this.itemName = newName;
        return itemName;
    }

    //MODIFIES: this
    //EFFECTS: change item's count
    public int changeItemCount(int newCount) {
        this.itemCount = newCount;
        return itemCount;
    }

    //MODIFIES: this
    //EFFECTS: change item's position
    public String changeItemPosition(String newPosition) {
        this.itemPosition = newPosition;
        return itemPosition;
    }

    //MODIFIES: this
    //EFFECTS: change item's in price
    public double changeItemInPrice(double newPrice) {
        this.itemInPrice = newPrice;
        return itemInPrice;
    }

    //MODIFIES: this
    //EFFECTS: change item's out price
    public double changeItemOutPrice(double newPrice) {
        this.itemOutPrice = newPrice;
        return itemOutPrice;
    }

    //getters & setters ----------------------------------------------------

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getItemPosition() {
        return itemPosition;
    }

    public double getItemInPrice() {
        return itemInPrice;
    }

    public double getItemOutPrice() {
        return itemOutPrice;
    }

    // ------------------------------------------

    //EFFECTS: construct Item JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", itemID);
        json.put("name", itemName);
        json.put("count", itemCount);
        json.put("position", itemPosition);
        json.put("inprice", itemInPrice);
        json.put("outprice", itemOutPrice);
        return json;
    }

}
