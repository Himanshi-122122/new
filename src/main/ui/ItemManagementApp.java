package ui;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Item;
import model.ItemList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ItemManagementApp {
    private Scanner input;
    private ItemList itemsInStore;
    private Item item;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String jsonStoreAddress = "./data/itemList.json";


    //EFFECTS: runs the food inventory application
    public ItemManagementApp() throws IOException {
        runApp();
    }

    //MODIFIERS: this
    //EFFECTS: process user input
    private void runApp() throws IOException {
        boolean appStillRun = true;
        String commandInput = null;

        init();
        while (appStillRun) {
            displayMenu();
            commandInput = input.next();
            commandInput = commandInput.toUpperCase();

            if (commandInput.equals("Q")) {
                appStillRun = false;
            } else {
                chooseCommand(commandInput);
            }
        }
        System.out.println("\nItem Updating Finished!");
    }

    //EFFECTS: process user command
    private void chooseCommand(String command) throws IOException {
        switch (command) {
            case "A":
                userAddItem();
                break;
            case "D":
                userDeleteItem();
                break;
            case "S":
                showItemList();
                break;
            case "B":
                showItemListUnderCount();
                break;
            case "C":
                changeItemParameters();
                break;
            case "SAVE":
                saveItemList();
                break;
            case "LOAD":
                loadItemList();
                break;
        }
    }

    //EFFECTS: initialize new list
    private void init() throws FileNotFoundException {
        itemsInStore = new ItemList("My Store");
        jsonReader = new JsonReader(jsonStoreAddress);
        jsonWriter = new JsonWriter(jsonStoreAddress);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("-------------- SUPERMARKET STOCK MANAGEMENT ----------------");
        System.out.println("Select from actions below:");
        System.out.println("Input 'A' to Add item");
        System.out.println("Input 'D' to Delete item");
        System.out.println("Input 'C' to Change item information");
        System.out.println("Input 'S' to Show all items in the supermarket");
        System.out.println("Input 'B' to Show all items that was lower than a specific count");
        System.out.println("Input 'Q' to Quit");
        System.out.println("-------------- SAVE & LOAD DATA ----------------------------");
        System.out.println("Input 'SAVE' to Save item list");
        System.out.println("Input 'LOAD' to LOAD item list");

    }

    //MODIFIERS: this
    //EFFECTS: add item to list
    private void userAddItem() {
        System.out.println("Item's ID:");
        int valueID = input.nextInt();
        System.out.println("Item's name:");
        String valueName = input.next();
        System.out.println("Item's count:");
        int valueCount = input.nextInt();
        System.out.println("Item's position:");
        String valuePosition = input.next();
        System.out.println("Item's in price:");
        Double valueInPrice = input.nextDouble();
        System.out.println("Item's out price:");
        Double valueOutPrice = input.nextDouble();
        item = new Item(valueID, valueName, valueCount, valuePosition, valueInPrice, valueOutPrice);
        itemsInStore.addItemToList(item);
    }


    //MODIFIERS: this
    //EFFECTS: delete specific item in the list
    private void userDeleteItem() {

        int index = 1;
        for (Item item : itemsInStore.getList()) {
            System.out.println(index + "# -> " + "ID: " + item.getItemID() + " | " + "Name: " + item.getItemName()
                    + " | " + "Count: " + item.getItemCount() + " | " + "Position: " + item.getItemPosition());
            index++;
        }

        System.out.println("Input the order # of item to delete:");
        int deleteItemOrder = input.nextInt();
        itemsInStore.deleteItemInList(deleteItemOrder - 1);
    }


    //EFFECTS: show all item list in specific structured string
    private void showItemList() {
        int index = 1;
        for (Item item : itemsInStore.getList()) {
            System.out.println(index + "# -> " + "ID: " + item.getItemID() + " | " + "Name: " + item.getItemName()
                    + " | " + "Count: " + item.getItemCount() + " | " + "Position: " + item.getItemPosition() + " | "
                    + "Price included BC tax: " + item.priceAfterBCTax() + "$");
            index++;
        }
    }


    //EFFECTS: show all items which has count smaller than user's input count
    private void showItemListUnderCount() {
        System.out.println("Input the count:");
        int userCount = input.nextInt();
        int indexPrintList = 0;
        for (Item item : itemsInStore.listItemUnderCount(userCount)) {
            indexPrintList++;
            System.out.println(indexPrintList + "# -> " + "ID: " + item.getItemID() + " | " + "Name: "
                    + item.getItemName() + " | " + "Count: " + item.getItemCount() + " | " + "Position: "
                    + item.getItemPosition());
        }
        System.out.println("-- End of the item list with the count under: " + userCount);
    }

    //MODIFIERS: this
    //EFFECTS: set new ID, name, count, position, inprice, outprice for item
    private void changeItemParameters() {
        changingMenu();
        String userChange = input.next();
        userChange = userChange.toUpperCase();


        if (userChange.equals("CI")) {
            int userChangeOption = userChooseItemToChange();
            chooseChangeID(userChangeOption);
        } else if (userChange.equals("CN")) {
            int userChangeOption = userChooseItemToChange();
            chooseChangeName(userChangeOption);
        } else if (userChange.equals("CC")) {
            int userChangeOption = userChooseItemToChange();
            chooseChangeCount(userChangeOption);
        } else if (userChange.equals("CP")) {
            int userChangeOption = userChooseItemToChange();
            chooseChangePosition(userChangeOption);
        } else if (userChange.equals("CIP")) {
            int userChangeOption = userChooseItemToChange();
            chooseChangeInPrice(userChangeOption);
        } else if (userChange.equals("COP")) {
            int userChangeOption = userChooseItemToChange();
            chooseChangeOutPrice(userChangeOption);
        }
    }

    private void chooseChangeOutPrice(int userChangeOption) {
        System.out.println("Input a new outPrice:");
        int userNewOutPrice = input.nextInt();
        Item getItemFromList = itemsInStore.getItemFromList(userChangeOption - 1);
        getItemFromList.changeItemOutPrice(userNewOutPrice);
    }

    private void chooseChangeInPrice(int userChangeOption) {
        System.out.println("Input a new inPrice:");
        int userNewInPrice = input.nextInt();
        Item getItemFromList = itemsInStore.getItemFromList(userChangeOption - 1);
        getItemFromList.changeItemInPrice(userNewInPrice);
    }

    private void chooseChangePosition(int userChangeOption) {
        System.out.println("Input a new position:");
        String userNewPosition = input.next();
        Item getItemFromList = itemsInStore.getItemFromList(userChangeOption - 1);
        getItemFromList.changeItemPosition(userNewPosition);
    }

    private void chooseChangeCount(int userChangeOption) {
        System.out.println("Input a new Count:");
        int userNewCount = input.nextInt();
        Item getItemFromList = itemsInStore.getItemFromList(userChangeOption - 1);
        getItemFromList.changeItemCount(userNewCount);
    }

    private void chooseChangeName(int userChangeOption) {
        System.out.println("Input a new name:");
        String userNewName = input.next();
        Item getItemFromList = itemsInStore.getItemFromList(userChangeOption - 1);
        getItemFromList.changeItemName(userNewName);
    }

    private void chooseChangeID(int userChangeOption) {
        System.out.println("Input a new ID:");
        int userNewID = input.nextInt();
        Item getItemFromList = itemsInStore.getItemFromList(userChangeOption - 1);
        getItemFromList.changeItemID(userNewID);
    }

    //helper method
    private int userChooseItemToChange() {
        showItemList();
        System.out.println("Input the item order number need to change:");
        int userChangeOption = input.nextInt();
        return userChangeOption;
    }

    //menu for choosing what item's information need to change
    private void changingMenu() {
        System.out.println("Input 'CI' to change ID");
        System.out.println("Input 'CN' to change Name");
        System.out.println("Input 'CC' to change Count");
        System.out.println("Input 'CP' to change Position");
        System.out.println("Input 'CIP' to change inPrice");
        System.out.println("Input 'COP' to change outPrice\n");
        System.out.println("--- Choose information to change:");
    }

    // --------------------------- save & load data -----------------------
    //EFFECTS: saves the itemList to file
    private void saveItemList() {
        try {
            jsonWriter.open();
            jsonWriter.write(itemsInStore);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to read from file: " + jsonStoreAddress);
        }
    }

    //MODIFIES: this
    //EFFECTS: load itemList from file
    private void loadItemList() {
        try {
            itemsInStore = jsonReader.read();
            System.out.println("Loaded items in " + itemsInStore.getName() + "from " + jsonStoreAddress);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStoreAddress);
        }
    }
}
