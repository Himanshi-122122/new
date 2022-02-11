package model;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class FoodListTest {

    FoodProducts foodProductsTest;
    UserFoods userFoodsTest;
    FoodChart foodChartTest;
    private UserFoods f1;
    private UserFoods f2;
    private UserFoods f3;
    private UserFoods f4;

    @BeforeEach
    public void setUp() {
        f1 = new UserFoods("fresh eggs","6 counts");
        f2 = new UserFoods("cooked eggs","2 eggs");
        f3 = new UserFoods("cooked eggs","2 eggs");
        f4 = new UserFoods("cooked eggs","2 eggs");

    }

}