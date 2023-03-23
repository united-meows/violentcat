package pisi.unitedmeows.violentcat.shared.holders.shared.etc;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.utils.Jsons;

import java.io.File;

public class FileAttachment {

    protected File file;
    protected String name;
    protected String description;

    protected FileAttachment() {}

    public static FileAttachment create() {
        return new FileAttachment();
    }

    public String toString(int id) {
        return Jsons.BUILDER.toJson(toObject(id));
    }

    public FileAttachment file(File _file) {
        file = _file;
        return this;
    }

    public FileAttachment name(String _name) {
        name = _name;
        return this;
    }

    public FileAttachment description(String _description) {
        description = _description;
        return this;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public File file() {
        return file;
    }

    public JsonObject toObject(int id) {
        JsonObject json = new JsonObject();
        json.addProperty("id", id);
        json.addProperty("filename", name);
        json.addProperty("description", description);
        return json;
    }
}
