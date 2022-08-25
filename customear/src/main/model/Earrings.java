package model;

import org.json.JSONObject;
import persistence.Writable;

// Inspired by Teller App from CPSC 210 + based on CPSC 210 Workspace project (JSON serialization demo)
public class Earrings implements Writable {
    private String metalType;
    private String charmType;
    private int quantity;
    private int price;

    // EFFECTS: constructs a new pair of earrings with default information
    public Earrings() {
        metalType = "";
        charmType = "";
        quantity = 0;
        price = 0;

    }


    public String getMetalType() {
        return metalType;
    }

    public String getCharmType() {
        return charmType;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }


    // REQUIRES: user must select 1 or 2
    public void setMetalType(String selection) {
        if (selection.equals("sterling silver")) {
            this.metalType = "sterling silver";
        } else if (selection.equals("gold plated")) {
            this.metalType = "gold plated";
        }
    }


    // REQUIRES: user must select strawberry, cow or mushroom
    public void setCharmType(String selection) {
        if (selection.equals("strawberry")) {
            this.charmType = "strawberry";
        } else if (selection.equals("cow")) {
            this.charmType = "cow";
        } else if (selection.equals("mushroom")) {
            this.charmType = "mushroom";
        }

    }

    // REQUIRES: given quantity must be greater than zero
    // EFFECTS: sets the quantity of earrings and multiplies quantity by price according
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // MODIFIES: this
    // EFFECTS: calculates the price of the earrings.
    public void calculatePrice(int quantity) {
        if (metalType.equals("sterling silver")) {
            this.price = 10 * quantity;
        } else if (metalType.equals("gold plated")) {
            this.price = 15 * quantity;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the price of earrings to be given price
    public void setPrice(int price) {
        this.price = price;
    }

    // EFFECTS: creates new JSONObject with the qualities of this earrings
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("metal type", metalType);
        json.put("charm type", charmType);
        json.put("quantity", quantity);
        json.put("price", price);
        return json;
    }
}



