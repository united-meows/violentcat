package pisi.unitedmeows.violentcat.client.gateway.signal;

import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.client.DiscordClient;

public abstract class Signal {

	public static final int SIGNAL_HEARTBEAT_INTERVAL = 10;
	public static final int SIGNAL_HEARTBEAT_ACK = 1;
	public static final int SIGNAL_IDENTIFY = 2;
	public static final int SIGNAL_READY = 2;
	public static final int SIGNAL_PRESENCE = 3;
	public static final int SIGNAL_SEND_MESSAGE = 0;

	public abstract void read(DiscordClient client, JsonObject data);
	public abstract JsonObject write(DiscordClient client);
}
