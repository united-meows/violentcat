package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.DiscordClient;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.RateListener;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.etc.VideoQuality;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.Jsons;

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

    private DiscordClient bot;

    public EditChannel(X _channel, DiscordClient _bot) {
        channel = _channel;
        position = channel.position;
        bot = _bot;
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        if (!channel.name().equals(name)) json.addProperty("name", name);
        if (channel.position != position) json.addProperty("position", position);
        if (channel.flags != flags) json.addProperty("flags", flags);
        if (channel.parentId() != null && parentId != null && channel.parentId().equals(parentId)) json.addProperty("parent_id", parentId);

        final ChannelType type = channel.type();
        if (type == ChannelType.TEXT) {
            TextChannel textChannel = channel.asTextChannel();
            if (textChannel.isNsfw() != nsfw)
                json.addProperty("nsfw", nsfw);
        } else if (type == ChannelType.VOICE) {
            VoiceChannel voiceChannel = channel.asVoiceChannel();
        }

        return Jsons.BUILDER.toJson(json);
    }

    public Action<X> end() {
        if (bot.isBot()) {
            return bot.actionPool().rateListener(Ratelimits.GUILD).queue(channel.guildId(), () -> {
                if (bot.isBot()) {
                    final String parsed = toString();
                    final String result = bot.simpleWebClient().patchRequest(DiscordHelper.route("/channels/%s", channel.id), parsed);
                    JsonObject json = Jsons.parser. parse(result).getAsJsonObject();
                    int type = json.get("type").getAsInt();
                    return GsonWrap.parse(ChannelType.from(type).type(), json).result();
                }
                return null;
            });
        }
        return null;
    }

    public EditChannel name(String _name) {
        name = _name;
        return this;
    }

    public EditChannel position(int _position) {
        position = _position;
        return this;
    }

    public EditChannel topic(String _topic) {
        topic = _topic;
        return this;
    }

    public EditChannel nsfw(boolean _nsfw) {
        nsfw = _nsfw;
        return this;
    }

    public EditChannel rateLimitPerUser(int _rateLimitPerUser) {
        rateLimitPerUser = _rateLimitPerUser;
        return this;
    }

    public EditChannel bitrate(int _bitrate) {
        bitrate = _bitrate;
        return this;
    }

    public EditChannel userLimit(int _userLimit) {
        userLimit = _userLimit;
        return this;
    }

    public EditChannel parentId(String _parentId) {
        parentId = _parentId;
        return this;
    }

    public EditChannel defaultAutoArchiveDuration(int _defaultAutoArchiveDuration) {
        defaultAutoArchiveDuration = _defaultAutoArchiveDuration;
        return this;
    }

    public EditChannel flags(int _flags) {
        flags = _flags;
        return this;
    }

    public EditChannel videoQualityMode(VideoQuality _videoQualityMode) {
        videoQualityMode = _videoQualityMode;
        return this;
    }
}
