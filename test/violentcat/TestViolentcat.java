package test.violentcat;

import pisi.unitedmeows.violentcat.client.DiscordClient;
import pisi.unitedmeows.violentcat.client.selfclient.SelfClient;
import pisi.unitedmeows.violentcat.holders.guild.Guild;
import pisi.unitedmeows.violentcat.holders.Presence;
import pisi.unitedmeows.violentcat.holders.guild.GuildPreview;
import pisi.unitedmeows.violentcat.slashcmd.SlashCommandCreator;
import pisi.unitedmeows.violentcat.user.AccountType;
import pisi.unitedmeows.yystal.utils.kThread;

import java.util.Base64;

public class TestViolentcat {

	public static void main(String[] args) {
		String tokenDecode = "T1RNeE1UZ3dOREEzTmprNU9UVTVPRGM0LlllQXJWUS50LVU0Qmh3cWxaZ0l0M0dkSWdaaXBfUGJ3ZVE=";


		SelfClient selfClient = new SelfClient("NjkxODU0NzQxNjM3OTU1Nzc0.YiOUBQ.j51kUkIpAYPInPdkjqXJB5S-758");
		selfClient.login();

//		DiscordClient discordClient = new DiscordClient(new String(Base64.getDecoder().decode(tokenDecode))).login();
//		discordClient.setPresence(Presence.playing("hello world"));
//		discordClient.addListener(new TestListener());
//
//
//		discordClient.slashCommands();
		kThread.sleep(100000);
	}
}
