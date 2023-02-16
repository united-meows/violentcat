package pisi.unitedmeows.violentcat.shared.packet.impl.client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;

@RegisterPacket(PacketHeaders.IDENTIFY)
public class VIdentifyPacket extends VPacket {

    private final String token;
    private final int intents;
    private final String os;
    private final String browser;
    private final String device;
    private boolean compress;

    public VIdentifyPacket(String _token, int _intents, boolean _compress, String _os, String _browser, String _device) {
        token = _token;
        intents = _intents;
        compress = _compress;
        os = _os;
        browser = _browser;
        device = _device;
    }

    @Override
    public void decode(JsonElement object) {

    }


    @Override
    public JsonObject encode() {
        JsonObject d = new JsonObject();
        JsonObject properties = new JsonObject();
        d.addProperty("token", token);
        d.addProperty("intents", intents);
        d.addProperty("compress", compress);
        properties.addProperty("os", os);
        properties.addProperty("browser", browser);
        properties.addProperty("device", device);
        d.add("properties", properties);
        return d;
    }
}
