package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EarringsTest {
    private Earrings earring;

    @BeforeEach
    void setup() {
        earring = new Earrings();
    }

    @Test
    public void constructorTest() {
        assertEquals("", earring.getMetalType());
        assertEquals("", earring.getCharmType());
        assertEquals(0, earring.getQuantity());
        assertEquals(0, earring.getPrice());
    }

    @Test
    public void setSilverMetalTypeTest() {
        earring.setMetalType("sterling silver");
        assertEquals("sterling silver", earring.getMetalType());
    }

    @Test
    public void setGoldMetalTypeTest() {
        earring.setMetalType("gold plated");
        assertEquals("gold plated", earring.getMetalType());
    }

    @Test
    public void setInvalidMetalTypeTest() {
        earring.setMetalType("rose gold");
        assertEquals("", earring.getMetalType());
    }

    @Test
    public void setStrawberryCharmTypeTest() {
        earring.setCharmType("strawberry");
        assertEquals("strawberry", earring.getCharmType());
    }

    @Test
    public void setCowCharmTypeTest() {
        earring.setCharmType("cow");
        assertEquals("cow", earring.getCharmType());
    }

    @Test
    public void setMushroomCharmTypeTest() {
        earring.setCharmType("mushroom");
        assertEquals("mushroom", earring.getCharmType());
    }

    @Test
    public void setInvalidCharmTypeTest() {
        earring.setCharmType("ariana grande");
        assertEquals("", earring.getCharmType());
    }

    @Test
    public void setQuantityTest() {
        earring.setQuantity(3);
        assertEquals(3, earring.getQuantity());
    }

    @Test
    public void calculatePriceTest() {
        earring.setMetalType("sterling silver");
        assertEquals("sterling silver", earring.getMetalType());
        earring.setQuantity(2);
        earring.calculatePrice(earring.getQuantity());
        assertEquals(20, earring.getPrice());

        earring.setMetalType("gold plated");
        assertEquals("gold plated", earring.getMetalType());
        earring.setQuantity(3);
        earring.calculatePrice(earring.getQuantity());
        assertEquals(45, earring.getPrice());
    }

    @Test
    public void setPriceInvalidMetalTest() {
        earring.setMetalType("rose gold");
        assertEquals("", earring.getMetalType());
        earring.setQuantity(3);
        earring.calculatePrice(earring.getQuantity());
        assertEquals(0, earring.getPrice());
    }

    @Test
    public void setPriceTest() {
        earring.setPrice(40);
        assertEquals(40, earring.getPrice());
    }
}
