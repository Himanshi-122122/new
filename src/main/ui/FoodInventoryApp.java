package ui;

import model.UserFoods;

import java.util.ArrayList;
import java.util.Scanner;

public class FoodInventoryApp {
    private Scanner input;
    private UserFoods userInput;
    private ArrayList<UserFoods> userFoodList = new ArrayList<>();


    //EFFECTS: runs the food inventory application
    public FoodInventoryApp() {
        runApp();
    }

    //MODIFIERS:
    //EFFECTS: process user input
    private void runApp() {
        boolean appStillRun = true;
        String commandInput = null;

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
        System.out.println("\nFood Inventory Updating Finished!");
    }



    //EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n--- Food Inventory application ---");
        System.out.println("\nSelect from actions below:");
        System.out.println("\nInput 'A' to Add food item");
        System.out.println("\nInput 'U' to Update food quantity");
        System.out.println("\nInput 'D' to Delete food item in the inventory");
        System.out.println("\nInput 'S' to Show all food item in the inventory");
        System.out.println("\nInput 'Q' to Quit");
    }



    //EFFECTS: process user command
    private void chooseCommand(String command) {
        if (command.equals("A")) {
            doUserAddFoodItem();
        } else if (command.equals("U")) {
            doUserUpdateFoodItem();
        } else if (command.equals("D")) {
            doUserDeleteFoodItem();
        } else if (command.equals("S")) {
            doShowList();
        }
    }




    //MODIFIERS: this
    //EFFECTS: - let user input recommended shelf-life duration from FDA directory
    //         - conduct add food name & quantity
    //         - add user's item to list
    private void doUserAddFoodItem() {
        //print all object in FoodProducts list

    }





    //MODIFIERS: this
    //EFFECTS: delete food from list
    private void doUserDeleteFoodItem() {
        //stub
    }

    //MODIFIERS: this
    //EFFECTS: modify food quantity
    private void doUserUpdateFoodItem() {
        //stub
    }


    //MODIFIERS: this
    //EFFECTS: show user food list
    private void doShowList() {
        //stub
    }
}
