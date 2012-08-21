package nl.gjosse;

import nl.gjosse.classes.SpawnZone;
import nl.gjosse.listener.LoginLis;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnZones extends JavaPlugin {
	
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new LoginLis(this), this);
		getLogger().info("SpawnZones Enabled");
		
		SpawnZone.start();
	}
	
	public void onDisable()
	{
		getLogger().info("SpawnZones Disabled");
		SpawnZone.stop();
	}
	//Test Edit
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		if(commandLabel.equalsIgnoreCase("spawnzone")&&args.length==0)
		{
			sender.sendMessage(ChatColor.RED+"Wrong Command. Do /spawnzone help");
			String string = SpawnZone.getGroup(SpawnZone.getPlayerGroup((Player)sender)).toString();
			sender.sendMessage(string);
			System.out.println(string);
			Player i = (Player) sender;
			sender.sendMessage(i.getLocation().toString());
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("spawnzone")&&args[0].equalsIgnoreCase("tp"))
		{
			if(args.length==2)
			{
				if(sender instanceof Player)
				{
				Player player = (Player) sender;
				if(SpawnZone.tpLoc(args[1], player))
				{
					sender.sendMessage(ChatColor.GREEN+"Teleported to: "+args[1]);
				}
				else
				{
					sender.sendMessage(ChatColor.RED+"Cant find location name.");
				}
				}
				else
				{
					sender.sendMessage("You have to be a player to do this command!");
				}
			}
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("spawnzone")&&args[0].equalsIgnoreCase("remove"))
		{
			if(args.length==2)
			{
				if(SpawnZone.remove(args[1]))
				{
					sender.sendMessage(ChatColor.GREEN+"Removed: "+args[1]);
				}
				else
				{
					sender.sendMessage(ChatColor.RED+"Cant find location name.");
				}
			}
			else
			{
				sender.sendMessage(ChatColor.RED+"You did not provide a name for this location.");
			}
			return true;
		}
		
		if(commandLabel.equalsIgnoreCase("spawnzone")&&args[0].equalsIgnoreCase("set"))
			{
				if(args.length==2)
				{
					if(sender instanceof Player)
					{
						Player player = (Player) sender;
						SpawnZone.addLocation(args[1], player.getLocation());
						sender.sendMessage(ChatColor.GREEN+"Location set as: "+args[1]);
						
					}
					else
					{
						sender.sendMessage("You have to be a player to do this command!");
					}
				 }
				else
				{
					sender.sendMessage(ChatColor.RED+"You did not provide a name for this location.");
				}
				return true;
				}
		
		if(commandLabel.equalsIgnoreCase("spawnzone")&&args.length==1&&args[0].equalsIgnoreCase("list"))
				{
				SpawnZone.showZones(sender);	
				return true;
				}
		return false;
	}
	

}
