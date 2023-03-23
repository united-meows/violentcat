package pisi.unitedmeows.violentcat.shared.packet;

public enum PacketHeaders {

    MESSAGE_CREATE(0, "MESSAGE_CREATE"),

    CHANNEL_CREATE(0, "CHANNEL_CREATE"),
    CHANNEL_DELETE(0, "CHANNEL_DELETE"),
    CHANNEL_UPDATE(0, "CHANNEL_UPDATE"),

    GUILD_CREATE(0, "GUILD_CREATE"),
    GUILD_UPDATE(0, "GUILD_UPDATE"),
    GUILD_ROLE_CREATE(0, "GUILD_ROLE_CREATE"),
    GUILD_ROLE_DELETE(0, "GUILD_ROLE_DELETE"),
    GUILD_ROLE_UPDATE(0, "GUILD_ROLE_UPDATE"),

    THREAD_CREATE(0, "THREAD_CREATE"),
    THREAD_DELETE(0, "THREAD_DELETE"),
    THREAD_UPDATE(0, "THREAD_UPDATE"),

    INTERACTION_CREATE(0, "INTERACTION_CREATE"),
    READY(0, "READY"),

    HEARTBEAT(1),
    HEARTBEAT_INTERVAL(10),
    HEARTBEAT_CONFIRM(11),
    IDENTIFY(2),


    PRESENCE_UPDATE(3),


    ;


    public int opCode;
    public int serial;
    public String name; // TODO: change name later

    PacketHeaders(int _opCode, int _serial, String _name) {
        opCode = _opCode;
        name = _name;
        serial = _serial;
    }

    PacketHeaders(int _opCode) { this(_opCode, -1, ""); }
    PacketHeaders(int _opCode, String _name) { this(_opCode, -1, _name); }
    PacketHeaders(int _opCode, int _serial) { this(_opCode, _serial, ""); }
}