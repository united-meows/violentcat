package pisi.unitedmeows.violentcat.shared.holders.shared.message.embed;

import com.google.gson.annotations.Expose;

public class EmbedField {

    @Expose protected String name;
    @Expose protected String value;
    @Expose protected boolean inline;




    public EmbedField name(String _name) {
        name = _name;
        return this;
    }

    public EmbedField value(String _value) {
        value = _value;
        return this;
    }

    public EmbedField inline(boolean _inline) {
        inline = _inline;
        return this;
    }

    public String name() {
        return name;
    }

    public String value() {
        return value;
    }

    public boolean isInline() {
        return inline;
    }
}
