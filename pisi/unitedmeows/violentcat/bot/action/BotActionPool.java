package pisi.unitedmeows.violentcat.bot.action;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.action.*;

public class BotActionPool extends ActionPool<Ratelimits> {

    private DiscordBot bot;

    public BotActionPool(DiscordBot _bot) {
        bot = _bot;
        registerRateListener(Ratelimits.APPLICATION, new RateListener(this, Ratelimits.APPLICATION, 10, 1000));
        registerRateListener(Ratelimits.GUILD, new VictimRateListener(this, Ratelimits.GUILD, 5, 1000));
        registerRateListener(Ratelimits.MESSAGE_SENT, new VictimRateListener(this, Ratelimits.MESSAGE_SENT, 5, 5000));
    }

}
