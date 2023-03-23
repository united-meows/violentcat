package pisi.unitedmeows.violentcat.bot.gateway;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.packet.*;
import pisi.unitedmeows.violentcat.shared.packet.impl.client.VHeartbeatAckPacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.utils.Pair;
import pisi.unitedmeows.yystal.utils.Stopwatch;
import pisi.unitedmeows.yystal.utils.kThread;

import java.net.URI;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class BotGateway extends WebSocketClient implements IGateway {

    /**
     * The thread of bot gateway
     */
    private Thread writeThread;
    private DiscordBot discordBot;

    private String beatSequence;
    private int nextHeartbeat;
    private Stopwatch heartbeatWatcher;
    private int beatInterval;
    private int sequence;
    private boolean beated = true;


    public BotGateway(DiscordBot _discordBot, /* make this enum */URI _uri) {
        super(_uri);
        discordBot = _discordBot;
        heartbeatWatcher = new Stopwatch();
    }

    private Queue<VPacketData> sendQueue = new ArrayDeque<>();

    public VPacketData prepare(VPacket _vPacket) {
        final RegisterPacket registerPacket = _vPacket.getClass().getAnnotation(RegisterPacket.class);
        JsonObject object = new JsonObject();

        object.addProperty("op", registerPacket.value().opCode); //çalışmadı
        JsonElement packetData = _vPacket.encode();
        object.add("d", packetData == null ? JsonNull.INSTANCE : packetData);
        String data = object.toString();

        return prepareRaw(data);
    }

    public VPacketData prepareRaw(String data) {
        return new VPacketData(data, this);
    }

    @Override
    public void onOpen(ServerHandshake _handshake) {
        writeThread = new Thread(this::_write);
        writeThread.start();

    }

    public void _write() {
        while (!isClosed()) {
            if (!sendQueue.isEmpty()) {
                    VPacketData data = sendQueue.poll();
                    send(data.packet());
                    data.mark();
            }
            if (!beated && heartbeatWatcher.isReached(nextHeartbeat)) {
                prepare(new VHeartbeatAckPacket(sequence)).queue();
                System.out.println("sent heartbeat ack " + sequence);
                beated = true;
            }
            kThread.sleep(YYStal.setting(YSettings.TCP_CLIENT_WRITE_DELAY));
        }
    }

    @Override
    public void onMessage(String _message) {
        System.out.println(_message);
        Pair<PacketHeaders, VPacket> packetPair = VPacketSerializer.serialize(_message, ClientType.BOT);
        if (packetPair != null) {
            discordBot.networkHandler().process(packetPair.item1(), packetPair.item2());
        }
    }

    @Override
    public void onClose(int _code, String _reason, boolean _remote) {
        System.out.println(_reason);
    }

    @Override
    public void onError(Exception e) {
            e.printStackTrace();
    }

    @Override
    public VPacketData queue(VPacketData data) {
        sendQueue.add(data);
        return data;
    }

    @Override
    public VPacketData await(VPacketData data) {
        queue(data);
        while (!data.sent()) {
            kThread.sleep(YYStal.setting(YSettings.TASK_AWAIT_DELAY));
        }
        return data;
    }

    public void setupHeartbeat(int _beatInterval) {
        beatInterval = _beatInterval;
    }

    public void beatNext() {
        nextHeartbeat = (int)(beatInterval * Math.max(0.7, new Random().nextFloat()));
        heartbeatWatcher.reset();
        beated = false;
    }

    public BotGateway sequence(int _sequence) {
        sequence = _sequence;
        return this;
    }
}
