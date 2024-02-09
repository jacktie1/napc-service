package org.apathinternational.faithpathrestful.common.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import com.google.gson.GsonBuilder;

public class LoggingJsonFormatter {
    private final String[] keysToRedact = new String[] { "password", "token", "securityAnswer1", "securityAnswer2", "securityAnswer3" };

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

            if(parsedJson.getAsJsonObject().has("userAccount")) {
                JsonElement userAccountJson = parsedJson.getAsJsonObject().get("userAccount");

                for (String keyToReact : keysToRedact) {
                    if (userAccountJson.getAsJsonObject().has(keyToReact)) {
                        userAccountJson.getAsJsonObject().remove(keyToReact);
                        userAccountJson.getAsJsonObject().addProperty(keyToReact, "********");
                    }
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
