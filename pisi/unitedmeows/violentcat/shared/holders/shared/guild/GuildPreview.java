package pisi.unitedmeows.violentcat.shared.holders.shared.guild;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

import java.util.Arrays;

public class GuildPreview extends ClientOwned {

    @Expose private String id;
    @Expose private String name;

    @SerializedName("icon")
    @Expose private String iconId;
    @Expose private String splash;
    @SerializedName("discovery_splash")
    @Expose private String discoverySplash;
    @Expose private volatile String[] features;

    @SerializedName("approximate_member_count")
    @Expose private int approxMemberCount;

    @SerializedName("approximate_presence_count")
    @Expose private int approxPresenceCount;

    @Expose private String description;

    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject json) {
        final JsonArray array = json.get("features").getAsJsonArray();
        features = new String[array.size()];

        for (int i = 0; i < array.size(); i++) {
            features[i] = array.get(i).getAsString();
        }
    }


    public Action<Guild> fullInfo() {
        if (botInstance().isBot())
            return botInstance().guild(id);

        return null;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String iconId() {
        return iconId;
    }

    public String splash() {
        return splash;
    }

    public String discoverySplash() {
        return discoverySplash;
    }

    public String[] features() {
        return features;
    }

    public int approxMemberCount() {
        return approxMemberCount;
    }

    public int approxPresenceCount() {
        return approxPresenceCount;
    }

    public String description() {
        return description;
    }

    @Override
    public String  toString() {
        return "GuildPreview{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", iconId='" + iconId + '\'' +
                ", splash='" + splash + '\'' +
                ", discoverySplash='" + discoverySplash + '\'' +
                ", features=" + Arrays.toString(features) +
                ", approxMemberCount=" + approxMemberCount +
                ", approxPresenceCount=" + approxPresenceCount +
                ", description='" + description + '\'' +
                '}';
    }
}
