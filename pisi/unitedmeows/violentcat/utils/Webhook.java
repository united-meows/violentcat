package pisi.unitedmeows.violentcat.utils;

import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.shared.DiscordClient;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.holders.shared.etc.FileAttachment;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.User;
import pisi.unitedmeows.yystal.utils.Pair;
import pisi.unitedmeows.yystal.web.client.YSimpleWebClient;

import java.io.File;
import java.io.IOException;

public class Webhook {

    protected String id;
    protected String url;
    protected String guildId;
    protected String channelId;
    protected String name;
    protected String avatar;
    protected String applicationId;
    protected boolean initialized = false;

    public static Webhook of(final String _url) {
        return new Webhook(_url);
    }

    protected Webhook(final String _url) {
        url = _url;

        if (!url.startsWith("https"))
            url = "https://" + url;
    }

    public Webhook info() {
        final Pair<String, String> info = webhookInfo(url);
        YSimpleWebClient simpleWebClient = new YSimpleWebClient();
        final String result = simpleWebClient.downloadString(DiscordHelper.route("/webhooks/%s/%s", info.item1(), info.item2()));
        final JsonObject json = Jsons.parser.parse(result).getAsJsonObject();
        
        id = Jsons.getString(json.get("name"));
        name = Jsons.getString(json.get("name"));
        avatar = Jsons.getString(json.get("avatar"));
        channelId = Jsons.getString(json.get("channel_id"));
        guildId = Jsons.getString(json.get("guild_id"));
        applicationId = Jsons.getString(json.get("application_id"));

        initialized = true;
        return this;
    }


    public String id() {
        if (!initialized) info();
        return id;
    }

    public String url() {
        if (!initialized) info();
        return url;
    }

    public String guildId() {
        if (!initialized) info();
        return guildId;
    }

    public String channelId() {
        if (!initialized) info();
        return channelId;
    }

    public String name() {
        if (!initialized) info();
        return name;
    }

    public String avatar() {
        if (!initialized) info();
        return avatar;
    }

    public String applicationId() {
        if (!initialized) info();
        return applicationId;
    }

    public Action<Boolean> delete(DiscordClient client) {
        if (client.isBot())
            return ((DiscordBot)client).deleteWebhook(guildId(), id());
        // todo: add selfbot
        return null;
    }

    public void send(WebhookMessage message) {
        try {

            final HttpPost post = new HttpPost(url);

            post.setHeader("Accept", "application/json");

            if (!message.files().isEmpty()) {
                final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                //builder.addTextBody("payload_json", message.toString(), ContentType.APPLICATION_JSON);
                int count = 0;
                for (FileAttachment attachment : message.files()) {
                    builder.addBinaryBody("file[" + count++ + "]", attachment.file(), ContentType.DEFAULT_BINARY, attachment.name());
                }

                post.setEntity(builder.build());
            } else {
                post.setHeader("Content-type", "application/json");
                post.setEntity(new StringEntity(message.toString()));
            }

            System.out.println(message.toString());

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                CloseableHttpResponse response =  client.execute(post);
                final int responseCode = response.getStatusLine().getStatusCode();;
                System.out.println(responseCode);
            }

        } catch (IOException ex) {

        }
    }

    public void send(String content) {
        send(WebhookMessage.create().content(content));
    }

    private static Pair<String, String> webhookInfo(String _url) {
        final String[] split = _url.split("\\/");
        return new Pair<>(split[split.length - 2], split[split.length - 1]);
    }




}
