package pisi.unitedmeows.violentcat.bot.gateway;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.bot.events.MessageEvent;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.packet.NetworkHandler;
import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VGuildCreatePacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VHeartbeatConfirmPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VHeartbeatPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.VMessageCreatePacket;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterProcessor;

public class BotNetworkHandler extends NetworkHandler {

    private DiscordBot discordBot;

    public BotNetworkHandler(DiscordBot _discordBot) {
        super(BotNetworkHandler.class);
        discordBot = _discordBot;
    }

    @RegisterProcessor(PacketHeaders.GUILD_CREATE)
    public void messageProcessor(VGuildCreatePacket packet) {
        packet.guild().bind(discordBot);
        discordBot.guilds().add(packet.guild());
        System.out.println(packet.guild());
    }

    @RegisterProcessor(PacketHeaders.MESSAGE_CREATE)
    public void messageProcessor(VMessageCreatePacket packet) {
        final Message message = packet.message();

       message.bind(discordBot);
        discordBot.eventSystem().fire(new MessageEvent(message));
    }

    @RegisterProcessor(PacketHeaders.HEARTBEAT_INTERVAL)
    public void heartbeatProcessor(VHeartbeatPacket packet) {
        discordBot.gateway().setupHeartbeat((int) packet.heartbeatInterval());
        discordBot.gateway().beatNext();
    }

    @RegisterProcessor(PacketHeaders.HEARTBEAT_CONFIRM)
    public void heartbeatConfirm(VHeartbeatConfirmPacket packet) {
            discordBot.gateway().sequence(packet.sequence());
            discordBot.gateway().beatNext();
    }
}
