package persistence;

import model.Earrings;
import model.Order;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// based on CPSC 210 Workspace project (JSON serialization demo)
// Represents a reader that reads order from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads order from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Order read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOrder(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses order from JSON object and returns it
    private Order parseOrder(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        long phoneNum = jsonObject.getInt("phone number");
        int totalCost = jsonObject.getInt("total cost");

        Order order = new Order();
        order.setName(name);
        order.setPhoneNum(phoneNum);
        addItems(order, jsonObject);
        order.setTotalCost(totalCost);
        return order;
    }

    // MODIFIES: order
    // EFFECTS: parses items from JSON object and adds them to order
    private void addItems(Order order, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addItem(order, nextThingy);
        }
    }

    // MODIFIES: order
    // EFFECTS: parses item from JSON object and adds it to order
    private void addItem(Order order, JSONObject jsonObject) {
        String metalType = jsonObject.getString("metal type");
        String charmType = jsonObject.getString("charm type");
        int quantity = jsonObject.getInt("quantity");
        int price = jsonObject.getInt("price");

        Earrings earrings = new Earrings();
        earrings.setMetalType(metalType);
        earrings.setCharmType(charmType);
        earrings.setQuantity(quantity);
        earrings.setPrice(price);
        order.addEarrings(earrings);
    }


}
