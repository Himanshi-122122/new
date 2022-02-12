package model;

import java.util.Date;

// allow user add foods data including: Name, Quantity to store in the refrigerator
public class UserFoods {

    private String userFoodName;
    private String userFoodQuantity;
    private Date recommendedShelfLifeDate;
    private long addingDate;
    private Date setToday = new Date();

    //construct the input product by user:- including Name, Quantity
    //                                    - set adding date = TODAY
    public UserFoods(String name, String quantity) {
        userFoodName = name;
        userFoodQuantity = quantity;
        addingDate = setToday.getTime();
        recommendedShelfLifeDate = null;
    }

    //REQUIRES:
    //MODIFIERS: this
    //EFFECTS: produce Recommended Shelf Life Date
    public void shelfLifeDate(int recommendedFromFDA) {
        recommendedShelfLifeDate = new Date(setToday.getTime() + (24 * 60 * 60 * 1000 * recommendedFromFDA));
    }



    //MODIFIERS: this
    //EFFECTS: modify quantity of food object
    public String modifyQuantity(String newQuantity) {
        userFoodQuantity = newQuantity;
        return userFoodQuantity;
    }



    //EFFECTS: return user's food name
    public String getName() {
        return userFoodName;
    }

    //EFFECTS: return user's food quality
    public String getQuantity() {
        return userFoodQuantity;
    }

    //EFFECTS: return user's food adding date
    public long getAddingDate() {
        return addingDate;
    }

    //EFFECTS: return user's food recommended shelf-life date
    public Date getRecommendedShelfLifeDate() {
        return recommendedShelfLifeDate;
    }
}
