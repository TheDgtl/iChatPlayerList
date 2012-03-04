package com.worldcretornica.ichatplayerlist;

import net.TheDgtl.iChat.iChat;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class iChatPlayerList extends JavaPlugin {
	public iChat ichat;
	private FileConfiguration newConfig;
	private Server server;
	private PluginManager pm;
	
	public boolean ShowStatusOP;
	public boolean ShowStatusCreative;
	public boolean ShowInTAB;
	public boolean ShowInDisplayName;
	public boolean ShowBothStatus;
	public String OPSymbol;
	public String CreativeSymbol;

	@Override
	public void onEnable() {
		this.server = getServer();
		this.pm = server.getPluginManager();
		this.ichat = (iChat)this.getServer().getPluginManager().getPlugin("iChat");
		
		loadConfig();
		
		pm.registerEvents(new PlayerLoginListener(this), this);
	}
	
	private void loadConfig()
	{
		reloadConfig();
		newConfig = getConfig();
		newConfig.options().copyDefaults(true);
		
		ShowStatusOP = newConfig.getBoolean("ShowStatusOP");
		ShowStatusCreative = newConfig.getBoolean("ShowStatusCreative");
		ShowInTAB = newConfig.getBoolean("ShowInTAB");
		ShowInDisplayName = newConfig.getBoolean("ShowInDisplayName");
		ShowBothStatus = newConfig.getBoolean("ShowBothStatus");
		OPSymbol = newConfig.getString("OPSymbol");
		CreativeSymbol = newConfig.getString("CreativeSymbol");
		
		saveConfig();
	}

}
