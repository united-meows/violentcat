package pisi.unitedmeows.violentcat.shared.holders.shared.channel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.holders.shared.embed.Embed;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.Jsons;
import pisi.unitedmeows.yystal.hook.YString;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TextChannel extends Channel  {

    @Expose @SerializedName("last_message_id") private String lastMessageId;
    @Expose private String topic;
    @Expose @SerializedName("last_pin_timestamp") private String last_pin_time;
    @Expose @SerializedName("rate_limit_per_user") private int rateLimitPerUser;
    @Expose private boolean nsfw;


    @Deprecated
    public Action<Message> reply(String messageId, String content) {
        if (owner().isBot())
            return botInstance().reply(id, messageId, new Message(content));
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, Message message) {
        if (owner().isBot())
             return botInstance().reply(id, messageId, message);
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, Message message, Embed... embeds) {
        if (owner().isBot())
            return botInstance().send(id, message.reference(messageId), Arrays.stream(embeds).iterator());
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, String content, Embed... embeds) {
        if (owner().isBot())
           return botInstance().send(id, new Message(content).reference(messageId), Arrays.stream(embeds).iterator());
        return null;
    }

    @Deprecated
    public Action<Message> reply(String messageId, Embed... embeds) {
        if (owner().isBot())
            return botInstance().send(id, new Message("").reference(messageId), Arrays.stream(embeds).iterator());
        return null;
    }

    // todo
    public Action<Message> send(Message message) {
        if (owner().isBot())
            return botInstance().send(id, message);
        return null;
    }

    public Action<Message> send(String message) {
        return botInstance().send(id, message);
    }

    public Action<Message> send(Embed... embeds) {
        if (owner().isBot())
            return botInstance().send(YString.EMPTY_R, embeds);
        return null;
    }

    public Action<Message> send(String content, List<Embed> embeds) {
        if (owner().isBot())
            return botInstance().send(id, content, embeds);
        return null;
    }

    public Action<Message> send(List<Embed> embeds) {
        if (owner().isBot())
            return botInstance().send(id, embeds);
        return null;
    }

    public Action<Message> send(Message message, Iterator<Embed> iterator) {
        if (owner().isBot())
            return botInstance().send(id, message, iterator);
        return null;
    }

    public Action<Message> send(String content, Iterator<Embed> iterator) {
        if (owner().isBot())
            return botInstance().send(id, content, iterator);
        return null;
    }


    public Action<Message> send(String content, Embed... embeds) {
        return send(content, Arrays.stream(embeds).iterator());
    }

    public String id() {
        return id();
    }

    public String lastMessageId() {
        return lastMessageId;
    }

    public String name() {
        return name;
    }

    public int position() {
        return position;
    }

    public int flags() {
        return flags;
    }

    public String topic() {
        return topic;
    }

    public String last_pin_time() {
        return last_pin_time;
    }

    public int rateLimitPerUser() {
        return rateLimitPerUser;
    }

    public boolean isNsfw() {
        return nsfw;
    }
}
