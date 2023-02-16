package pisi.unitedmeows.violentcat.shared.action;

import pisi.unitedmeows.violentcat.shared.stamp.BotSpecific;
import pisi.unitedmeows.violentcat.shared.stamp.Shared;

public enum Ratelimits {
    @Shared
    GUILD,

    @Shared
    MESSAGE_SENT,


    @Shared
    USER,

    @BotSpecific
    APPLICATION,

    @Shared
    WEBHOOK,

    @Shared
    GLOBAL,

    @Shared
    OTHER
}
