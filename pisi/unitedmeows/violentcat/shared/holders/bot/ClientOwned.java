package pisi.unitedmeows.violentcat.shared.holders.bot;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.DiscordClient;

public class ClientOwned {

    private DiscordClient owner;

    public ClientOwned bind(DiscordBot _owner) {
        owner = _owner;
        return this;
    }

    public DiscordClient owner() {
        return owner;
    }


    public DiscordBot botInstance() {
        return (DiscordBot) owner;
    }
}
