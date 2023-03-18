package pisi.unitedmeows.violentcat.bot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import pisi.unitedmeows.eventapi.system.BasicEventSystem;
import pisi.unitedmeows.violentcat.bot.action.BotActionPool;
import pisi.unitedmeows.violentcat.bot.gateway.BotGateway;
import pisi.unitedmeows.violentcat.bot.gateway.BotNetworkHandler;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.DiscordClient;
import pisi.unitedmeows.violentcat.shared.action.Action;
import pisi.unitedmeows.violentcat.shared.action.ActionPool;
import pisi.unitedmeows.violentcat.shared.action.Ratelimits;
import pisi.unitedmeows.violentcat.shared.cache.BotCache;
import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Presence;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.holders.bot.ApplicationInfo;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.ChannelBuilder;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.DetailedGuild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;
import pisi.unitedmeows.violentcat.shared.holders.shared.embed.Embed;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.packet.impl.client.VIdentifyPacket;
import pisi.unitedmeows.violentcat.shared.packet.impl.client.VPresenceUpdatePacket;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.violentcat.utils.GsonWrap;
import pisi.unitedmeows.violentcat.utils.Jsons;
import pisi.unitedmeows.yystal.hook.YString;
import pisi.unitedmeows.yystal.web.client.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DiscordBot extends DiscordClient {

    protected BotGateway gateway;
    protected BotNetworkHandler networkHandler;
    protected YWebClient client;
    protected YSimpleWebClient simpleWebClient;
    protected BotCache cache;
    protected BasicEventSystem eventSystem;

    protected BotActionPool actionPool;
    protected List<DetailedGuild> guilds = new ArrayList<>();

    DiscordBot(DiscordBotBuilder _builder) {
        super(ClientType.BOT);

        eventSystem = new BasicEventSystem();
        cache = new BotCache();

        try {
            client = YWebClientBuilder.build();
            client.header("Authorization", String.format("Bot %s", _builder.token()));
            client.header("User-Agent", "violentcat");
            // todo temp

            simpleWebClient = new YSimpleWebClient();
            simpleWebClient.setUserAgent("violentcat");
            simpleWebClient.header("Authorization", String.format("Bot %s", _builder.token()));

            actionPool = new BotActionPool(this);
            actionPool.start();

            networkHandler = new BotNetworkHandler(this);
            gateway = new BotGateway(this, new URI("wss://gateway.discord.gg/?v=10&encoding=json"));


            /* queue the identify packet */
            gateway().prepare(
                    new VIdentifyPacket(_builder.token(), _builder.intents(), false,
                            "violentcat", _builder.onMobile() ? "Discord Android" : "violentcat", "violentcat"))
                    .queue();

        } catch (Exception ex) { }
    }

    public Action<Channel> createChannel(String guild, ChannelBuilder channelBuilder) {
        return actionPool.rateListener(Ratelimits.GUILD).queue(guild, () -> {
               final String result = simpleWebClient.postRequest(DiscordHelper.route("/guilds/%s/channels", guild),
                       channelBuilder.toString());
               if (result == null)
                   return null;
               JsonObject json = (JsonObject) Jsons.parser.parse(result);
               ChannelType type = ChannelType.from(json.get("type").getAsInt());
               final Channel channel = GsonWrap.parse(type.type(), json).result();
               channel.bind(this);
               channel.__setup(json);
               return channel;
        });
    }

    /* will be removed in next 2-3 updates */
    /* use send(String, Message) and set referenceMessage method */
    @Deprecated
    public Action<Message> reply(String channel, String messageId, String content) {
        return reply(channel, messageId, new Message(content));
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, Message message) {
        return send(channel, message.reference(messageId));
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, Message message, Embed... embeds) {
        return send(channel, message.reference(messageId), Arrays.stream(embeds).iterator());
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, String content, Embed... embeds) {
        return send(channel, new Message(content).reference(messageId), Arrays.stream(embeds).iterator());
    }

    @Deprecated
    public Action<Message> reply(String channel, String messageId, Embed... embeds) {
        return send(channel, new Message("").reference(messageId), Arrays.stream(embeds).iterator());
    }

    // todo
    public Action<Message> send(String channel, Message message) {
        return actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            final String link = DiscordHelper.route("/channels/%s/messages", channel);
            final String result = simpleWebClient.postRequest(link, Jsons.BUILDER.toJson(message));
            System.out.println(Jsons.BUILDER.toJson(message));
            final GsonWrap wrap = GsonWrap.parse(Message.class, result);
            final Message returnMsg = wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    /* change boolean to Message */
    public Action<Message> send(String channel, String message) {
        return actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            final String link = DiscordHelper.route("/channels/%s/messages", channel);
            final String result = simpleWebClient.postRequest(link, String.format("{  \"content\": \"%s\", \"tts\": false}", message));

            final GsonWrap wrap = GsonWrap.parse(Message.class, result);
            final Message returnMsg = wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    public Action<Message> send(String channel, Embed... embeds) {
        return send(channel, YString.EMPTY_R, embeds);
    }

    public Action<Message> send(String channel, String content, List<Embed> embeds) {
        return send(channel, content, embeds.iterator());
    }

    public Action<Message> send(String channel, List<Embed> embeds) {
        return send(channel, YString.EMPTY_R, embeds);
    }

    public Action<Message> send(String channel, Message message, Iterator<Embed> iterator) {
        return actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            while (iterator.hasNext()) {
                Embed embed = iterator.next();
                message.embeds().add(embed);
            }

            final String data = Jsons.BUILDER.toJson(message);
            final String result = simpleWebClient.postRequest(DiscordHelper.route("/channels/%s/messages", channel), data);
            final GsonWrap wrap = GsonWrap.parse(Message.class, result);
            final Message returnMsg = wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }

    public Action<Message> send(String channel, String content, Iterator<Embed> iterator) {
        return actionPool.rateListener(Ratelimits.MESSAGE_SENT).queue(channel, () -> {
            StringBuilder embedRaw = new StringBuilder();
            while (iterator.hasNext()) {
                Embed embed = iterator.next();
                embedRaw.append(embed.toString());
                if (iterator.hasNext())
                    embedRaw.append(",");
            }

            final String data = String.format(
                    "{  \"content\": \"%s\", \"tts\": false, \"embeds\": [%s]}",
                    content,
                    embedRaw.toString());
            final String result = simpleWebClient.postRequest(DiscordHelper.route("/channels/%s/messages", channel), data);
            final GsonWrap wrap = GsonWrap.parse(Message.class, result);
            final Message returnMsg = wrap.result();
            returnMsg.bind(this);
            return returnMsg;
        });
    }


    public Action<Message> send(String channel, String content, Embed... embeds) {
        return send(channel, content, Arrays.stream(embeds).iterator());
    }

    public Action<Boolean> deleteMessage(String channelId, String messageId) {
        return actionPool.rateListener(Ratelimits.CHANNEL).queue(channelId, () -> {
            final YWebResponse response = client.delete(DiscordHelper.route(String.format("/channels/%s/messages/%s", channelId, messageId)))
                    .run();
            return true;
        });
    }

    public Action<ApplicationInfo> applicationInfo() {
        if (!cache.shouldIgnore()) {
            ApplicationInfo applicationInfo = cache.applicationInfo();
            if (applicationInfo != null)
                return Action.completedTask(applicationInfo);
        }

        return actionPool.rateListener(Ratelimits.APPLICATION).queue(() -> {
            final YWebResponse response = client.get("https://discord.com/api/v10/oauth2/applications/@me").run();
            final GsonWrap wrap = GsonWrap.parse(ApplicationInfo.class, response.asString());
            final ApplicationInfo applicationInfo = wrap.result();
            applicationInfo.__setup(wrap.data());
            cache.storeApplicationInfo(applicationInfo);
            return applicationInfo;
        });
    }

    public Action<Guild> guild(String id) {
        if (!cache.shouldIgnore()) {
            Guild guild = cache.guild(id);
            if (guild != null)
                return Action.completedTask(guild);

        }
        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {
            final YWebResponse response = client.get(String.format("https://discord.com/api/v10/guilds/%s?with_counts=true", id)).run();
            final GsonWrap wrap = GsonWrap.parse(Guild.class, response.asString());
            final Guild guild = wrap.result();
            guild.__setup(wrap.data());
            guild.bind(this);

            /* store to cache */
            cache.storeGuild(guild);
            return guild;
        });
    }

    public Action<GuildPreview> guildPreview(String id) {
        if (!cache.shouldIgnore()) {
            GuildPreview preview = cache.guildPreview(id);
            if (preview != null)
                return Action.completedTask(preview);
        }

        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {
            final YWebResponse response = client.get(DiscordHelper.route("guilds/%s", id)).run();
            final GsonWrap wrap = GsonWrap.parse(GuildPreview.class, response.asString());
            final GuildPreview preview = wrap.result();
            preview.__setup(wrap.data());
            preview.bind(this);

            /* store to cache */
            cache.storeGuildPreview(preview);
            return preview;
        });
    }

    public Action<List<Channel>> guildChannels(String id) {
        if (!cache.shouldIgnore()) {
            List<Channel> channels = cache.guildChannels(id);
            if (channels != null)
                return Action.completedTask(channels);
        }

        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {

            final YWebResponse response = client.get(String.format("https://discord.com/api/v10/guilds/%s/channels", id)).run();
            final String result = response.asString();
            System.out.println("CHANNEL RESULT");
            System.out.println(result);
            JsonArray array = (JsonArray) Jsons.parser.parse(result);
            List<Channel> channels = new ArrayList<>(array.size());

            for (int i = 0; i < array.size(); i++) {
                JsonObject guildObject = array.get(i).getAsJsonObject();
                int type = Jsons.getInt(guildObject.get("type"));
                ChannelType channelType = ChannelType.from(type);
                if (channelType.type() != null) {
                    try {
                        final Channel channel = GsonWrap.parse(channelType.type(), guildObject).result();
                        channel.bind(this);
                        channel.__setup(guildObject);
                        channels.add(channel);
                    } catch (Exception e) {
                    }

                }
            }

            /* store to cache */
            cache.storeChannels(id, channels);
            return channels;
        });
    }

    public Action<Boolean> channel(String id) {
        return actionPool.rateListener(Ratelimits.GUILD).queue(id, () -> {
            final YWebResponse response = client.get(String.format("https://discord.com/api/v10/channels/%s", id)).run();
            System.out.println(response.asString());
            return true;
        });
    }

    public Action<Boolean> deleteChannel(String channelId) {
        return actionPool.rateListener(Ratelimits.GUILD).queue(channelId, () -> {
            final YWebResponse response = client.delete(String.format("https://discord.com/api/v10/channels/%s", channelId)).run();
            System.out.println(response.asString());
            return true;
        });
    }

    public void login() {
        gateway.connect();
    }

    public void presence(Availability availability, Status status, String message, String url) {
        gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, status, message, url))).queue();
    }

    public void presence(Availability availability, Status status, String message) {
        gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, status, message))).queue();
    }

    public void presence(Availability availability) {
        gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, Status.NOTHING, ""))).queue();
    }
    public void presence(Availability availability, Status status) {
        gateway().prepare(new VPresenceUpdatePacket(new Presence(availability, status, ""))).queue();
    }

    public List<DetailedGuild> guilds() {
        return guilds;
    }

    public BotNetworkHandler networkHandler() {
        return networkHandler;
    }

    public BotGateway gateway() {
        return gateway;
    }

    public DiscordBot ignoreCache() {
        cache.ignoreNext();
        return this;
    }

    @Override
    public YSimpleWebClient simpleWebClient() {
        return simpleWebClient;
    }

    @Override
    public YWebClient webClient() {
        return client;
    }

    @Override
    public ActionPool<Ratelimits> actionPool() {
        return actionPool;
    }

    @Override
    public BasicEventSystem eventSystem() {
        return eventSystem;
    }
}