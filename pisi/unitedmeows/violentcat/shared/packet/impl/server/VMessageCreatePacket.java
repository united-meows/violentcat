package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

import static pisi.unitedmeows.violentcat.utils.Jsons.*;
@RegisterPacket(PacketHeaders.MESSAGE_CREATE)
public class VMessageCreatePacket extends VPacket {

    private Message message;



    @Override
    public void decode(JsonElement object) {
        message = new GsonWrap().convert(Message.class, object.getAsJsonObject()).result();
    }


    @Override
    public JsonObject encode() {
        return null;
    }

    public Message message() {
        return message;
    }
}
