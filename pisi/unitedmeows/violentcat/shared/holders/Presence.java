package pisi.unitedmeows.violentcat.shared.holders;

// TODO: Don't know if same applies to selfbots
public class Presence {

    private boolean afk;
    private Availability availability;
    private int type;
    private String statusMessage;
    private String special;

    public Presence(Availability _availability, Status status, String _statusMessage, String _special) {
        availability = _availability;
        statusMessage = _statusMessage;
        type = status.id();
        special = _special;
    }

    public Presence(Availability _availability, Status status, String _statusMessage) {
        this(_availability, status, _statusMessage, "");
    }

    public Presence afk(boolean _afk) {
        afk = afk;
        return this;
    }

    public boolean afk() { return afk; }

    public Availability availability() {
        return availability;
    }

    public String statusMessage() {
        return statusMessage;
    }

    public String special() {
        return special;
    }

    public int type() {
        return type;
    }
}
