package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ItemListTest {
    private Item item01;
    private Item item02;
    private Item item03;
    ItemList testList;


    @BeforeEach
    public void setUp() {
        testList = new ItemList("My Store");
        item01 = new Item(1, "item01 name", 100, "A", 100.0, 110.0);
        item02 = new Item(2, "item02 name", 200, "B", 200.0, 220.0);
        item03 = new Item(3, "item03 name", 300, "C", 300.0, 330.0);
        testList.addItemToList(item01);
        testList.addItemToList(item02);
        testList.addItemToList(item03);
    }

    @Test
    public void addItemTest() {
        testList.addItemToList(item01);
        assertEquals(4, testList.getSize());
    }

    @Test
    public void deleteItemTest() {
        testList.deleteItemInList(2);
        assertEquals(2,testList.getSize());
    }

    @Test
    public void listItemUnderCountTest(){
        ArrayList<Item> resultList = testList.listItemUnderCount(250);
        assertEquals(2, resultList.size());
    }

    @Test
    public void getListTest () {
        ArrayList<Item> resultList = testList.getList();
        assertEquals(3, resultList.size());
    }

    @Test
    public void getItemFromListTest() {
        Item itemTest = testList.getItemFromList(0);
        assertEquals(1, itemTest.getItemID());
    }

    @Test
    public void getSizeTest() {
        assertEquals(3, testList.getSize());
    }

    @Test
    public void getNameListTest() {
        assertEquals("My Store", testList.getNameOfList());
    }

    @Test
    public void toJsonTest() {
        JSONObject itemListToJsonTest = testList.toJson();
        assertEquals("My Store", itemListToJsonTest.getString("listName"));
        //todo: how to check itemsToJson
    }
}
