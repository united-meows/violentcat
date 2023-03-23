package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.bot.etc.Interaction;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.GsonWrap;

@RegisterPacket(PacketHeaders.INTERACTION_CREATE)
public class VInteractionCreatePacket extends VPacket {


    @Override
    public void decode(JsonElement object) {
        final GsonWrap wrap = GsonWrap.parse(Interaction.class, object.getAsJsonObject());
        Interaction interaction = wrap.result();
        System.out.println(interaction.componentType());
        System.out.println(interaction.id());
        interaction.__setup(wrap.data());
    }

    @Override
    public JsonElement encode() {
        return new JsonObject();
    }
}
