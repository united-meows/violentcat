package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;

public class ChannelBuilder {

    @Expose private String name;
    @Expose private int type = -1;
    @Expose private String topic;
    @Expose private int userLimit = -1;
    @Expose private int rateLimitPerUser;
    @Expose private int position;
    @Expose private String parentId;
    @Expose private boolean nsfw;

    protected ChannelBuilder() {}

    public static ChannelBuilder create() {
        return new ChannelBuilder();
    }


    public ChannelBuilder type(ChannelType _type) {
        type = _type.id();
        return this;
    }

    public ChannelBuilder name(String _name) {
        name = _name;
        return this;
    }

    public ChannelBuilder topic(String _topic) {
        topic = _topic;
        return this;
    }

    public ChannelBuilder userLimit(int _userLimit) {
        userLimit = _userLimit;
        return this;
    }

    public ChannelBuilder rateLimitPerUser(int _rateLimitPerUser) {
        rateLimitPerUser = _rateLimitPerUser;
        return this;
    }

    public ChannelBuilder position(int _position) {
        position = _position;
        return this;
    }

    public ChannelBuilder parentId(String _parentId) {
        parentId = _parentId;
        return this;
    }

    public ChannelBuilder nsfw(boolean _nsfw) {
        nsfw = _nsfw;
        return this;
    }

    @Override
    public String toString() {
        final JsonObject json = new JsonObject();
        json.addProperty("name", name);
        if (type != -1) json.addProperty("type", type);
        if (topic != null && !topic.isEmpty()) json.addProperty("topic", topic);
        if (userLimit != -1) json.addProperty("user_limit", userLimit);
        if (rateLimitPerUser != -1) json.addProperty("rate_limit_per_user", rateLimitPerUser);
        if (position != -1) json.addProperty("position", position);
        if (parentId != null && !parentId.isEmpty()) json.addProperty("parent_id", parentId);
        if (nsfw) json.addProperty("nsfw", true);

        return json.toString();
    }
}
