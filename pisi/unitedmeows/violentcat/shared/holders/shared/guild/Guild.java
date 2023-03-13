package pisi.unitedmeows.violentcat.shared.holders.shared.guild;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.holders.bot.ClientOwned;
import pisi.unitedmeows.violentcat.shared.holders.shared.Role;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.ChannelBuilder;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

import java.util.Arrays;
import java.util.List;

public class Guild extends ClientOwned {

    @Expose protected String id;
    @Expose protected String name;
    @Expose protected String icon;
    @Expose protected String description;
    @Expose protected String splash;

    @SerializedName("discovery_splash")
    @Expose protected String discoverySplash;

    @Expose protected String[] features;

    /* todo: emojis */

    @Expose protected String banner;
    @SerializedName("owner_id")
    @Expose protected String ownerId;

    @SerializedName("application_id")
    @Expose protected String applicationId;

    @Expose protected String region;

    @SerializedName("afk_channel_id")
    @Expose protected String afkChannelId;

    @SerializedName("afk_timeout")
    @Expose protected int afkTimeout;

    @SerializedName("system_channel_id")
    @Expose protected String systemChannelId;

    @SerializedName("widget_enabled")
    @Expose protected boolean widgetEnabled;

    @SerializedName("widget_channel_id")
    @Expose protected String widgetChannelId;

    @SerializedName("verification_level")
    @Expose protected int verificationLevel;

    @Expose protected List<Role> roles;

    @SerializedName("default_message_notifications")
    @Expose protected int defaultMessageNotifications;

    @SerializedName("mfa_level")
    @Expose protected int mfaLevel;

    @SerializedName("explicit_content_filter")
    @Expose protected int explicitContentFilter;

    @SerializedName("max_presences")
    @Expose protected int maxPresences;

    @SerializedName("max_members")
    @Expose protected int maxMembers;

    @SerializedName("max_video_channel_users")
    @Expose protected int maxVideoChannelUsers;

    @SerializedName("vanity_url_code")
    @Expose protected String vanityUrlCode;

    @SerializedName("premium_tier")
    @Expose protected int premiumTier;

    @SerializedName("premium_subscription_count")
    @Expose protected int premiumSubCount;

    @SerializedName("system_channel_flags")
    @Expose protected int systemChannelFlags;

    @SerializedName("preferred_locale")
    @Expose protected String preferredLocale;

    @SerializedName("rules_channel_id")
    @Expose protected String rulesChannelId;

    @SerializedName("public_updates_channel_id")
    @Expose protected String publicUpdatesChannelId;

    @SerializedName("approximate_member_count")
    @Expose protected int approxMemberCount;

    @SerializedName("approximate_presence_count")
    @Expose protected int approxPresenceCount;

    public Action<List<Channel>> channels() {
        if (botInstance().isBot()) {
            return botInstance().guildChannels(id);
        }
        return null;
    }


    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject object) {

    }

    public String id() {
        return id;
    }

    public Action<Channel> createChannel(ChannelBuilder channelBuilder) {
        if (owner().isBot())
            return botInstance().createChannel(id, channelBuilder);
        return null;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", splash='" + splash + '\'' +
                ", discoverySplash='" + discoverySplash + '\'' +
                ", features=" + Arrays.toString(features) +
                ", banner='" + banner + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", region='" + region + '\'' +
                ", afkChannelId='" + afkChannelId + '\'' +
                ", afkTimeout=" + afkTimeout +
                ", systemChannelId='" + systemChannelId + '\'' +
                ", widgetEnabled=" + widgetEnabled +
                ", widgetChannelId='" + widgetChannelId + '\'' +
                ", verificationLevel=" + verificationLevel +
                ", roles=" + roles +
                ", defaultMessageNotifications=" + defaultMessageNotifications +
                ", mfaLevel=" + mfaLevel +
                ", explicitContentFilter=" + explicitContentFilter +
                ", maxPresences=" + maxPresences +
                ", maxMembers=" + maxMembers +
                ", maxVideoChannelUsers=" + maxVideoChannelUsers +
                ", vanityUrlCode='" + vanityUrlCode + '\'' +
                ", premiumTier=" + premiumTier +
                ", premiumSubCount=" + premiumSubCount +
                ", systemChannelFlags=" + systemChannelFlags +
                ", preferredLocale='" + preferredLocale + '\'' +
                ", rulesChannelId='" + rulesChannelId + '\'' +
                ", publicUpdatesChannelId='" + publicUpdatesChannelId + '\'' +
                ", approxMemberCount=" + approxMemberCount +
                ", approxPresenceCount=" + approxPresenceCount +
                '}';
    }
}
