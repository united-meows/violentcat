package pisi.unitedmeows.violentcat.bot;

import pisi.unitedmeows.violentcat.shared.stamp.NotImplemented;

public class DiscordBotBuilder {

    private int intents;
    private String token;

    @NotImplemented
    private boolean compression;

    protected DiscordBotBuilder() {}

    public static DiscordBotBuilder create() {
        return new DiscordBotBuilder();
    }

    String token() {
        final String temp = token;
        token = "";
        return temp;
    }

    int intents() {
        return intents;
    }

}
