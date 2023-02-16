package pisi.unitedmeows.violentcat.shared.holders.shared;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

import java.util.ArrayList;
import java.util.List;

public class DiscordTeam {

    private String id;
    private String icon;
    private String name;

    @SerializedName("owner_user_id")
    private String ownerId;

    private transient List<TeamMember> members = new ArrayList<>();

    @OnlyLibCalls
    @Deprecated
    public void __setup(JsonObject json) {
        JsonArray membersArray = json.get("members").getAsJsonArray();
        for (int i = 0; i < membersArray.size(); i++) {
            final JsonObject userWrap = membersArray.get(i).getAsJsonObject();
            final JsonObject user = userWrap.get("user").getAsJsonObject();
            final TeamMember member = GsonWrap.parse(TeamMember.class, user).result();
            member.__setup(userWrap);
            members.add(member);
        }
    }

    @Override
    public String toString() {
        return "DiscordTeam{" +
                "id='" + id + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", members=" + members +
                '}';
    }
}
