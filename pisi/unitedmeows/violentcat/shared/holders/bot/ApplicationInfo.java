package pisi.unitedmeows.violentcat.shared.holders.bot;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.holders.shared.DiscordTeam;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

import java.util.Arrays;

public class ApplicationInfo {

    /* todo: maybe snowflake instead of string? */
    @Expose private String id;

    /* name of the application */
    @Expose private String name;

    /* id of the application icon */
    @Expose private String icon;

    /* application description */
    @Expose private String description;

    /* summary */
    @Expose private String summary;

    /* type */
    @Expose private int type;
    @Expose private boolean hook;

    /* bot is public or not */
    @SerializedName("bot_public")
    @Expose private boolean botPublic;
    @Expose private String[] scopes;


    /* default permission requirement for the bot */
    @SerializedName("install_permission")
    @Expose private String installPermission;
    @Expose private String verifyKey;

    /* owner of the application */
    @Expose private BasicUser owner;
    @Expose private String flags;
    @Expose private transient DiscordTeam team;

    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject json) {
        JsonObject teamObject = json.get("team").getAsJsonObject();
        team = GsonWrap.parse(DiscordTeam.class, teamObject).result();
        team.__setup(teamObject);
    }


    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                ", type=" + type +
                ", hook=" + hook +
                ", botPublic=" + botPublic +
                ", scopes=" + Arrays.toString(scopes) +
                ", installPermission='" + installPermission + '\'' +
                ", verifyKey='" + verifyKey + '\'' +
                ", owner=" + owner +
                ", flags='" + flags + '\'' +
                ", team=" + team +
                '}';
    }
}
