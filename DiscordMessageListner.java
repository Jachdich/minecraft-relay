package com.cospox.relay;

import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordMessageListner extends ListenerAdapter {
	private JavaPlugin plugin;
	public DiscordMessageListner(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if (!e.getAuthor().equals(Main.bot.getSelfUser()) && e.getChannel().getId().equals("638858434539946004")) {
			String message = e.getMessage().getContentRaw();
			String playerName = e.getMember().getEffectiveName();
			plugin.getServer().broadcastMessage("<" + playerName + "> " + message);
			if (message.contains("69") || message.contains("420")) {
				plugin.getServer().broadcastMessage("<" + playerName + "> Nice");
	    	}
		}
	}
}
