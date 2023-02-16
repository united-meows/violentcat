package pisi.unitedmeows.violentcat.shared.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.Jsons;

public abstract class VPacket {

    public PacketHeaders header() {
        return getClass().getAnnotation(RegisterPacket.class).value();
    }

    public abstract void decode(JsonElement object);
    public abstract JsonElement encode();
}
