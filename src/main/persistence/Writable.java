package persistence;

import org.json.JSONObject;

// based on CPSC 210 Workspace project (JSON serialization demo)
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
