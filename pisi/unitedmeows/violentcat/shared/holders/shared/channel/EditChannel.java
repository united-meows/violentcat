package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.etc.VideoQuality;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;

public class EditChannel<X extends Channel> {

    private X channel;

    @Expose private String name;
    @Expose private int position;
    @Expose private String topic;
    @Expose private boolean nsfw;
    @Expose @SerializedName("rate_limit_per_user") private int rateLimitPerUser = -1;
    @Expose private int bitrate = -1; /* min 8k */
    @Expose @SerializedName("user_limit") private int userLimit = -1;
    @Expose @SerializedName("parent_id") private String parentId;
    @Expose @SerializedName("default_auto_archive_duration") private int defaultAutoArchiveDuration = -1;
    @Expose private int flags;
    @Expose @SerializedName("video_quality_mode") private VideoQuality videoQualityMode;

    public EditChannel(X _channel) {
        channel = _channel;
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        if (!channel.name().equals(name)) json.addProperty("name", name);
        final ChannelType type = channel.type();
        if (type == ChannelType.TEXT) {
            TextChannel textChannel = channel.asTextChannel();
            if (textChannel.isNsfw() != nsfw)
                json.addProperty("nsfw", nsfw);
        } else if (type == ChannelType.VOICE) {
            VoiceChannel voiceChannel = channel.asVoiceChannel();

        }
        return "";
    }

}
