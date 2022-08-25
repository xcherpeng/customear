package persistence;

import model.Earrings;

import static org.junit.jupiter.api.Assertions.assertEquals;

// based on CPSC 210 Workspace project (JSON serialization demo)
public class JsonTest {
    protected void checkItem(String metalType, String charmType, int quantity, int price, Earrings earring) {
        assertEquals(metalType, earring.getMetalType());
        assertEquals(charmType, earring.getCharmType());
        assertEquals(quantity, earring.getQuantity());
        assertEquals(price, earring.getPrice());
    }
}
