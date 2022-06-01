package dev._2lstudios.flamecord.configuration;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;

public class MessagesConfiguration {
	private final Logger logger;
	private final Map<String, String> messages = new HashMap<>();

	public MessagesConfiguration(final Logger logger, final ConfigurationProvider configurationProvider) {
		this.logger = logger;

		try {
			final String fileName = "./messages_flamecord.yml";
			final File configurationFile = new File(fileName);
			final Configuration configuration;
			final boolean configurationExists = configurationFile.exists();

			if (!configurationExists) {
				configuration = new Configuration();
			} else {
				configuration = configurationProvider.load(configurationFile);
			}

			// BungeeCord
			setIfUnexistant("alert", "&8[&4Alert&8]&r ", configuration);
			setIfUnexistant("already_connected", "&cYou are already connected to this server!", configuration);
			setIfUnexistant("already_connected_proxy", "&cYou are already connected to this proxy!", configuration);
			setIfUnexistant("already_connecting", "&cAlready connecting to this server!", configuration);
			setIfUnexistant("command_list", "&a[{0}] &e({1}): &r{2}", configuration);
			setIfUnexistant("connect_kick", "&cKicked whilst connecting to {0}: {1}", configuration);
			setIfUnexistant("current_server", "&6You are currently connected to {0}.", configuration);
			setIfUnexistant("fallback_kick",
					"&cCould not connect to a default or fallback server, please try again later: {0}", configuration);
			setIfUnexistant("fallback_lobby",
					"&cCould not connect to target server, you have been moved to a fallback server.", configuration);
			setIfUnexistant("timeout",
					"Server not reachable (timeout). Offline? Incorrectly configured address/port/firewall?", configuration);
			setIfUnexistant("lost_connection", "[Proxy] Lost connection to server.", configuration);
			setIfUnexistant("mojang_fail", "Error occurred while contacting login servers, are they down?",
					configuration);
			setIfUnexistant("no_permission", "&cYou do not have permission to execute this command!", configuration);
			setIfUnexistant("no_server", "&cThe specified server does not exist.", configuration);
			setIfUnexistant("no_server_permission", "&cYou don't have permission to access this server.",
					configuration);
			setIfUnexistant("outdated_client", "Outdated client! Please use {0}", configuration);
			setIfUnexistant("outdated_server", "Outdated server! I'm still on {0}", configuration);
			setIfUnexistant("proxy_full", "Server is full!", configuration);
			setIfUnexistant("restart", "[Proxy] Proxy restarting.", configuration);
			setIfUnexistant("server_list", "&6You may connect to the following servers at this time: ", configuration);
			setIfUnexistant("server_went_down",
					"&cThe server you were previously on went down, you have been connected to a fallback server",
					configuration);
			setIfUnexistant("total_players", "Total players online: {0}", configuration);
			setIfUnexistant("name_invalid", "Username contains invalid characters.", configuration);
			setIfUnexistant("ping_cannot_connect", "&c[Bungee] Can't connect to server.", configuration);
			setIfUnexistant("offline_mode_player", "Not authenticated with Minecraft.net", configuration);
			setIfUnexistant("message_needed", "&cYou must supply a message.", configuration);
			setIfUnexistant("error_occurred_player",
					"&cAn error occurred while parsing your message. (Hover for details)", configuration);
			setIfUnexistant("error_occurred_console", "&cAn error occurred while parsing your message: {0}",
					configuration);
			setIfUnexistant("click_to_connect", "Click to connect to the server", configuration);
			setIfUnexistant("username_needed", "&cPlease follow this command by a user name.", configuration);
			setIfUnexistant("user_not_online", "&cThat user is not online.", configuration);
			setIfUnexistant("user_online_at", "&a{0} &ris online at {1}", configuration);
			setIfUnexistant("send_cmd_usage",
					"&cNot enough arguments, usage: /send <server|player|all|current> <target>", configuration);
			setIfUnexistant("player_only", "&cOnly in game players can use this command", configuration);
			setIfUnexistant("you_got_summoned", "&6Summoned to {0} by {1}", configuration);
			setIfUnexistant("command_perms_groups", "&6You have the following groups: {0}", configuration);
			setIfUnexistant("command_perms_permission", "&9- {0}", configuration);
			setIfUnexistant("command_ip", "&9IP of {0} is {1}", configuration);
			setIfUnexistant("illegal_chat_characters", "&cIllegal characters in chat ({0})", configuration);

			// FlameCord
			setIfUnexistant("firewall_added", "&e{0}&c had been firewalled from the proxy!", configuration);
			setIfUnexistant("firewall_blocked", "&e{0}&c is firewalled from the proxy, request blocked!",
					configuration);
			setIfUnexistant("firewall_info",
					"&aThere are&b {0} &aaddresses firewalled!\n&aThe firewall will clear in &b{1} &aseconds!",
					configuration);
			setIfUnexistant("firewall_cleared", "&b{0}&a addresses had been automatically removed from the firewall!",
					configuration);
			setIfUnexistant("flamecord_reload", "&aAll files had been successfully reloaded!", configuration);
			setIfUnexistant("flamecord_help",
					"&aInfinityCord &f{0} &aImplement &fFlameCord &7(Patch's) &r\n&a by&b LinsaFTW&a &&b Sammwy&r\n&e /flamecord reload&7 >&b Reloads FlameCord files!\n&e /flamecord firewall&7 >&b Shows information about the Firewall!\n&e /flamecord help&7 >&b Shows this message!",
					configuration);
			setIfUnexistant("flamecord_nopermission", "&cYou don't have permission to do this!", configuration);

			if (!configurationExists) {
				configurationProvider.save(configuration, configurationFile);
			}

			for (final String key : configuration.getKeys()) {
				final Object value = configuration.get(key);

				if (value instanceof String) {
					this.messages.put(key, ChatColor.translateAlternateColorCodes('&', (String) value));
				}
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public String getTranslation(final String name, final Object... args) {
		if (!messages.containsKey(name)) {
			logger.warning("[FlameCord] Tried to get translation '" + name
					+ "' from messages.yml file but wasn't found. Please try resetting this file or report to a developer.");
		}

		return MessageFormat.format(messages.getOrDefault(name, "<translation '" + name + "' missing>"), args);
	}

	private void setIfUnexistant(final String arg1, final Object arg2, final Configuration configuration) {
		if (!configuration.contains(arg1)) {
			configuration.set(arg1, arg2);
		}
	}
}