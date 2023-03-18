package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

public class Channel extends ClientOwned {
    @Expose @SerializedName("type") private int typeId;
    protected ChannelType type;

    @Expose protected String id;
    @Expose protected String name;
    @Expose @SerializedName("parent_id") private String parentId;
    @Expose @SerializedName("guild_id") private String guildId;
    @Expose protected int position;
    @Expose protected int flags;

    public VoiceChannel asVoiceChannel() {
        return (VoiceChannel) this;
    }

    public TextChannel asTextChannel() {
        return (TextChannel) this;
    }

    public CategoryChannel asCategoryChannel() {
        return (CategoryChannel) this;
    }

    public <X extends Channel> EditChannel<X> edit() {
        return new EditChannel<X>((X) this, botInstance());
    }

    public ChannelType type() {
        return type;
    }

    public String name() {
        return name;
    }

    public Action<Boolean> delete() {
        if (owner().isBot()) {
            return botInstance().deleteChannel(id);
        }
        return null;
    }

    public String parentId() {
        return parentId;
    }

    public String guildId() {
        return guildId;
    }

    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject jsonObject) {
        type = ChannelType.from(typeId);
    }

}
