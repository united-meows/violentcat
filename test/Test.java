package test;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.bot.DiscordBotBuilder;
import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.ChannelBuilder;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.TextChannel;
import pisi.unitedmeows.violentcat.shared.holders.shared.embed.Embed;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.ChannelType;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.MessageReference;
import pisi.unitedmeows.violentcat.utils.DiscordHelper;
import pisi.unitedmeows.yystal.YSettings;
import pisi.unitedmeows.yystal.YYStal;
import pisi.unitedmeows.yystal.parallel.Future;
import pisi.unitedmeows.yystal.utils.kThread;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception  {

        /* example embed message */
        Embed embed =
                Embed.rich()
                        .title("Hello world")
                        .description("This is a description")
                        .footer(a -> {
                            a.text("slowcheet4h");
                            a.iconUrl("https://cdn.discordapp.com/avatars/554371566553792555/3f45eb712a8c4a4e100aae3daf9363d3.png");
                        })
                        .color(0xcc0000);

        /* create bot instance */
        DiscordBot discordBot = DiscordBotBuilder.create()
                .token("TOKEN")
                .intents(65253)
                .onMobile(true)
                .build();

       /* gateway login */
        discordBot.login();

        discordBot.eventSystem().subscribeAll(new EventListener());


        /* .await makes the thread wait until the task has complete and returns the api's response */
        /* if you don't want to hang the thread just use .then(Supplier) method */

        final Guild guild = discordBot.guild("931282703477784690").await();

        for (Channel channel : guild.channels().await()) {
            channel.edit().name("kedi").end();
        }


        kThread.sleep(5000);
        
        /* bot's presence */
        discordBot.presence(Availability.ONLINE, Status.NOTHING /*,"github.com/united-meows/violentcat", "https://twitch.tv/slowcheetah"*/);
        kThread.sleep_till(() -> true);
    }


}