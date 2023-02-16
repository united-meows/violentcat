package pisi.unitedmeows.violentcat.shared.holders;

import pisi.unitedmeows.violentcat.shared.stamp.RISKY;

public enum Availability {
    ONLINE("online"),
    IDLE("idle"),
    DO_NOT_DISTURB("dnd"),
    INVISIBLE("invisible"),
    OFFLINE("offline");
    private String code;

    Availability(String _code) {
        code = _code;
    }

    public String code() {
        return code;
    }
}