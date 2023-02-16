package pisi.unitedmeows.violentcat.shared.holders.shared.embed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Footer {

    @Expose
    private String text;

    @Expose
    @SerializedName("icon_url")
    private String iconUrl;

    @Expose
    @SerializedName("proxy_icon_url")
    private String proxyIconUrl;


    public Footer text(String _text) {
        text = _text;
        return this;
    }

    public Footer iconUrl(String _iconUrl) {
        iconUrl = _iconUrl;
        return this;
    }
}
