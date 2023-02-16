package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(PacketHeaders.HEARTBEAT_CONFIRM)
public class VHeartbeatConfirmPacket extends VPacket {

    private int sequence;

    @Override
    public JsonObject encode() {
        return null;
    }

    @Override
    public void decode(JsonElement object) {
        sequence = object == JsonNull.INSTANCE   ? -1 : object.getAsInt();
    }

    public int sequence() {
        return sequence;
    }
}
