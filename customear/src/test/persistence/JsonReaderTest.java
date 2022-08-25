package persistence;

import model.Earrings;
import model.Order;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// based on CPSC 210 Workspace project (JSON serialization demo)
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fakeFile.json");
        try {
            Order order = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyOrder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyOrder.json");
        try {
            Order order = reader.read();
            order.setName("John Smith");
            order.setPhoneNum(123);
            order.setTotalCost(0);
            assertEquals("John Smith", order.getName());
            assertEquals(123, order.getPhoneNum());
            assertEquals(0, order.getTotalCost());
            assertEquals(0, order.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderOrderWithItems() {
        JsonReader reader = new JsonReader("./data/testReaderOrderWithItems.json");
        try {
            Order order = reader.read();
            order.setName("John Smith");
            order.setPhoneNum(123);
            order.setTotalCost(0);
            assertEquals("John Smith", order.getName());
            assertEquals(123, order.getPhoneNum());
            assertEquals(0, order.getTotalCost());
            ArrayList<Earrings> items = order.getOrder();
            assertEquals(3, items.size());
            checkItem("sterling silver", "strawberry", 2, 20, items.get(0));
            checkItem("gold plated", "mushroom", 1, 15, items.get(1));
            checkItem("gold plated", "cow", 3, 45, items.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}