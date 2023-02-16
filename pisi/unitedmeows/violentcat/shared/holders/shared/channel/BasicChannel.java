package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicChannel {

    @Expose protected int version;
    @Expose protected int type;
    @Expose protected int position;
    @Expose protected String name;
    @Expose protected String id;
    @Expose protected int flags;
    @Expose protected boolean nsfw;
    @Expose @SerializedName("last_message_id") protected String lastMessageId;
    @Expose @SerializedName("parent_id") protected String parentId;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasicChannel{");
        sb.append("version=").append(version);
        sb.append(", type=").append(type);
        sb.append(", position=").append(position);
        sb.append(", name='").append(name).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", flags=").append(flags);
        sb.append(", nsfw=").append(nsfw);
        sb.append(", lastMessageId='").append(lastMessageId).append('\'');
        sb.append(", parentId='").append(parentId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
