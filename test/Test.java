package test;

import pisi.unitedmeows.violentcat.bot.DiscordBot;
import pisi.unitedmeows.violentcat.bot.DiscordBotBuilder;
import pisi.unitedmeows.violentcat.shared.holders.Availability;
import pisi.unitedmeows.violentcat.shared.holders.Status;
import pisi.unitedmeows.violentcat.shared.holders.shared.channel.Channel;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.Message;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.component.ActionRow;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.component.Button;
import pisi.unitedmeows.violentcat.shared.holders.shared.message.embed.Embed;
import pisi.unitedmeows.violentcat.shared.holders.shared.guild.Guild;
import pisi.unitedmeows.violentcat.utils.Webhook;
import pisi.unitedmeows.violentcat.utils.WebhookMessage;
import pisi.unitedmeows.yystal.utils.kThread;

public class Test {



    public static void main(String[] args)  {
        /* create bot instance */
        DiscordBot discordBot = DiscordBotBuilder.create()
                .token("OTMxMTgwNDA3Njk5OTU5ODc4.GliaAu.nuziNCyqK1bdkeTvo4Che4vDSizujbm6YFm55o")
                .intents(65253)
                .onMobile(true)
                .build();

        discordBot.send("931282704014659676", "god is awakening");


       /* gateway login */
        discordBot.login();

        //discordBot.eventSystem().subscribeAll(new EventListener());

        /* .await makes the thread wait until the task has complete and returns the api's response */
        /* if you don't want to hang the thread just use .then(Supplier) method */
        final Guild guild = discordBot.guild("931282703477784690").await();

        discordBot.send("931282704014659676", Message.
                create()
                .content("Test Message")
                /* adding component */
                .add(ActionRow.create().add(Button.create("Click Me!", 1, "test_id")))
        );


        kThread.sleep(5000);
        
        /* bot's presence */
        discordBot.presence(Availability.ONLINE, Status.NOTHING /*,"github.com/united-meows/violentcat", "https://twitch.tv/slowcheetah"*/);
        kThread.sleep_till(() -> true);
    }


}