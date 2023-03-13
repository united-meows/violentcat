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

    public VoiceChannel asVoiceChannel() {
        return (VoiceChannel) this;
    }

    public TextChannel asTextChannel() {
        return (TextChannel) this;
    }

    public CategoryChannel asCategoryChannel() {
        return (CategoryChannel) this;
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

    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject jsonObject) {
        type = ChannelType.from(typeId);
    }

}
