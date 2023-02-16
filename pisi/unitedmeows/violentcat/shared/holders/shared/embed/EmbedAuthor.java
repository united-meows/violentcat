package pisi.unitedmeows.violentcat.shared.holders.shared.embed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmbedAuthor {

    @Expose
    private String name;
    @Expose
    private String url;

    @Expose
    @SerializedName("icon_url")
    private String iconUrl;

    @Expose
    @SerializedName("proxy_icon_url")
    private String proxyIconUrl;

    public EmbedAuthor name(String _name) {
        name = _name;
        return this;
    }

    public EmbedAuthor url(String _url) {
        url = _url;
        return this;
    }

    public EmbedAuthor iconUrl(String _iconUrl) {
        iconUrl = _iconUrl;
        return this;
    }

    public EmbedAuthor proxyIconUrl(String _proxyIconUrl) {
        proxyIconUrl = _proxyIconUrl;
        return this;
    }
}
