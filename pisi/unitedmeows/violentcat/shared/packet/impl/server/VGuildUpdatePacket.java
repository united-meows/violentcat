package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import static pisi.unitedmeows.violentcat.utils.Jsons.*;
@RegisterPacket(PacketHeaders.GUILD_UPDATE)
public class VGuildUpdatePacket extends VPacket {
    private String id;
    private int verificationLevel;
    private String preferredLocale;
    private String afkChannelId;
    private String vanityUrlCode;
    private String splash;
    private String icon;
    private boolean widget;
    private String rulesChannelId;
    private int premiumTier;
    private String guildId;
    private boolean nsfw;
    private int systemChannelFlags;
    private String systemChannelId;
    private int version;
    private int maxMembers;
    private int afkTimeout;
    private String applicationId;
    private String hubType;
    private String latestOnboardingQuestionId;
    private String banner;
    private int nsfwLevel;
    private int maxStageVideoChannelUsers;
    private int maxVideoChannelUsers;
    private int premiumSubscriptionCount;
    private String region;

    /*
        "features":[
         "COMMUNITY",
         "APPLICATION_COMMAND_PERMISSIONS_V2",
         "NEWS"
      ],
     */
    private boolean premiumProgressBar;

    /*hashes*/
    private int hashVersion;
    private String roleHash;
    private String metaDataHash;
    private String channelHash;

    /*guild_hashes*/
    private int guildHashVersion;
    private String guildRoleHash;
    private String guildMetaDataHash;
    private String guildChannelHash;

    private String safetyAlertsChannelId;
    private String description;

    /*roles*/
    private int roleVersion;
    private String roleUnicodeEmoji;
    private String rolePosition;
    private String rolePermission;
    private String roleName;
    private boolean roleMentionable;
    private boolean roleManaged;
    private String roleId;
    private String roleIcon;
    private boolean roleHoist;
    private int roleFlags;
    private String roleDescription;
    private int roleColor;

    private String ownerId;

    /*emojis*/
    private int emojiVersion;
    private boolean emojiRequireColons;
    private String emojiName;
    private boolean emojiManaged;
    private String emojiId;
    private boolean emojiAvailable;
    private boolean emojiAnimated;

    private String  publicUpdatesChannelId;
    private String widgetChannelId;
    private String homeHeader;

    /*stickers*/
    /**TODO do later*/

    private String discoverySplash;
    private int explicitContentFilter;
    private int defaultMessageNotifications;
    private int mfaLevel;
    private String name;
    private int maxPresences;




    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VGuildUpdatePacket >>>>> " + object);
        id = getString(object.get("id"));
        verificationLevel = getInt(object.get("verification_level"));
        preferredLocale = getString(object.get("preferred_locale"));
        afkChannelId = getString(object.get("afk_channel_id"));
        vanityUrlCode = getString(object.get("vanity_url_code"));
        splash = getString(object.get("splash"));
        icon = getString(object.get("icon"));
        widget = getBoolean(object.get("widget_enabled"));
        rulesChannelId = getString(object.get("rules_channel_id"));
        premiumTier = getInt(object.get("premium_tier"));
        guildId = getString(object.get("guild_id"));
        nsfw = getBoolean(object.get("nsfw"));
        systemChannelFlags = getInt(object.get("system_channel_flags"));
        systemChannelId = getString(object.get("system_channel_id"));
        version = getInt(object.get("version"));
        maxMembers = getInt(object.get("max_members"));
        afkTimeout = getInt(object.get("afk_timeout"));
        applicationId = getString(object.get("application_id"));
        hubType = getString(object.get("hub_type"));
        latestOnboardingQuestionId = getString(object.get("latest_onboarding_question_id"));
        banner = getString(object.get("banner"));
        nsfwLevel = getInt(object.get("nsfw_level"));
        maxStageVideoChannelUsers = getInt(object.get("max_stage_video_channel_users"));
        maxVideoChannelUsers = getInt(object.get("max_video_channel_users"));
        premiumSubscriptionCount = getInt(object.get("premium_subscription_count"));
        region = getString(object.get("region"));
        JsonArray features = object.getAsJsonArray("features");
        premiumProgressBar = getBoolean(object.get("premium_progress_bar_enabled"));

        JsonObject hashes = object.getAsJsonObject("hashes");
        hashVersion = getInt(hashes.get("version"));
        JsonObject roles = hashes.getAsJsonObject("roles");
        roleHash = getString(roles.get("hash"));
        JsonObject metadata = hashes.getAsJsonObject("metadata");
        metaDataHash = getString(metadata.get("hash"));
        JsonObject channels = hashes.getAsJsonObject("channels");
        channelHash = getString(channels.get("hash"));

        JsonObject guildHashes = object.getAsJsonObject("guild_hashes");
        guildHashVersion = getInt(guildHashes.get("version"));
        JsonObject guildRoles = guildHashes.getAsJsonObject("roles");
        guildRoleHash = getString(guildRoles.get("hash"));
        JsonObject guildMetadata = guildHashes.getAsJsonObject("metadata");
        guildMetaDataHash = getString(guildMetadata.get("hash"));
        JsonObject guildChannels = guildHashes.getAsJsonObject("channels");
        guildChannelHash = getString(guildChannels.get("hash"));

        safetyAlertsChannelId = getString(object.get("safety_alerts_channel_id"));
        description = getString(object.get("description"));

        JsonArray rolesArray = object.getAsJsonArray("roles");
        if(!rolesArray.isEmpty()) {
            for (int i = 0; i < rolesArray.size(); i++) {
                JsonObject rolesObject = rolesArray.get(i).getAsJsonObject();
                roleVersion = getInt(rolesObject.get("version"));
                roleUnicodeEmoji = getString(rolesObject.get("unicode_emoji"));
                rolePosition = getString(rolesObject.get("position"));
                rolePermission = getString(rolesObject.get("permissions"));
                roleName = getString(rolesObject.get("name"));
                roleMentionable = getBoolean(rolesObject.get("mentionable"));
                roleManaged = getBoolean(rolesObject.get("managed"));
                roleId = getString(rolesObject.get("id"));
                roleIcon = getString(rolesObject.get("icon"));
                roleHoist = getBoolean(rolesObject.get("hoist"));
                roleFlags = getInt(rolesObject.get("flags"));
                roleDescription = getString(rolesObject.get("description"));
                roleColor = getInt(rolesObject.get("color"));
            }
        }

        ownerId = getString(object.get("owner_id"));

        JsonArray emojis = object.getAsJsonArray("emojis");
        if(!emojis.isEmpty()) {
            for (int i = 0; i < emojis.size(); i++) {
                JsonObject emoji = emojis.get(i).getAsJsonObject();
                emojiVersion = getInt(emoji.get("version"));
                JsonArray emojiRoles = emoji.getAsJsonArray("roles");/*TODO do later*/
                emojiRequireColons = getBoolean(emoji.get("require_colons"));
                emojiName = getString(emoji.get("name"));
                emojiManaged = getBoolean(emoji.get("managed"));
                emojiId = getString(emoji.get("id"));
                emojiAvailable = getBoolean(emoji.get("available"));
                emojiAnimated = getBoolean(emoji.get("animated"));
            }
        }

        publicUpdatesChannelId = getString(object.get("public_updates_channel_id"));
        widgetChannelId = getString(object.get("widget_channel_id"));
        homeHeader = getString(object.get("home_header"));

        JsonArray stickers = object.getAsJsonArray("stickers");
        if(!stickers.isEmpty()) {
            for (int i = 0; i < stickers.size(); i++) {
                /**TODO do later*/
            }
        }

        discoverySplash = getString(object.get("discovery_splash"));
        explicitContentFilter = getInt(object.get("explicit_content_filter"));
        defaultMessageNotifications = getInt(object.get("default_message_notifications"));
        mfaLevel = getInt(object.get("mfa_level"));
        name = getString(object.get("name"));
        maxPresences = getInt(object.get("max_presences"));

    }

    @Override
    public JsonObject encode() {
        return new JsonObject();
    }
}
