package pisi.unitedmeows.violentcat.shared.packet;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pisi.unitedmeows.violentcat.shared.ClientType;
import pisi.unitedmeows.violentcat.shared.packet.impl.server.*;
import pisi.unitedmeows.violentcat.shared.stamp.RegisterPacket;
import pisi.unitedmeows.violentcat.utils.Jsons;
import pisi.unitedmeows.yystal.utils.Pair;

import java.util.*;

public class VPacketSerializer {

    private static final JsonParser parser = new JsonParser();
    private static final Map<Integer, List<VPacketCapsule>> botPackets = new HashMap<>();

    static {
        registerPacket(ClientType.BOT, VHeartbeatPacket.class);
        registerPacket(ClientType.BOT, VHeartbeatConfirmPacket.class);
        registerPacket(ClientType.BOT, VMessageCreatePacket.class);
        registerPacket(ClientType.BOT, VChannelCreatePacket.class);
        registerPacket(ClientType.BOT, VChannelDeletePacket.class);
        registerPacket(ClientType.BOT, VGuildUpdatePacket.class);
        registerPacket(ClientType.BOT, VGuildRoleCreatePacket.class);
        registerPacket(ClientType.BOT, VGuildRoleDeletePacket.class);
        registerPacket(ClientType.BOT, VGuildRoleUpdatePacket.class);
        registerPacket(ClientType.BOT, VThreadCreatePacket.class);
        registerPacket(ClientType.BOT, VThreadDeletePacket.class);
        registerPacket(ClientType.BOT, VGuildCreatePacket.class);
        registerPacket(ClientType.BOT, VInteractionCreatePacket.class);
    }

    static void registerPacket(ClientType _type, Class<? extends VPacket> _clazz) {
        final RegisterPacket registerPacket = _clazz.getAnnotation(RegisterPacket.class);
        switch (_type) {
            case BOT:
                List<VPacketCapsule> list = botPackets.getOrDefault(registerPacket.value().opCode, null);
                if (list != null)
                    list.add(new VPacketCapsule(_clazz, registerPacket.value()));
                else {
                    list = new ArrayList<>(1);
                    list.add(new VPacketCapsule(_clazz, registerPacket.value()));
                    botPackets.put(registerPacket.value().opCode, list);
                }
                break;
            case SELF:
                break;
        }
    }

    public static Pair<PacketHeaders, VPacket> serialize(String _input, ClientType _type) {
        switch (_type) {
            case BOT: {
                JsonObject data = parser.parse(_input).getAsJsonObject();

                /*
                    {"t":"READY","s":1,"op":0,"d":{"v":9,"user_settings":{},"user":{"verified":true,"username":"violentcat","mfa_enabled":true,"id":"931180407699959878","flags":0,"email":null,"display_name":null,"discriminator":"7561","bot":true,"avatar":"fadf85bcc3a0336aa19cf877ee68e963"},"session_type":"normal","session_id":"a531fd9f258e2f1ba2b8b1af583be0aa","resume_gateway_url":"wss://gateway-us-east1-d.discord.gg","relationships":[],"private_channels":[],"presences":[],"guilds":[{"unavailable":true,"id":"931282703477784690"}],"guild_join_requests":[],"geo_ordered_rtc_regions":["bucharest","milan","russia","frankfurt","st-pete"],"application":{"id":"931180407699959878","flags":8953856},"_trace":["[\"gateway-prd-us-east1-d-63kj\",{\"micros\":160742,\"calls\":[\"id_created\",{\"micros\":2343,\"calls\":[]},\"session_lookup_time\",{\"micros\":1650,\"calls\":[]},\"session_lookup_finished\",{\"micros\":10,\"calls\":[]},\"discord-sessions-blue-prd-2-144\",{\"micros\":156337,\"calls\":[\"start_session\",{\"micros\":87441,\"calls\":[\"discord-api-5c9db8558b-2vb2w\",{\"micros\":77897,\"calls\":[\"get_user\",{\"micros\":22326},\"get_guilds\",{\"micros\":11111},\"send_scheduled_deletion_message\",{\"micros\":16},\"guild_join_requests\",{\"micros\":220},\"authorized_ip_coro\",{\"micros\":15}]}]},\"starting_guild_connect\",{\"micros\":63,\"calls\":[]},\"presence_started\",{\"micros\":23434,\"calls\":[]},\"guilds_started\",{\"micros\":84,\"calls\":[]},\"guilds_connect\",{\"micros\":1,\"calls\":[]},\"presence_connect\",{\"micros\":45267,\"calls\":[]},\"connect_finished\",{\"micros\":45271,\"calls\":[]},\"build_ready\",{\"micros\":16,\"calls\":[]},\"clean_ready\",{\"micros\":1,\"calls\":[]},\"optimize_ready\",{\"micros\":0,\"calls\":[]},\"split_ready\",{\"micros\":26,\"calls\":[]}]}]}]"]}}
                    {"t":"GUILD_CREATE","s":2,"op":0,"d":{"description":null,"embedded_activities":[],"max_members":500000,"splash":null,"application_command_counts":{"3":5,"2":1,"1":22},"public_updates_channel_id":"932026330244055051","region":"deprecated","member_count":4,"explicit_content_filter":2,"joined_at":"2022-01-24T23:54:20.988000+00:00","mfa_level":0,"guild_hashes":{"version":1,"roles":{"omitted":false,"hash":"1/w3Dw"},"metadata":{"omitted":false,"hash":"/o0o1g"},"channels":{"omitted":false,"hash":"B8cUCQ"}},"stickers":[],"id":"931282703477784690","afk_channel_id":null,"icon":"03e0812fb83c88c200cc46e43b879756","premium_progress_bar_enabled":false,"roles":[{"version":0,"unicode_emoji":null,"tags":{},"position":0,"permissions":"1071698529857","name":"@everyone","mentionable":false,"managed":false,"id":"931282703477784690","icon":null,"hoist":false,"flags":0,"color":0},{"version":1675860599504,"unicode_emoji":null,"tags":{},"position":5,"permissions":"1071698660937","name":"Maintainer","mentionable":true,"managed":false,"id":"931623721905586257","icon":null,"hoist":true,"flags":0,"color":10181046},{"version":1675860599497,"unicode_emoji":null,"tags":{"bot_id":"931180407699959878"},"position":4,"permissions":"8","name":"violentcat","mentionable":false,"managed":true,"id":"935321658942705715","icon":null,"hoist":false,"flags":0,"color":0},{"version":1675860599487,"unicode_emoji":null,"tags":{"bot_id":"559426966151757824"},"position":3,"permissions":"536895488","name":"Not Quite Nitro","mentionable":false,"managed":true,"id":"1005224714265956455","icon":null,"hoist":false,"flags":0,"color":0},{"version":1675860599756,"unicode_emoji":null,"tags":{},"position":1,"permissions":"0","name":"naber","mentionable":false,"managed":false,"id":"1072861844542328854","icon":null,"hoist":false,"flags":0,"color":0}],"stage_instances":[],"premium_subscription_count":0,"lazy":true,"members":[{"user":{"username":"violentcat","public_flags":0,"id":"931180407699959878","display_name":null,"discriminator":"7561","bot":true,"avatar_decoration":null,"avatar":"fadf85bcc3a0336aa19cf877ee68e963"},"roles":["931623721905586257","935321658942705715"],"premium_since":null,"pending":false,"nick":null,"mute":false,"joined_at":"2022-01-24T23:54:20.988000+00:00","flags":0,"deaf":false,"communication_disabled_until":null,"avatar":null}],"system_channel_id":"931282704014659676","nsfw_level":0,"large":false,"verification_level":1,"threads":[],"channels":[{"version":0,"type":4,"position":0,"permission_overwrites":[],"name":"violent","id":"931282704014659674","flags":0},{"version":0,"type":4,"position":0,"permission_overwrites":[],"name":"Voice Channels","id":"931282704014659675","flags":0},{"version":1675861541056,"type":0,"topic":"topiç testi 2","rate_limit_per_user":0,"position":0,"permission_overwrites":[],"parent_id":"931282704014659674","nsfw":true,"name":"general","last_pin_timestamp":"2022-01-22T10:58:40+00:00","last_message_id":"1073336355234533386","id":"931282704014659676","flags":0},{"version":0,"type":0,"topic":null,"rate_limit_per_user":0,"position":2,"permission_overwrites":[{"type":0,"id":"931282703477784690","deny":"515396528193","allow":"1024"}],"parent_id":"931282704014659674","name":"broadcast","last_message_id":"1051935617438715934","id":"931659507023609866","flags":0},{"version":0,"type":0,"topic":null,"rate_limit_per_user":0,"position":0,"permission_overwrites":[{"type":0,"id":"931282703477784690","deny":"2048","allow":"0"}],"name":"rules","last_message_id":"932261775473279046","id":"932026330244055050","flags":0},{"version":0,"type":0,"topic":null,"rate_limit_per_user":0,"position":0,"permission_overwrites":[{"type":0,"id":"931282703477784690","deny":"1024","allow":"0"}],"name":"moderator-only","last_message_id":"1052833988919689226","id":"932026330244055051","flags":0},{"version":0,"type":5,"topic":"topppic","rate_limit_per_user":0,"position":0,"permission_overwrites":[{"type":0,"id":"931282703477784690","deny":"2048","allow":"0"}],"parent_id":"931282704014659674","name":"news-test","last_message_id":"932029106386784277","id":"932026438721343498","flags":0}],"features":["APPLICATION_COMMAND_PERMISSIONS_V2","NEWS","COMMUNITY"],"vanity_url_code":null,"home_header":null,"premium_tier":0,"max_stage_video_channel_users":0,"voice_states":[],"system_channel_flags":0,"unavailable":false,"presences":[],"guild_scheduled_events":[],"nsfw":false,"rules_channel_id":"932026330244055050","default_message_notifications":1,"safety_alerts_channel_id":null,"hub_type":null,"max_video_channel_users":25,"owner_id":"554371566553792555","application_id":null,"latest_onboarding_question_id":null,"preferred_locale":"en-US","emojis":[{"version":1675808595690,"roles":[],"require_colons":true,"name":"deneme","managed":false,"id":"1072643718626156625","available":true,"animated":false}],"banner":null,"name":"violentcat #reborn","afk_timeout":300,"discovery_splash":null}}
                */

                final int op = Jsons.getInt(data.get("op"));
                final int s = Jsons.getInt(data.get("s"));
                final String type = Jsons.getString(data.get("t"));

                List<VPacketCapsule> capsuleList = botPackets.getOrDefault(op, null);
                if (capsuleList != null) {

                    Optional<VPacketCapsule> capsule = capsuleList
                        .stream().
                            filter(x -> (x.header.name.isEmpty() || x.header.name.equals(type)) && (x.header.serial == -1 || x.header.serial == s))
                            .findFirst();


                    if (capsule.isPresent()) {
                        JsonElement element = data.get("d");
                        VPacket packet = decode(capsule.get().type, element);
                        return new Pair<PacketHeaders, VPacket>(capsule.get().header, packet);
                    }
                }

            }
            break;
            case SELF:
                break;
        }
        return null;
    }

    private static VPacket decode(Class<? extends VPacket> type, JsonElement data) {
        VPacket packet;
        try {
            packet = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        packet.decode(data);
        return packet;
    }

    public static class VPacketCapsule {

        private PacketHeaders header;
        private Class<? extends VPacket> type;
        public VPacketCapsule(Class<? extends VPacket> _type, PacketHeaders _header) {
            type = _type;
            header = _header;
        }

        public Class<? extends VPacket> type() {
            return type;
        }

        public PacketHeaders header() {
            return header;
        }

    }
}