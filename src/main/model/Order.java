package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Inspired by Teller App from CPSC 210 + based on CPSC 210 Workspace project (JSON serialization demo)
public class Order implements Writable  {
    ArrayList<Earrings> order;
    private int totalCost;
    private String name;
    private long phoneNum;

    // EFFECTS: constructs a new order with no items
    public Order() {
        order = new ArrayList<>();
        totalCost = 0;
        name = "";
        phoneNum = 0;
    }

    public ArrayList<Earrings> getOrder() {
        return order;
    }

    // EFFECTS: returns length of the order
    public int length() {
        return order.size();
    }

    // EFFECTS: adds a pair of earrings to the order
    public void addEarrings(Earrings earrings) {
        order.add(earrings);
        EventLog.getInstance().logEvent(new Event("Earrings added to order."));
    }

    // REQUIRES: order must not be empty
    // EFFECTS: removes a pair of earrings with given index
    public void removeEarrings(int index) {
        order.remove(index);
        EventLog.getInstance().logEvent(new Event("Earrings removed from order."));
    }

    // MODIFIES: this
    // EFFECTS: sums the total cost of all the earrings in the order; returns 0.00 if order has no earrings
    public int calculateTotalPrice() {
        this.totalCost = 0;
        for (Earrings e : order) {
            this.totalCost += e.getPrice();
        }
        return totalCost;
    }

    // EFFECTS: returns totalCost of the order
    public int getTotalCost() {
        return totalCost;
    }

    // EFFECTS: sets name to given name
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: sets phone number to given phone number. must be a long.
    public void setPhoneNum(long num) {
        this.phoneNum = num;
    }

    // MODIFIES: this
    // EFFECTS: sets total price to given total price
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getName() {
        return name;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    // EFFECTS: creates new JSONObject with the qualities of this order
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phone number", phoneNum);
        json.put("items", itemsToJson());
        json.put("total cost", totalCost);
        return json;
    }

    // EFFECTS: returns items in this order as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Earrings e : order) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }


}
