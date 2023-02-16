package pisi.unitedmeows.violentcat.shared.holders.shared.guild;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.BasicChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.GuildMember;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

import java.util.Arrays;
import java.util.List;

public class DetailedGuild extends Guild {

    @Expose @SerializedName("joined_at") protected String joinedAt; // todo change to date
    @Expose @SerializedName("member_count") protected int memberCount;
    @Expose protected boolean nsfw;
    @Expose protected boolean lazy;
    @Expose @SerializedName("max_stage_video_channel_users") private int maxStageVideoChannelUsers;
    // todo splash?
    @Expose protected boolean large;
    @Expose @SerializedName("nsfw_level") protected int nsfwLevel;
    @Expose List<BasicChannel> channels;
    @Expose @SerializedName("safety_alerts_channel_id") protected String safetyAlertsChannelId;
    @Expose protected List<GuildMember> members;


    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject object) {
        super.__setup(object);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DetailedGuild{");
        sb.append("joinedAt='").append(joinedAt).append('\'');
        sb.append(", memberCount=").append(memberCount);
        sb.append(", nsfw=").append(nsfw);
        sb.append(", lazy=").append(lazy);
        sb.append(", maxStageVideoChannelUsers=").append(maxStageVideoChannelUsers);
        sb.append(", large=").append(large);
        sb.append(", nsfwLevel=").append(nsfwLevel);
        sb.append(", channels=").append(channels);
        sb.append(", safetyAlertsChannelId='").append(safetyAlertsChannelId).append('\'');
        sb.append(", members=").append(members);
        sb.append(", id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", icon='").append(icon).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", splash='").append(splash).append('\'');
        sb.append(", discoverySplash='").append(discoverySplash).append('\'');
        sb.append(", features=").append(Arrays.toString(features));
        sb.append(", banner='").append(banner).append('\'');
        sb.append(", ownerId='").append(ownerId).append('\'');
        sb.append(", applicationId='").append(applicationId).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", afkChannelId='").append(afkChannelId).append('\'');
        sb.append(", afkTimeout=").append(afkTimeout);
        sb.append(", systemChannelId='").append(systemChannelId).append('\'');
        sb.append(", widgetEnabled=").append(widgetEnabled);
        sb.append(", widgetChannelId='").append(widgetChannelId).append('\'');
        sb.append(", verificationLevel=").append(verificationLevel);
        sb.append(", roles=").append(roles);
        sb.append(", defaultMessageNotifications=").append(defaultMessageNotifications);
        sb.append(", mfaLevel=").append(mfaLevel);
        sb.append(", explicitContentFilter=").append(explicitContentFilter);
        sb.append(", maxPresences=").append(maxPresences);
        sb.append(", maxMembers=").append(maxMembers);
        sb.append(", maxVideoChannelUsers=").append(maxVideoChannelUsers);
        sb.append(", vanityUrlCode='").append(vanityUrlCode).append('\'');
        sb.append(", premiumTier=").append(premiumTier);
        sb.append(", premiumSubCount=").append(premiumSubCount);
        sb.append(", systemChannelFlags=").append(systemChannelFlags);
        sb.append(", preferredLocale='").append(preferredLocale).append('\'');
        sb.append(", rulesChannelId='").append(rulesChannelId).append('\'');
        sb.append(", publicUpdatesChannelId='").append(publicUpdatesChannelId).append('\'');
        sb.append(", approxMemberCount=").append(approxMemberCount);
        sb.append(", approxPresenceCount=").append(approxPresenceCount);
        sb.append('}');
        return sb.toString();
    }
}
