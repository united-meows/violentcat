package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pisi.unitedmeows.violentcat.shared.holders.bot.BotObject;
import pisi.unitedmeows.violentcat.shared.holders.shared.user.BasicUser;
import pisi.unitedmeows.violentcat.shared.holders.shared.embed.Embed;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* todo for GuildMessage, extend this class and add guildId and don't forget to add getGuild() that returns Guild object */
public class Message extends BotObject {

    @Expose private String id;
    @Expose private int type;
    @Expose @SerializedName("channel_id") private String channelId;
    @Expose private Author author;
    @Expose private String content;
    @Expose private List<Attachment> attachments;

    @Expose private List<Embed> embeds = new ArrayList<>();
    @Expose private List<BasicUser> mentions;

    @Expose @SerializedName("mention_roles") private String[] mentionRoles;
    @Expose private boolean pinned;
    @Expose private boolean tts;
    @Expose @SerializedName("mention_everyone") private boolean mentionEveryone;
    @Expose private int flags;
    @Expose @SerializedName("message_reference") private MessageReference referenceMessage;

    @Deprecated
    @OnlyLibCalls
    public Message() {}
    public Message(String _content) {
        content = _content;
    }

    public static Message create(String content) {
        return new Message(content);
    }
    public static Message create() {
        return new Message();
    }

    /* components */

    public String id() {
        return id;
    }

    public int type() {
        return type;
    }

    public String channelId() {
        return channelId;
    }

    public Author author() {
        return author;
    }

    public List<Embed> embeds() {
        return embeds;
    }

    public List<BasicUser> mentions() {
        return mentions;
    }

    public String[] mentionRoles() {
        return mentionRoles;
    }

    public String content() {
        return content;
    }

    public boolean isPinned() {
        return pinned;
    }

    public boolean isTts() {
        return tts;
    }

    public boolean isMentionEveryone() {
        return mentionEveryone;
    }

    public int flags() {
        return flags;
    }

    public MessageReference referenceMessage() {
        return referenceMessage;
    }


    public Message type(int _type) {
        type = _type;
        return this;
    }

    public Message content(String _content) {
        content = _content;
        return this;
    }

    public Message embeds(List<Embed> _embeds) {
        embeds = _embeds;
        return this;
    }

    public Message tts(boolean _tts) {
        tts = _tts;
        return this;
    }

    public Message flags(int _flags) {
        flags = _flags;
        return this;
    }

    public Message referenceMessage(String messageId) {
        referenceMessage = new MessageReference();
        referenceMessage.messageId(messageId);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("id='").append(id).append('\'');
        sb.append(", type=").append(type);
        sb.append(", channelId='").append(channelId).append('\'');
        sb.append(", author=").append(author);
        sb.append(", content='").append(content).append('\'');
        sb.append(", attachments=").append(attachments);
        sb.append(", embeds=").append(embeds);
        sb.append(", mentions=").append(mentions);
        sb.append(", mentionRoles=").append(Arrays.toString(mentionRoles));
        sb.append(", pinned=").append(pinned);
        sb.append(", tts=").append(tts);
        sb.append(", mentionEveryone=").append(mentionEveryone);
        sb.append(", flags=").append(flags);
        sb.append(", referenceMessage=").append(referenceMessage);
        sb.append('}');
        return sb.toString();
    }
}
