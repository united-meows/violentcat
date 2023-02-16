package pisi.unitedmeows.violentcat.utils;

import com.google.gson.*;

public class Jsons {
    public static final Gson BUILDER = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public static final JsonParser parser = new JsonParser();
    public static String getString(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? null : jsonElement.getAsString();
    }

    public static int getInt(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? -1 : jsonElement.getAsInt();
    }

    public static long getLong(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? -1 : jsonElement.getAsLong();
    }

    public static boolean getBoolean(JsonElement jsonElement) {
        return jsonElement == JsonNull.INSTANCE ? null : jsonElement.getAsBoolean();
    }
}