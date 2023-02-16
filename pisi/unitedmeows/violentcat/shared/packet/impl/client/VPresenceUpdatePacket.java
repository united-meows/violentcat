package pisi.unitedmeows.violentcat.shared.packet.impl.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Presence;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(PacketHeaders.PRESENCE_UPDATE)
public class VPresenceUpdatePacket extends VPacket {

    private Presence presence;

    public VPresenceUpdatePacket(Presence _presence) {
        presence = _presence;
    }

    @Override
    public void decode(JsonElement object) {}

    @Override
    public JsonObject encode() {
        JsonObject d = new JsonObject();
        JsonArray activities = new JsonArray();
        JsonObject activitiesData = new JsonObject();
        d.addProperty("since", 99999);

        activitiesData.addProperty("name", presence.statusMessage());
        if (presence.type() != Status.NOTHING.id()) {
            activitiesData.addProperty("type", presence.type());

            if (presence.special() != null && !presence.special().isEmpty()) {
                activitiesData.addProperty("url", presence.special());
            }
        }
        activities.add(activitiesData);

        d.add("activities", activities);

        d.addProperty("status", presence.availability().code());
        d.addProperty("afk", presence.afk());
        return d;
    }
}
