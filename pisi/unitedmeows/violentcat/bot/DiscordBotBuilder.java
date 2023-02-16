package pisi.unitedmeows.violentcat.bot;

import pisi.unitedmeows.violentcat.shared.stamp.NotImplemented;
import pisi.unitedmeows.violentcat.shared.stamp.RISKY;

public class DiscordBotBuilder {

    private int intents = 4609; /* TODO: DEFAULT */
    private String token;
    private boolean onMobile;

    @NotImplemented
    private boolean compression;

    protected DiscordBotBuilder() {}

    public static DiscordBotBuilder create() {
        return new DiscordBotBuilder();
    }

    public DiscordBot build() {
        return new DiscordBot(this);
    }

    String token() {
        return token;
    }

    public DiscordBotBuilder token(String _token) {
        token = _token;
        return this;
    }

    @RISKY
    public DiscordBotBuilder onMobile(boolean _onMobile) {
        onMobile = _onMobile;
        return this;
    }

    public DiscordBotBuilder intents(int _intents) {
        intents = _intents;
        return this;
    }

    public boolean onMobile() {
        return onMobile;
    }

    int intents() {
        return intents;
    }

}
