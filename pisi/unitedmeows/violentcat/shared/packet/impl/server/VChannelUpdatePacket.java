package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import static pisi.unitedmeows.violentcat.utils.Jsons.*;
@RegisterPacket(PacketHeaders.CHANNEL_CREATE)
public class VChannelUpdatePacket extends VPacket {

    private int version;
    private int type;
    private String topic;
    private int rateLimitPerUser;
    private int position;

    /*permission_overwrites*/
    private int permissionType;
    private String permissionId;
    private String permissionDeny;
    private String permissionAllow;


    private String parentId;
    private boolean nsfw;
    private String name;
    private String lastMessageId;
    private String id;

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

    private int flags;


    /*voice channel packet*/
    private int userLimit;
    private String rtcRegion;
    private int bitrate;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();
        System.out.println("VChannelUpdatePacket >>>>> " + object);
        version = getInt(object.get("version"));
        type = getInt(object.get("type"));//0 text 2 voice
        if(type == 2) {
            userLimit = getInt(object.get("user_limit"));
            rtcRegion = getString(object.get("rtc_region"));
            bitrate = getInt(object.get("bitrate"));
        } else {
            topic = getString(object.get("topic"));
            rateLimitPerUser = getInt(object.get("rate_limit_per_user"));
            position = getInt((object.get("position")));

            JsonArray permissionOverwrites = object.getAsJsonArray("permission_overwrites");
            if (!permissionOverwrites.isEmpty()) {
                for (int i = 0; i < permissionOverwrites.size(); i++) {
                    permissionType = getInt(permissionOverwrites.get(i).getAsJsonObject().get("type"));
                    permissionId = getString(permissionOverwrites.get(i).getAsJsonObject().get("id"));
                    permissionDeny = getString(permissionOverwrites.get(i).getAsJsonObject().get("deny"));
                    permissionAllow = getString(permissionOverwrites.get(i).getAsJsonObject().get("allow"));
                }
            }
            parentId = getString(object.get("parent_id"));
            nsfw = getBoolean(object.get("nsfw"));
            name = getString(object.get("name"));
            lastMessageId = getString(object.get("last_message_id"));
            id = getString(object.get("id"));

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

            flags = getInt(object.get("flags"));
        }
    }


    @Override
    public JsonObject encode() {
        return new JsonObject();
    }
}
