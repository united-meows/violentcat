package pisi.unitedmeows.violentcat.shared.holders;

import pisi.unitedmeows.violentcat.shared.stamp.RISKY;

public enum Status {
    PLAYING(0),
    STREAMING(1),
    LISTENING(2),
    WATCHING(3),
    COMPETING(5),
    NOTHING(2173);

    private int id;
    Status(int _id) {
        id = _id;
    }

    public int id() {
        return id;
    }
}
