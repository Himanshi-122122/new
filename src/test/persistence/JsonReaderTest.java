package persistence;

import model.Item;
import model.ItemList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    //read invalid source address.
    @Test
    public void invalidAddressTest() {
        JsonReader readTest = new JsonReader("./data/invalidReaderListTest.json");
        try {
            ItemList test = readTest.read();
            fail();
        } catch (IOException e) {
            //passed
        }
    }

    //test empty item list
    @Test
    public void emptyListTest() {
        try {
            ItemList listTest = new ItemList("My Store");
            JsonWriter writerTest = new JsonWriter("./data/emptyWriterListTest.json");
            writerTest.open();
            writerTest.write(listTest);
            writerTest.close();

            JsonReader readTest = new JsonReader("./data/emptyWriterListTest.json");
            ItemList test = readTest.read();
            assertEquals("My Store", test.getNameOfList());
            assertEquals(0, test.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void readTest() {
        try {
            ItemList listTest = new ItemList("My Store");
            listTest.addItemToList(new Item(1, "name 1", 1, "A", 1, 1));
            listTest.addItemToList(new Item(2, "name 2", 2, "B", 2, 2));
            listTest.addItemToList(new Item(3, "name 3", 3, "C", 3, 3));

            JsonWriter writerTest = new JsonWriter("./data/readTest.json");
            writerTest.open();
            writerTest.write(listTest);
            writerTest.close();

            JsonReader readTest = new JsonReader("./data/readTest.json");
            ItemList readReturnItemListTest = readTest.read();
            assertEquals("My Store",readReturnItemListTest.getNameOfList());
            assertEquals(3, readReturnItemListTest.getSize());
            jsonTest(1, "name 1", 1, "A", readReturnItemListTest.getItemFromList(0));
            jsonTest(2, "name 2", 2, "B", readReturnItemListTest.getItemFromList(1));
        } catch (IOException e) {
            fail();
        }
    }

}

