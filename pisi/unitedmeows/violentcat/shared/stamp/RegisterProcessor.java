package pisi.unitedmeows.violentcat.shared.stamp;

import pisi.unitedmeows.violentcat.shared.packet.PacketHeaders;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterProcessor {
    PacketHeaders value();
}
