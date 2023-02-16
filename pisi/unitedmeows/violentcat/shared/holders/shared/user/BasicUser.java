package pisi.unitedmeows.violentcat.shared.holders.shared.user;

import com.google.gson.annotations.Expose;

public class BasicUser {

    @Expose private String id;
    @Expose private String username;
    @Expose private String displayName;
    @Expose private String avatarId;
    @Expose private String avatarDecoration;
    @Expose private String discriminator;
    @Expose private boolean bot;
    @Expose private int publicFlags;
    @Expose private int flags;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BasicUser{");
        sb.append("id='").append(id).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", avatarId='").append(avatarId).append('\'');
        sb.append(", avatarDecoration='").append(avatarDecoration).append('\'');
        sb.append(", discriminator='").append(discriminator).append('\'');
        sb.append(", publicFlags=").append(publicFlags);
        sb.append(", flags=").append(flags);
        sb.append('}');
        return sb.toString();
    }
}