package model;

import java.util.ArrayList;
import java.util.Date;

// allow user add foods data including: Name, Quantity to store in the refrigerator
public class UserFoods {

    private String userFoodName;
    private String userFoodQuantity;
    private int userFoodRecommendedShelfLife;

    //construct the input product by user:- including Name, Quantity
    //                                    - set adding date = TODAY
    //                                    - set starting day for increment each day = 1
    public UserFoods(String name, String quantity) {
        this.userFoodName = name;
        this.userFoodQuantity = quantity;
        Date today = new Date(); //assign date of today to this adding food
        int startingDay = 1; //assign the number of today as 1
        this.userFoodRecommendedShelfLife = ////???
    }



    //MODIFIERS: this
    //EFFECTS: add user's food in the list
    public void userFoodsList() {
        ArrayList<UserFoods> userFoodsListAdded = new ArrayList<>();
        userFoodsListAdded.add(new UserFoods(userFoodName,userFoodQuantity));
    }



    //REQUIRES: have to choose one type of food products
    //EFFECTS: get the self-life time corresponding to the user's food product choice
    //TODO: how to point to the selected product in FoodProducts class and get the corresponding self-life number?
    public void referenceDate() {
        //stub
    }



    //REQUIRES: startingDay
    //MODIFIERS: this
    //EFFECTS: increase startingDay by 1 by each day according to calendar
    public void startingDayIncrement() {
        //stub
    }



    //REQUIRES: referenceDate() & userFoodDate
    //MODIFIERS: this
    //EFFECTS: return the remaining days of user's food from the adding date
    //         to today (referencing to recommended self-life)
    //         - if remaining date >= 0, print out "food can use"
    //         - if remaining date < 0, print out "out of self-life, can not use"
    public void remainingDays() {
        //subtraction of "recommended self-life day" and "startingDate"

        //stub
    }



    //MODIFIERS: this
    //EFFECTS: modify quantity of food object
    public void modifyQuantity() {
        //stub
    }



    //MODIFIERS: this
    //EFFECTS: delete food in list
    public void deleteFood() {
        //stub
    }




    //EFFECTS: return user's food name
    public String getName() {
        return userFoodName;
    }

    //EFFECTS: return user's food quality
    public String getQuantity () {
        return userFoodQuantity;
    }

}
