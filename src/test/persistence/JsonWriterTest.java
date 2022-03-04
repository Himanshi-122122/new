package persistence;

import model.Item;
import model.ItemList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    //writing into invalid source address
    @Test
    public void invalidAddressTest() {
        try {
            ItemList listTest = new ItemList("My Store");
            JsonWriter writerTest = new JsonWriter("./data/\0itemList.json");
            writerTest.open();
            fail();
        } catch (FileNotFoundException e) {
            //passed
        }
    }

    //writing into empty list
    @Test
    public void emptyListTest() {
        try {
            ItemList listTest = new ItemList("My Store");
            JsonWriter writerTest = new JsonWriter("./data/emptyListTest.json");
            writerTest.open();
            writerTest.write(listTest);
            writerTest.close();

            JsonReader readTest = new JsonReader("./data/emptyListTest.json");
            listTest = readTest.read();
            assertEquals("My Store", listTest.getName());
            assertEquals(0, listTest.getSize());
        } catch (IOException e) {
            fail();
        }
    }

    //writing general list with some items
    @Test
    public void generalListTest() {
        try {
            ItemList listTest = new ItemList("My Store");
            listTest.addItemToList(new Item(1, "name 1", 1, "A", 1, 1));
            listTest.addItemToList(new Item(2, "name 2", 2, "B", 2, 2));
            listTest.addItemToList(new Item(3, "name 3", 3, "C", 3, 3));

            JsonWriter writerTest = new JsonWriter("./data/generalListTest.json");
            writerTest.open();
            writerTest.write(listTest);
            writerTest.close();

            JsonReader readTest = new JsonReader("./data/generalListTest.json");
            listTest = readTest.read();
            assertEquals("My Store", listTest.getName());
            jsonTest(1, "name 1", 1, "A", listTest.getItemFromList(0));
            jsonTest(2, "name 2", 2, "B", listTest.getItemFromList(1));

        } catch (IOException e) {
            fail();
        }
    }
}
