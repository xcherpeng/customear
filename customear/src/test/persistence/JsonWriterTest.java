package persistence;

import model.Earrings;
import model.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// based on CPSC 210 Workspace project (JSON serialization demo)
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Order order = new Order();
            order.setName("John Smith");
            order.setPhoneNum(123);
            order.setTotalCost(0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyOrder() {
        try {
            Order order = new Order();
            order.setName("John Smith");
            order.setPhoneNum(123);
            order.setTotalCost(0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyOrder.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyOrder.json");
            order = reader.read();
            assertEquals("John Smith", order.getName());
            assertEquals(123, order.getPhoneNum());
            assertEquals(0, order.getTotalCost());
            assertEquals(0, order.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterOrderWithItems() {
        try {
            Order order = new Order();
            order.setName("John Smith");
            order.setPhoneNum(123);
            order.setTotalCost(80);

            Earrings earringOne = new Earrings();
            earringOne.setMetalType("sterling silver");
            earringOne.setCharmType("strawberry");
            earringOne.setQuantity(2);
            earringOne.setPrice(20);
            order.addEarrings(earringOne);

            Earrings earringTwo = new Earrings();
            earringTwo.setMetalType("gold plated");
            earringTwo.setCharmType("mushroom");
            earringTwo.setQuantity(1);
            earringTwo.setPrice(15);
            order.addEarrings(earringTwo);

            Earrings earringThree = new Earrings();
            earringThree.setMetalType("gold plated");
            earringThree.setCharmType("cow");
            earringThree.setQuantity(3);
            earringThree.setPrice(45);
            order.addEarrings(earringThree);

            JsonWriter writer = new JsonWriter("./data/testWriterOrderWithItems.json");
            writer.open();
            writer.write(order);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterOrderWithItems.json");
            order = reader.read();
            assertEquals("John Smith", order.getName());
            assertEquals(123, order.getPhoneNum());
            assertEquals(80, order.getTotalCost());
            ArrayList<Earrings> items = order.getOrder();
            assertEquals(3, items.size());
            checkItem("sterling silver", "strawberry", 2, 20, items.get(0));
            checkItem("gold plated", "mushroom", 1, 15, items.get(1));
            checkItem("gold plated", "cow", 3, 45, items.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}