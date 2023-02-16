package pisi.unitedmeows.violentcat.shared.packet.impl.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.VPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import static pisi.unitedmeows.violentcat.utils.Jsons.*;
@RegisterPacket(PacketHeaders.THREAD_CREATE)
public class VThreadUpdatePacket extends VPacket {
    private int type;
    private int totalMessageSent;

    /*thread metadata*/
    private boolean metadataLocked;
    private String metadataCreateTimestamp;
    private int metadataAutoArchiveDuration;
    private boolean metadataArchived;
    private String metadataArchiveTimestamp;

    private int rateLimitPerUser;
    private String parentId;
    private String ownerId;
    private boolean newlyCreated;
    private String name;
    private int messageCount;
    private int memberCount;
    private String lastMessageId;
    private String id;
    private String guildId;
    private int flags;

    @Override
    public void decode(JsonElement _object) {
        JsonObject object = _object.getAsJsonObject();

        System.out.println("VThreadUpdatePacket >>>>> " + object);
        type = getInt(object.get("type"));
        totalMessageSent = getInt(object.get("total_message_sent"));

        JsonObject threadMetadata = object.getAsJsonObject("thread_metadata");
        metadataLocked = getBoolean(threadMetadata.get("locked"));
        metadataCreateTimestamp = getString(threadMetadata.get("create_timestamp"));
        metadataAutoArchiveDuration = getInt(threadMetadata.get("auto_archive_duration"));
        metadataArchived = getBoolean(threadMetadata.get("archived"));
        metadataArchiveTimestamp = getString(threadMetadata.get("archive_timestamp"));

        rateLimitPerUser = getInt(object.get("rate_limit_per_user"));
        parentId = getString(object.get("parent_id"));
        ownerId = getString(object.get("owner_id"));
        newlyCreated = getBoolean(object.get("newly_created"));
        name = getString(object.get("name"));
        messageCount = getInt(object.get("message_count"));
        memberCount = getInt(object.get("member_count"));
        lastMessageId = getString(object.get("last_message_id"));
        id = getString(object.get("id"));
        guildId = getString(object.get("guild_id"));
        flags = getInt(object.get("flags"));


    }

    @Override
    public JsonObject encode() {
        return new JsonObject();
    }
}
