package pisi.unitedmeows.violentcat.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;
import pisi.unitedmeows.violentcat.shared.holders.shared.etc.FileAttachment;

import java.util.ArrayList;
import java.util.List;

public class WebhookMessage {

    protected WebhookMessage() {}

    protected String content;
    protected String username;
    protected String avatarUrl;
    protected boolean tts;
    protected List<Embed> embeds = new ArrayList<>();
    protected List<FileAttachment> files = new ArrayList<>();

    /* add attachments and files */

    public static WebhookMessage create() {
        return new WebhookMessage();
    }

    @Override
    public String toString() {
        JsonObject json = new JsonObject();
        json.addProperty("content", content);
        if (username != null) json.addProperty("username", username);
        if (avatarUrl != null) json.addProperty("avatar_url", avatarUrl);
        if (tts) json.addProperty("tts", true);

        if (!embeds.isEmpty()) {
            JsonArray embedArray = new JsonArray();
            for (Embed embed : embeds) {
                embedArray.add(embed.toObject());
            }
            json.add("embeds", embedArray);
        }

        if (!files.isEmpty()) {
            JsonArray fileArray = new JsonArray();
            int i = 0;
            for (FileAttachment attachment : files) {
                fileArray.add(attachment.toString(i++));
            }
            json.add("attachments", fileArray);
        }

        return Jsons.BUILDER.toJson(json);
    }

    public List<Embed> embeds() { return embeds; }

    public List<FileAttachment> files() { return files; }

    public WebhookMessage addFile(FileAttachment attachment) {
        files.add(attachment);
        return this;
    }

    public WebhookMessage addEmbed(Embed embed) {
        embeds.add(embed);
        return this;
    }

    public String content() {
        return content;
    }

    public WebhookMessage content(String _content) {
        content = _content;
        return this;
    }

    public String username() {
        return username;
    }

    public WebhookMessage username(String _username) {
        username = _username;
        return this;
    }

    public String avatarUrl() {
        return avatarUrl;
    }

    public WebhookMessage avatarUrl(String _avatarUrl) {
        avatarUrl = _avatarUrl;
        return this;
    }

    public boolean isTts() {
        return tts;
    }

    public WebhookMessage tts(boolean _tts) {
        tts = _tts;
        return this;
    }
}
