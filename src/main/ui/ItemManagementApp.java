package ui;

import model.Item;

import java.util.ArrayList;
import java.util.Scanner;

public class ItemManagementApp {
    private Scanner input;
    ArrayList<Item> userItemList;
    Item item;


    //EFFECTS: runs the food inventory application
    public ItemManagementApp() {
        runApp();
    }

    //MODIFIERS: this
    //EFFECTS: process user input
    private void runApp() {
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
    private void chooseCommand(String command) {
        if (command.equals("A")) {
            userAddItem();
        } else if (command.equals("D")) {
            userDeleteItem();
        } else if (command.equals("S")) {
            showItemList();
        } else if (command.equals("B")) {
            showItemListUnderCount();
        } else if (command.equals("C")) {
            changeItemParameters();
        }
    }

    //MODIFIERS: this
    //EFFECTS: initialize new list
    private void init() {
        userItemList = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }


    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("--- Supermarket Item Management ---");
        System.out.println("Select from actions below:");
        System.out.println("Input 'A' to Add item");
        System.out.println("Input 'D' to Delete item");
        System.out.println("Input 'C' to Change item information");
        System.out.println("Input 'S' to Show all item in the supermarket");
        System.out.println("Input 'B' to Show all item that was lower a specific count");
        System.out.println("Input 'Q' to Quit");
    }

    //EFFECTS:
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
        userItemList.add(item);
    }


    //MODIFIERS: this
    //EFFECTS:
    private void userDeleteItem() {

        int index = 1;
        for (Item item : userItemList) {
            System.out.println(index + "# -> " + "ID: " + item.getItemID() + " | " + "Name: " + item.getItemName()
                    + " | " + "Count: " + item.getItemCount() + " | " + "Position: " + item.getItemPosition());
            index++;
        }

        System.out.println("Input the order # of item to delete:");
        int deleteItemOrder = input.nextInt();
        userItemList.remove((deleteItemOrder - 1));
    }


    //EFFECTS:
    private void showItemList() {
        int index = 1;
        for (Item item : userItemList) {
            System.out.println(index + "# -> " + "ID: " + item.getItemID() + " | " + "Name: " + item.getItemName()
                    + " | " + "Count: " + item.getItemCount() + " | " + "Position: " + item.getItemPosition() + " | "
                    + "Price included BC tax: " + item.priceAfterBCTax() + "$");
            index++;
        }
    }

    //EFFECTS:
    private void showItemListUnderCount() {
        System.out.println("Input the count:");
        int userCount = input.nextInt();

        int indexPrintList = 0;
        for (Item item : userItemList) {
            if (item.getItemCount() <= userCount) {
                indexPrintList++;
                System.out.println(indexPrintList + "# -> " + "ID: " + item.getItemID() + " | " + "Name: " + item.getItemName()
                        + " | " + "Count: " + item.getItemCount() + " | " + "Position: " + item.getItemPosition());
            }
        }
        System.out.println("-- End of the item list with the count under: " + userCount);
    }

    //EFFECTS:
    private void changeItemParameters() {
        changingMenu();
        String userChange = input.next();
        userChange = userChange.toUpperCase();


        if (userChange.equals("CI")) {
            int userChangeOption = getUserChangeOption();
            System.out.println("Input a new ID:");
            int userNewID = input.nextInt();
            Item getItemFromList = userItemList.get(userChangeOption - 1);
            getItemFromList.changeItemID(userNewID);
        } else if (userChange.equals("CN")) {
            int userChangeOption = getUserChangeOption();
            System.out.println("Input a new name:");
            String userNewName = input.next();
            Item getItemFromList = userItemList.get(userChangeOption - 1);
            getItemFromList.changeItemName(userNewName);
        } else if (userChange.equals("CC")) {
            int userChangeOption = getUserChangeOption();
            System.out.println("Input a new Count:");
            int userNewCount = input.nextInt();
            Item getItemFromList = userItemList.get(userChangeOption - 1);
            getItemFromList.changeItemCount(userNewCount);
        } else if (userChange.equals("CP")) {
            int userChangeOption = getUserChangeOption();
            System.out.println("Input a new position:");
            String userNewPosition = input.next();
            Item getItemFromList = userItemList.get(userChangeOption - 1);
            getItemFromList.changeItemPosition(userNewPosition);
        } else if (userChange.equals("CIP")) {
            int userChangeOption = getUserChangeOption();
            System.out.println("Input a new inPrice:");
            int userNewInPrice = input.nextInt();
            Item getItemFromList = userItemList.get(userChangeOption - 1);
            getItemFromList.changeItemInPrice(userNewInPrice);
        } else if (userChange.equals("COP")) {
            int userChangeOption = getUserChangeOption();
            System.out.println("Input a new outPrice:");
            int userNewOutPrice = input.nextInt();
            Item getItemFromList = userItemList.get(userChangeOption - 1);
            getItemFromList.changeItemOutPrice(userNewOutPrice);
        }
    }

    private int getUserChangeOption() {
        showItemList();
        System.out.println("Input the item order number need to change:");
        int userChangeOption = input.nextInt();
        return userChangeOption;
    }


    private void changingMenu() {
        System.out.println("Input 'CI' to change ID");
        System.out.println("Input 'CN' to change Name");
        System.out.println("Input 'CC' to change Count");
        System.out.println("Input 'CP' to change Position");
        System.out.println("Input 'CIP' to change inPrice");
        System.out.println("Input 'COP' to change outPrice\n");
        System.out.println("--- Choose information to change:");
    }
}
