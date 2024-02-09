package org.apathinternational.faithpathrestful.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.google.gson.GsonBuilder;

public class LoggingJsonFormatter {
    private final String[] keysToRedact = new String[] { "password", "token" };

    public String format(String jsonString) {
        Gson gson = new GsonBuilder().create();
        
        try {
            JsonElement parsedJson = gson.fromJson(jsonString, JsonElement.class);

            // password redaction
            for (String keyToReact : keysToRedact) {
                if (parsedJson.getAsJsonObject().has(keyToReact)) {
                    parsedJson.getAsJsonObject().remove(keyToReact);
                    parsedJson.getAsJsonObject().addProperty(keyToReact, "********");
                }
            }

            if(parsedJson.getAsJsonObject().has("result")) {
                JsonElement resultJson = parsedJson.getAsJsonObject().get("result");
                if(resultJson.getAsJsonObject().has("token")) {
                    resultJson.getAsJsonObject().remove("token");
                    resultJson.getAsJsonObject().addProperty("token", "********");
                }
            }

            // Parse JSON string to JSONObject
            String oneLineJsonString = parsedJson.toString();

            // Print the pretty printed JSON string
            return oneLineJsonString;
        } catch (Exception e) {
            return jsonString;
        }
    }
}
