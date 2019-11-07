package com.cospox.relay;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
//import net.dv8tion.jda.api.entities.Activity;

public class Main extends JavaPlugin implements Listener {
	public static JDA bot;
    @Override
    public void onEnable() {
    	this.setupConfig();
    	try {
			bot = new JDABuilder("ID-here").addEventListeners(new DiscordMessageListner(this)).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
    	this.getServer().getPluginManager().registerEvents(this, this);
    }

    public void setupConfig() {
    	//this.getConfig().addDefault("world.lobbyspawn", new double[]{0.5, 4.0, 1000.5});
		//this.getConfig().addDefault("world.hubworld", "world_the_end");
		//this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
    	bot.shutdown();
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
    	String message = e.getMessage();
    	String playerName = e.getPlayer().getName();
    	this.sendDiscordMessage("<" + playerName + "> " + message);
    	if (message.contains("69") || message.contains("420")) {
    		this.sendDiscordMessage("<" + playerName + "> Nice");
    	}
    	Bukkit.getWorlds().get(0).getSeed();
    }

    private void sendDiscordMessage(String message) {
    	bot.getTextChannelById(638858434539946004L).sendMessage(message).queue();
    }

    @EventHandler
    public void onPlayerCraft(CraftItemEvent e){
        if(e.getInventory().getResult().getType().equals(Material.STONE_SHOVEL)){
            //e.setCancelled(true);
            //e.getWhoClicked().sendMessage(ChatColor.AQUA + "TheColossal " + ChatColor.DARK_GRAY + ">> " + ChatColor.RED + "You are not allowed to craft this item.");
        }
    }
}
