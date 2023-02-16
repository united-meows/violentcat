package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import static pisi.unitedmeows.violentcat.utils.Jsons.*;
@RegisterPacket(PacketHeaders.GUILD_ROLE_DELETE)
public class VGuildRoleDeletePacket extends VPacket {
    private int version;
    private String roleId;

    /*hashes*/
    private int hashVersion;
    private String roleHash;
    private String metaDataHash;
    private String channelHash;

    private String guildId;

    /*guild_hashes*/
    private int guildHashVersion;
    private String guildRoleHash;
    private String guildMetaDataHash;
    private String guildChannelHash;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VGuildRoleDelete >>>>> " + object);
        version = getInt(object.get("version"));
        roleId = getString(object.get("role_id"));

        JsonObject hashes = object.getAsJsonObject("hashes");
        hashVersion = getInt(hashes.get("version"));
        JsonObject roles = hashes.getAsJsonObject("roles");
        roleHash = getString(roles.get("hash"));
        JsonObject metadata = hashes.getAsJsonObject("metadata");
        metaDataHash = getString(metadata.get("hash"));
        JsonObject channels = hashes.getAsJsonObject("channels");
        channelHash = getString(channels.get("hash"));

        guildId = getString(object.get("guild_id"));

        JsonObject guildHashes = object.getAsJsonObject("guild_hashes");
        guildHashVersion = getInt(guildHashes.get("version"));
        JsonObject guildRoles = guildHashes.getAsJsonObject("roles");
        guildRoleHash = getString(guildRoles.get("hash"));
        JsonObject guildMetadata = guildHashes.getAsJsonObject("metadata");
        guildMetaDataHash = getString(guildMetadata.get("hash"));
        JsonObject guildChannels = guildHashes.getAsJsonObject("channels");
        guildChannelHash = getString(guildChannels.get("hash"));

    }

    @Override
    public JsonObject encode() {
        return new JsonObject();
    }
}
