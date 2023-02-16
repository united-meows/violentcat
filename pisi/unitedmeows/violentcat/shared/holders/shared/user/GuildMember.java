package pisi.unitedmeows.violentcat.shared.holders.shared.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GuildMember {

    @Expose protected BasicUser user;
    @Expose protected List<String> roles;
    @Expose @SerializedName("premium_since") private boolean premiumSince;
    @Expose protected boolean pending;
    @Expose protected String nick;
    @Expose @SerializedName("joined_at") protected String joinedAt;
    @Expose protected boolean deaf;
    @Expose @SerializedName("communication_disabled_until") protected String communicationDisabledUntil;
    @Expose private String avatar;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GuildMember{");
        sb.append("user=").append(user);
        sb.append(", roles=").append(roles);
        sb.append(", premiumSince=").append(premiumSince);
        sb.append(", pending=").append(pending);
        sb.append(", nick='").append(nick).append('\'');
        sb.append(", joinedAt='").append(joinedAt).append('\'');
        sb.append(", deaf=").append(deaf);
        sb.append(", communicationDisabledUntil='").append(communicationDisabledUntil).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
