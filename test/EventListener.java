package test;

import pisi.unitedmeows.eventapi.event.listener.Listener;
import pisi.unitedmeows.violentcat.bot.events.MessageEvent;

public class EventListener {

    public Listener<MessageEvent> messageListener = new Listener<>(event -> {
        if (!event.message().author().isBot())
            event.message().reply("test");
    });








}
