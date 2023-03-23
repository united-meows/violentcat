package pisi.unitedmeows.violentcat.shared.holders.shared.message.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class Component {
    @Expose protected int type;
    @Expose protected String label;
    @Expose protected int style;
    @Expose @SerializedName("custom_id") protected String customId;

    @OnlyLibCalls
    public JsonElement toJson() {
        return JsonNull.INSTANCE;
    }
}
