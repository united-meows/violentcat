package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryChannel extends Channel {

    @Expose private int position;
    @Expose private int flags;
    @Expose @SerializedName("parent_id") private String parentId;
    @Expose @SerializedName("guild_id") private String guildId;
}
