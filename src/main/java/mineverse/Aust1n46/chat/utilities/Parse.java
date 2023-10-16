package mineverse.Aust1n46.chat.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class Parse {

    public static String parseJsonToMessage(String json) {
        StringBuilder message = new StringBuilder();
        JsonElement element = new JsonParser().parse(json);
        parseJsonElement(element, message);
        return message.toString();
    }

    private static void parseJsonElement(JsonElement element, StringBuilder message) {
        Deque<JsonElement> stack = new ArrayDeque<>();
        stack.push(element);

        while (!stack.isEmpty()) {
            JsonElement currentElement = stack.pop();

            if (currentElement.isJsonArray()) {
                for (int i = currentElement.getAsJsonArray().size() - 1; i >= 0; i--) {
                    stack.push(currentElement.getAsJsonArray().get(i));
                }
            } else if (currentElement.isJsonObject()) {
                if (currentElement.getAsJsonObject().has("text")) {
                    if (currentElement.getAsJsonObject().get("text").getAsString().equals(" [鉁揮")) {
                        continue;
                    }
                }
                JsonObject object = currentElement.getAsJsonObject();
                if (object.has("color")) {
                    String color = object.get("color").getAsString();
                    message.append(colorCode(color));
                }
                if (object.has("bold") && object.get("bold").getAsBoolean()) {
                    message.append("§l");
                }
                if (object.has("italic") && object.get("italic").getAsBoolean()) {
                    message.append("§o");
                }
                if (object.has("underlined") && object.get("underlined").getAsBoolean()) {
                    message.append("§n");
                }
                if (object.has("text")) {
                    String text = object.get("text").getAsString();
                    message.append(text);
                }
                if (object.has("extra")) {
                    for (int i = object.get("extra").getAsJsonArray().size() - 1; i >= 0; i--) {
                        stack.push(object.get("extra").getAsJsonArray().get(i));
                    }
                }
            }
        }
    }


    private static String colorCode(String color) {
        switch (color) {
            case "black":
                return "§0";
            case "dark_blue":
                return "§1";
            case "dark_green":
                return "§2";
            case "dark_aqua":
                return "§3";
            case "dark_red":
                return "§4";
            case "dark_purple":
                return "§5";
            case "gold":
                return "§6";
            case "gray":
                return "§7";
            case "dark_gray":
                return "§8";
            case "blue":
                return "§9";
            case "green":
                return "§a";
            case "aqua":
                return "§b";
            case "red":
                return "§c";
            case "light_purple":
                return "§d";
            case "yellow":
                return "§e";
            case "white":
                return "§f";
            default:
                return "";
        }
    }

}
