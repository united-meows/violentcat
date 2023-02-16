package pisi.unitedmeows.violentcat.shared.packet;

import pisi.unitedmeows.violentcat.bot.gateway.BotGateway;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class VPacketData {
    private String packet;
    private boolean sent = false;
    private Optional<Runnable> postTask = Optional.empty();
    private IGateway gateway;

    public VPacketData(String _packet, IGateway _gateway) {
        packet = _packet;
        gateway = _gateway;
    }

    public String packet() {
        return packet;
    }

    public boolean sent() {
        return sent;
    }

    public VPacketData then(Runnable _runnable) {
        postTask = Optional.of(_runnable);
        return this;
    }

    public VPacketData queue() {
        gateway.queue(this);
        return this;
    }

    public VPacketData await() {
        gateway.await(this);
        return this;
    }


    public VPacketData mark() {
        sent = true;
        post();
        return this;
    }

    public void post() {
        postTask.ifPresent(Runnable::run);
    }

}
