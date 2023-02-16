package pisi.unitedmeows.violentcat.shared.holders.shared;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

import java.util.Arrays;

public class TeamMember {

    @Expose private String id;
    @Expose private String username;
    @SerializedName("display_name")
    @Expose private String displayName;
    @SerializedName("avatar")
    @Expose private String avatarId;
    @SerializedName("avatar_decoration")
    @Expose private String avatarDecoration;

    @SerializedName("public_flags")
    @Expose private int publicFlags;

    @Expose private transient int membershipState;
    @Expose private transient String[] permissions;


    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject json) {
        membershipState = json.get("membership_state").getAsInt();
        final JsonArray permArray = json.get("permissions").getAsJsonArray();
        permissions = new String[permArray.size()];
        for (int i = 0; i < permArray.size(); i++) {
            permissions[i] = permArray.get(i).getAsString();
        }
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", avatarId='" + avatarId + '\'' +
                ", avatarDecoration='" + avatarDecoration + '\'' +
                ", publicFlags=" + publicFlags +
                ", membershipState=" + membershipState +
                ", permissions=" + Arrays.toString(permissions) +
                '}';
    }
}
