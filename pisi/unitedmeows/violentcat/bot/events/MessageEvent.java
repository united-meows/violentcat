package pisi.unitedmeows.violentcat.bot.events;

import pisi.unitedmeows.eventapi.event.Event;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;

public class MessageEvent extends Event {

    protected Message message;

    /*@OnlyLibCalls*/
    public MessageEvent(Message _message) {
        message = _message;
    }

    public Message message() {
        return message;
    }
}
