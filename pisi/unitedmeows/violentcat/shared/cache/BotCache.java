package pisi.unitedmeows.violentcat.shared.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import pisi.unitedmeows.violentcat.shared.holders.bot.ApplicationInfo;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.GuildPreview;

import java.time.Duration;
import java.util.List;


/*
    TODO: IBotCache should contain all the methods
    create a class with name EmptyCache and don't store any values there
 */
public class BotCache /* implements IBotCache */{

    private boolean ignoreNext;
    private Cache<String, List<Channel>> GUILD_CHANNELS;
    private Cache<String, Guild> GUILDS;
    private Cache<String, GuildPreview> GUILD_PREVIEWS;
    private SingleCache<ApplicationInfo> APPLICATION_INFO;

    public BotCache ignoreNext() {
        ignoreNext = true;
        return this;
    }

    public boolean shouldIgnore() {
        if (ignoreNext) {
            ignoreNext = false;
            return true;
        }
        return false;
    }

    public Guild guild(String id) {
        return GUILDS.getIfPresent(id);
    }

    public ApplicationInfo applicationInfo() {
        return APPLICATION_INFO.shouldUpdate() ? null : APPLICATION_INFO.value();
    }

    public void storeApplicationInfo(ApplicationInfo applicationInfo) {
        APPLICATION_INFO.value(applicationInfo);
    }

    public void storeGuild(Guild guild) {
        GUILDS.put(guild.id(), guild);
    }

    public List<Channel> guildChannels(String guild) {
        return GUILD_CHANNELS.getIfPresent(guild);
    }

    public void storeChannels(String guild, List<Channel> channels) {
        GUILD_CHANNELS.put(guild, channels);
    }

    public GuildPreview guildPreview(String id) {
        return GUILD_PREVIEWS.getIfPresent(id);
    }

    public void storeGuildPreview(GuildPreview preview) {
        GUILD_PREVIEWS.put(preview.id(), preview);
    }


    public BotCache() {
        GUILD_CHANNELS = Caffeine.newBuilder()
                .maximumSize(1_000)
                .expireAfterWrite(Duration.ofMinutes(5))
                .build();

        GUILDS = Caffeine.newBuilder()
                .maximumSize(200)
                .expireAfterWrite(Duration.ofMinutes(5))
                .build();

        GUILD_PREVIEWS = Caffeine.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(Duration.ofMinutes(1))
                .build();

    }
}
