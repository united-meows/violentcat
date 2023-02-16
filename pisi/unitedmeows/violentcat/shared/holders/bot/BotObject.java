package pisi.unitedmeows.violentcat.shared.holders.bot;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.yystal.clazz.Holder;
import pisi.unitedmeows.yystal.clazz.HookClass;

public class BotObject  {
    private DiscordBot owner;

    public BotObject bind(DiscordBot _owner) {
        owner = _owner;
        return this;
    }

    public DiscordBot owner() {
        return owner;
    }
}
