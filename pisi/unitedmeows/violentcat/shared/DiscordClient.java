package pisi.unitedmeows.violentcat.shared;

import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;
import pisi.unitedmeows.yystal.web.client.YWebClient;

public abstract class DiscordClient {

    private ClientType type;

    public DiscordClient(ClientType _type) {
        type = _type;
    }

    public abstract YSimpleWebClient simpleWebClient();
    public abstract YWebClient webClient();
    public abstract ActionPool<?> actionPool();

    public boolean isBot() {
        return type == ClientType.BOT;
    }

    public boolean isSelf() {
        return type == ClientType.SELF;
    }

    public ClientType type() {
        return type;
    }
}
