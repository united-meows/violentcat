package pisi.unitedmeows.violentcat.shared.holders.shared.embed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmbedMedia {

    @Expose
    private String url;

    @Expose
    @SerializedName("proxy_url")
    private String proxyUrl;

    @Expose
    private int width;

    @Expose
    private int height;

    public EmbedMedia width(int _width) {
        width = _width;
        return this;
    }

    public EmbedMedia height(int _height) {
        height = _height;
        return this;
    }

    public EmbedMedia proxyUrl(String _proxyUrl) {
        proxyUrl = _proxyUrl;
        return this;
    }

    public EmbedMedia url(String _url) {
        url = _url;
        return this;
    }

}
