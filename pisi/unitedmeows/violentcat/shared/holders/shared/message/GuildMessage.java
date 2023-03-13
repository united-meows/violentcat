package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

/*  don't forget to add getGuild() that returns Guild object */
public class GuildMessage extends Message {
    @Expose @SerializedName("guild_id") private String guildId;

    @OnlyLibCalls
    @Deprecated
    public GuildMessage() {}

    public GuildMessage(String message) {
        super(message);
    }

    public Action<Guild> guild() {
        if (owner().type() == ClientType.BOT) {
            return botInstance().guild(guildId);
        } else {
            // todo implement
            return null;
        }
    }

    public Action<GuildPreview> guildPreview() {
        if (owner().type() == ClientType.BOT) {
            return botInstance().guildPreview(guildId);
        } else {
            // todo implement
            return null;
        }
    }
}
