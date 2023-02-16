package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

    @Expose private String url;
    @Expose private int size;
    @Expose @SerializedName("proxy_url") private String proxyUrl;
    @Expose private String id;
    @Expose @SerializedName("filename") private String fileName;
    @Expose @SerializedName("content_type") private String contentType;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Attachment{");
        sb.append("url='").append(url).append('\'');
        sb.append(", size=").append(size);
        sb.append(", proxyUrl='").append(proxyUrl).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", contentType='").append(contentType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
