package model;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private ArrayList<Item> listTest;
    private Item item01;
    private Item item02;
    private Item item03;
    private Item item04;
    private Item item05;

    @BeforeEach
    public void setUp() {
        listTest = new ArrayList<>();
        item01 = new Item(0001,"item01 name",100,"A",100.0,110.0);
//        item02 = new Item(0002,"item02 name",200,"B",200.0,220.0);
//        item03 = new Item(0003,"item03 name",300,"A",300.0,330.0);
//        item04 = new Item(0004,"item04 name",400,"C",400.0,440.0);
//        item05 = new Item(0005,"item05 name",500,"D",500.0,550.0);

        listTest.add(item01);
//        listTest.add(item02);
//        listTest.add(item03);
//        listTest.add(item04);
//        listTest.add(item05);
    }

    @Test
    public void testConstructor() {
        assertEquals(1, item01.getItemID());
        assertEquals("item01 name", item01.getItemName());
        assertEquals(100, item01.getItemCount());
        assertEquals("A", item01.getItemPosition());
        assertEquals(100.0, item01.getItemInPrice());
        assertEquals(110.0, item01.getItemOutPrice());
    }

    @Test
    public void testPriceTax() {
        assertEquals(123.2, item01.priceAfterBCTax());
    }

    @Test
    public void testChangeID() {
        assertEquals(11,item01.changeItemID(11));
    }

    @Test
    public void testChangeName() {
        assertEquals("newname", item01.changeItemName("newname"));
    }

    @Test
    public void testChangeCount() {
        assertEquals(111, item01.changeItemCount(111));
    }

    @Test
    public void testChangePostion() {
        assertEquals("B", item01.changeItemPosition("B"));
    }

    @Test
    public void testChangeInPrice() {
        assertEquals(100.0, item01.changeItemInPrice(100.0));
    }

    @Test
    public void testChangeOutPrice() {
        assertEquals(100.0, item01.changeItemOutPrice(100.0));
    }

}
