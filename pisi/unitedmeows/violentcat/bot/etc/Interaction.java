package pisi.unitedmeows.violentcat.bot.etc;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.GuildMember;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class Interaction {

    @Expose protected int version;
    @Expose protected int type;
    @Expose protected String token;
    @Expose protected Message message;
    @Expose protected GuildMember member;
    @Expose protected String locale;
    @Expose protected String id;
    @Expose protected @SerializedName("guild_locale") String guildLocale;
    @Expose protected @SerializedName("guild_id") String guildID;

    protected String customID;
    protected int componentType;


    @OnlyLibCalls
    public void __setup(JsonObject object) {
        JsonObject data = object.get("data").getAsJsonObject();
        customID = Jsons.getString(data.get("custom_id"));
        componentType = Jsons.getInt(data.get("component_type"));

    }

    public int version() {
        return version;
    }

    public Interaction version(int _version) {
        version = _version;
        return this;
    }

    public int type() {
        return type;
    }

    public Interaction type(int _type) {
        type = _type;
        return this;
    }

    public String token() {
        return token;
    }

    public Interaction token(String _token) {
        token = _token;
        return this;
    }

    public Message message() {
        return message;
    }

    public Interaction message(Message _message) {
        message = _message;
        return this;
    }

    public GuildMember member() {
        return member;
    }

    public Interaction member(GuildMember _member) {
        member = _member;
        return this;
    }

    public String locale() {
        return locale;
    }

    public Interaction locale(String _locale) {
        locale = _locale;
        return this;
    }

    public String id() {
        return id;
    }

    public Interaction id(String _id) {
        id = _id;
        return this;
    }

    public String guildLocale() {
        return guildLocale;
    }

    public Interaction guildLocale(String _guildLocale) {
        guildLocale = _guildLocale;
        return this;
    }

    public String guildID() {
        return guildID;
    }

    public Interaction guildID(String _guildID) {
        guildID = _guildID;
        return this;
    }

    public String customID() {
        return customID;
    }

    public Interaction customID(String _customID) {
        customID = _customID;
        return this;
    }

    public int componentType() {
        return componentType;
    }

    public Interaction componentType(int _componentType) {
        componentType = _componentType;
        return this;
    }
}
