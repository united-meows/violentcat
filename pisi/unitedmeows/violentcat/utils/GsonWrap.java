package pisi.unitedmeows.violentcat.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonWrap {
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    private JsonObject _object;
    private Object result;

    @SuppressWarnings("unchecked")
    public static GsonWrap parse(Class<?> type, String fromJson) {
        return new GsonWrap().convert(type, Jsons.parser.parse(fromJson).getAsJsonObject());
    }

    public static GsonWrap parse(Class<?> type, JsonObject fromJson) {
        return new GsonWrap().convert(type, fromJson);
    }

    @SuppressWarnings("unchecked")

    public GsonWrap convert(Class<?> type, JsonObject object) {
        _object = object;
        try {
            result = type.newInstance();
            result = gson.fromJson(object, result.getClass());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        return this;
    }

    public JsonObject data() {
        return _object;
    }

    public <X> X result() {
        return (X) result;
    }

}