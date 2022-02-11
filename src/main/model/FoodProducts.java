package model;

import java.util.ArrayList;

// allow user to choose one of the pre-listed food products
// For each food product, it will come along with recommended shelf-life duration which
// data is obtained from: https://www.fda.gov/media/74435/download
public class FoodProducts {

    //add fields to represent the changing of the product and shelf-life of adding food
    private String mainFoodCategory;
    private String foodProduct;
    private int recommendedShelfLife;


    // construct food products with main category, given name and shelf-life time by days
    public FoodProducts(String mainCategory, String product, int shelfLife) {
        this.mainFoodCategory = mainCategory;
        this.foodProduct = product;
        this.recommendedShelfLife = shelfLife;
    }

    //MODIFIERS: this
    //EFFECTS: add different kinds of food products in the main list
    public ArrayList mainFoodsList() {
        ArrayList<FoodProducts> mainList = new ArrayList<>();

        mainList.add(new FoodProducts("Eggs","Fresh, in shell",35));
        mainList.add(new FoodProducts("Eggs","Raw yolks, whites",4));
        mainList.add(new FoodProducts("Eggs","Hard cooked ",7));
        mainList.add(new FoodProducts("Eggs","Liquid pasteurized eggs",3));

        mainList.add(new FoodProducts("Deli & Packed Products",
                "Store-prepared egg, chicken, tuna, ham",5));
        mainList.add(new FoodProducts("Deli & Packed Products",
                "Pre-stuffed pork & lamb chops, chicken",1));
        mainList.add(new FoodProducts("Deli & Packed Products",
                "Store-cooked convenience meals",4));
        mainList.add(new FoodProducts("Deli & Packed Products",
                "Commercial brand vacuum-packed dinners",14));

        return null;
    }



//    //MODIFIERS: this
//    //EFFECTS: add main products in mainProductList
//    public void mainProductList() {
//        ArrayList<ArrayList> allProducts = new ArrayList<>();
//
//        allProducts.add(eggs());
//        allProducts.add(packedFoods());
//    }


}
