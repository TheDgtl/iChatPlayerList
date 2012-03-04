package com.worldcretornica.ichatplayerlist;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLoginListener implements Listener {
	public static iChatPlayerList plugin;
	
	public PlayerLoginListener(iChatPlayerList instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		addPlayerToList(player);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		addPlayerToList(player);
	}
	
	
	public void addPlayerToList(Player player) {
		plugin.ichat.info.addPlayer(player);
		String prefix = plugin.ichat.info.getKey(player, "prefix");
		if(prefix != null && prefix.lastIndexOf("&") != -1) {
			int lastcolor = prefix.lastIndexOf("&");
			
			String coloredname = "&" + prefix.charAt(lastcolor + 1) + player.getName();
			String tabname = coloredname;
			
			if(plugin.ShowInTAB) {
				tabname = checkOP(tabname, player);
				tabname = plugin.ichat.API.addColor(tabname);
				if (tabname.length()>16) {
					player.setPlayerListName(tabname.substring(0, 14) + "..");
				} else {
					player.setPlayerListName(tabname);
				}
			}
			if(plugin.ShowInDisplayName) {
				coloredname = checkOP(coloredname, player);
				coloredname = plugin.ichat.API.addColor(coloredname);
				player.setDisplayName(coloredname);
			}
		}
	}
	
	public String checkOP(String name, Player player) {
		if(plugin.ShowStatusOP) {
			if(player.isOp()) {
				name = plugin.OPSymbol + name;
				
				if(plugin.ShowBothStatus) {
					name = checkCreative(name, player);
				}
			} else {
				name = checkCreative(name, player);
			}
			
			return name;
		} else {
			return checkCreative(name, player);
		}
	}
	
	public String checkCreative(String name, Player player) {
		if(plugin.ShowStatusCreative) {
			if(player.getGameMode() == GameMode.CREATIVE) {
				name = plugin.CreativeSymbol + name;
			}
			
			return name;
		} else {
			return name;
		}
	}
	
}
