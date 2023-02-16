package pisi.unitedmeows.violentcat.shared.holders.shared.embed;

import com.google.gson.annotations.Expose;

public enum EmbedType {
    RICH("rich"),
    IMAGE("image"),
    VIDEO("video"),
    GIFV("gifv"),
    ARTICLE("article"),
    LINK("link");

    @Expose
    private String code;
    EmbedType(String _code) {
        code = _code;
    }

    public String code() {
        return code;
    }
}
