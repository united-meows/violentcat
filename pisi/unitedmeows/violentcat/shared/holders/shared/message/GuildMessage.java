package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;

/*  don't forget to add getGuild() that returns Guild object */
public class GuildMessage extends Message {
    @Expose @SerializedName("guild_id") private String guildId;

    public Action<Guild> guild() {
        return owner().guild(guildId);
    }

    public Action<GuildPreview> guildPreview() {
        return owner().guildPreview(guildId);
    }
}
