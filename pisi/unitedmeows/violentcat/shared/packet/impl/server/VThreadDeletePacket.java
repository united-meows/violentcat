package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import static pisi.unitedmeows.violentcat.utils.Jsons.*;
@RegisterPacket(PacketHeaders.THREAD_CREATE)
public class VThreadDeletePacket extends VPacket {
    private int type;
    private String parentId;
    private String id;
    private String guildId;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();

        System.out.println("VThreadDeletePacket >>>>> " + object);
        type = getInt(object.get("type"));
        parentId = getString(object.get("parent_id"));
        id = getString(object.get("id"));
        guildId = getString(object.get("guild_id"));
    }

    @Override
    public JsonObject encode() {
        return new JsonObject();
    }
}
