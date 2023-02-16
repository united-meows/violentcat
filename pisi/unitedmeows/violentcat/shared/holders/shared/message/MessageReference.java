package pisi.unitedmeows.violentcat.shared.holders.shared.message;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageReference {

    @Expose @SerializedName("message_id") private String messageId;
    @Expose @SerializedName("channel_id") private String channelId;
    @Expose @SerializedName("fail_if_not_exists") private boolean failOnError = false;

    public String messageId() {
        return messageId;
    }

    public String channelId() {
        return channelId;
    }

    public boolean isFailOnError() {
        return failOnError;
    }

    public MessageReference messageId(String _messageId) {
        messageId = _messageId;
        return this;
    }

    public MessageReference channelId(String _channelId) {
        channelId = _channelId;
        return this;
    }

    public MessageReference failOnError(boolean _failOnError) {
        failOnError = _failOnError;
        return this;
    }
}
