package model;


// represent the item has parameters: ID, Name, Count, Position, inPrice, outPrice
public class Item {

    private int itemID;
    private String itemName;
    private int itemCount;
    private String itemPosition;
    private double itemInPrice; //price per count
    private double itemOutPrice; //price per count


    //construct the item
    public Item(int id, String name, int count, String position,double inPrice, double outPrice) {
        this.itemID = id;
        this.itemName = name;
        this.itemCount = count;
        this.itemPosition = position;
        this.itemInPrice = inPrice;
        this.itemOutPrice = outPrice;
    }

    //EFFECTS: after tax
    public double priceAfterBCTax() {
        return (itemOutPrice + itemOutPrice * 0.12);
    }

    //method of change -----------------------------------------------------

    //EFFECTS: change item's ID
    public int changeItemID(int newID) {
        this.itemID = newID;
        return itemID;
    }

    //EFFECTS: change item's name
    public String changeItemName(String newName) {
        this.itemName = newName;
        return itemName;
    }

    //EFFECTS: change item's count
    public int changeItemCount(int newCount) {
        this.itemCount = newCount;
        return itemCount;
    }

    //EFFECTS: change item's position
    public String changeItemPosition(String newPosition) {
        this.itemPosition = newPosition;
        return itemPosition;
    }

    //EFFECTS: change item's in price
    public double changeItemInPrice(double newPrice) {
        this.itemInPrice = newPrice;
        return itemInPrice;
    }

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
    // -----------------------------------

}
