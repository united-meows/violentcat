package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import static pisi.unitedmeows.violentcat.utils.Jsons.*;

@RegisterPacket(PacketHeaders.HEARTBEAT_INTERVAL)
public class VHeartbeatPacket extends VPacket {

    private long heartbeatInterval;

    public VHeartbeatPacket() {
    }

    @Override
    public void decode(JsonElement object) {
        System.out.println(object);
        heartbeatInterval = object.getAsJsonObject().get("heartbeat_interval").getAsLong();
    }

    @Override
    public JsonObject encode() {
        return null;
    }

    public long heartbeatInterval() {
        return heartbeatInterval;
    }
}
