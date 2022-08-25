package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order testOrder;
    private Earrings earringOne, earringTwo, earringThree;

    @BeforeEach
    void setup() {
        testOrder = new Order();

        earringOne = new Earrings();
        earringOne.setMetalType("sterling silver");
        earringOne.setCharmType("strawberry");
        earringOne.setQuantity(1);
        earringOne.calculatePrice(1);

        earringTwo = new Earrings();
        earringTwo.setMetalType("gold plated");
        earringTwo.setCharmType("cow");
        earringTwo.setQuantity(2);
        earringTwo.calculatePrice(2);

        earringThree = new Earrings();
        earringThree.setMetalType("sterling silver");
        earringThree.setCharmType("mushroom");
        earringThree.setQuantity(3);
        earringThree.calculatePrice(3);


    }

    @Test
    public void orderConstructorTest() {
        assertTrue(testOrder.getOrder().isEmpty());
        assertEquals(0, testOrder.getTotalCost());
        assertEquals(0, testOrder.length());
        assertEquals("", testOrder.getName());
        assertEquals(0, testOrder.getPhoneNum());
    }

    @Test
    public void addEarringsTest() {
        assertEquals(0, testOrder.length());
        testOrder.addEarrings(earringOne);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertEquals(1, testOrder.length());

        testOrder.addEarrings(earringTwo);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertTrue(testOrder.getOrder().contains(earringTwo));
        assertEquals(2, testOrder.length());

        testOrder.addEarrings(earringThree);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertTrue(testOrder.getOrder().contains(earringTwo));
        assertTrue(testOrder.getOrder().contains(earringThree));
        assertEquals(3, testOrder.length());
    }

    @Test
    public void removeEarringsTest() {
        assertEquals(0, testOrder.length());

        testOrder.addEarrings(earringOne);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertEquals(1, testOrder.length());

        testOrder.addEarrings(earringTwo);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertTrue(testOrder.getOrder().contains(earringTwo));
        assertEquals(2, testOrder.length());

        testOrder.addEarrings(earringThree);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertTrue(testOrder.getOrder().contains(earringTwo));
        assertTrue(testOrder.getOrder().contains(earringThree));
        assertEquals(3, testOrder.length());

        testOrder.removeEarrings(2);
        assertTrue(testOrder.getOrder().contains(earringOne));
        assertTrue(testOrder.getOrder().contains(earringTwo));
        assertFalse(testOrder.getOrder().contains(earringThree));
        assertEquals(2, testOrder.length());

        testOrder.removeEarrings(0);
        assertFalse(testOrder.getOrder().contains(earringOne));
        assertTrue(testOrder.getOrder().contains(earringTwo));
        assertFalse(testOrder.getOrder().contains(earringThree));
        assertEquals(1, testOrder.length());

    }

    @Test
    public void setTotalCostOfEarringsTest() {
        testOrder.calculateTotalPrice();
        assertEquals(0, testOrder.getTotalCost());

        testOrder.addEarrings(earringOne);
        testOrder.calculateTotalPrice();
        assertEquals(10, testOrder.getTotalCost());

        testOrder.addEarrings(earringTwo);
        testOrder.calculateTotalPrice();
        assertEquals(40, testOrder.getTotalCost());

        testOrder.addEarrings(earringThree);
        testOrder.calculateTotalPrice();
        assertEquals(70, testOrder.getTotalCost());

    }


    @Test
    public void setNameTest() {
        testOrder.setName("Sam");
        assertEquals("Sam", testOrder.getName());
    }

    @Test
    public void setPhoneNumberTest() {
        testOrder.setPhoneNum(123);
        assertEquals(123, testOrder.getPhoneNum());
    }

    @Test
    public void setTotalPriceTest() {
        testOrder.setTotalCost(100);
        assertEquals(100, testOrder.getTotalCost());
    }

}
