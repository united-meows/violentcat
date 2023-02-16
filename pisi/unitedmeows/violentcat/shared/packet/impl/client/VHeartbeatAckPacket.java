package pisi.unitedmeows.violentcat.shared.packet.impl.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VHeartbeatPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(PacketHeaders.HEARTBEAT)
public class VHeartbeatAckPacket extends VPacket {

    private int sequence;

    public VHeartbeatAckPacket(int _sequence) {
           sequence = _sequence;
    }

    public VHeartbeatAckPacket() {
        this(-1);
    }



    @Override
    public void decode(JsonElement object) {

    }

    @Override
    public JsonElement encode() {
        if (sequence == -1)
            return null;
        return new JsonPrimitive(sequence);
    }
}
