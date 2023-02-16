package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @Expose private String id;
    @Expose private String username;
    @Expose @SerializedName("display_name") private String displayName;
    @Expose private String avatar;
    @Expose @SerializedName("avatar_decoration") private int avatarDecoration;
    @Expose private String discriminator;
    @Expose @SerializedName("public_flags") private int publicFlags;
    @Expose private boolean bot;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("id='").append(id).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", displayName='").append(displayName).append('\'');
        sb.append(", avatar='").append(avatar).append('\'');
        sb.append(", avatarDecoration=").append(avatarDecoration);
        sb.append(", discriminator='").append(discriminator).append('\'');
        sb.append(", publicFlags=").append(publicFlags);
        sb.append(", bot=").append(bot);
        sb.append('}');
        return sb.toString();
    }
}
