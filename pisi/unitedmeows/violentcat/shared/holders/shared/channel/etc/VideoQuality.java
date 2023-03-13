package pisi.unitedmeows.violentcat.shared.holders.shared.channel.etc;

public enum VideoQuality {
    AUTO(1),
    FULL(2);

    int id;
    VideoQuality(int _id) {
        id = _id;
    }

    public int id() {
        return id;
    }
}
