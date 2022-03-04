package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    public void jsonTest(int id, String name, int count, String position, Item item) {
        assertEquals(id, item.getItemID());
        assertEquals(name, item.getItemName());
        assertEquals(count, item.getItemCount());
        assertEquals(position, item.getItemPosition());
    }
}
