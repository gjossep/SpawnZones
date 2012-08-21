package nl.gjosse.listener;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import nl.gjosse.SpawnZones;
import nl.gjosse.classes.OtherLocation;
import nl.gjosse.classes.SpawnZone;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class LoginLis implements Listener {
	
	SpawnZones plugin;
	
	public LoginLis(SpawnZones spawnZones) {
		this.plugin = spawnZones;
	}

	@EventHandler
	public void Login(PlayerLoginEvent e)
	{
		final Player player = e.getPlayer();
		String playerGroup = SpawnZone.getPlayerGroup(player);
		if(playerGroup==null)
		{
			final OtherLocation lessJoined = SpawnZone.getLessJoined();
			if(lessJoined!=null)
			{
				Runnable whenDone = new Runnable() {
					
					@Override
					public void run() {
						player.teleport(lessJoined.getLocation());
						player.sendMessage(ChatColor.GOLD+"[SpawnZone] You have been added to the group: "+lessJoined.getName());
						player.sendMessage(ChatColor.GOLD+"[SpawnZone] From now on you will always spawn here.");
						player.sendMessage(ChatColor.GOLD+"[SpawnZone] For more infomation about this do /spawnzone help");						
					}
				};
				lessJoined.addPlayer(player);
				final ScheduledThreadPoolExecutor exe = new ScheduledThreadPoolExecutor(1);
				exe.schedule(whenDone, 1, TimeUnit.SECONDS);
				
			}
		}
	}
	@EventHandler
	public void Death(PlayerRespawnEvent e)
	{
	Player player = e.getPlayer();	
	String playerGroup = SpawnZone.getPlayerGroup(player);
	System.out.println(playerGroup);
	Location togo = null;
	if(togo!=null)
	{
	e.setRespawnLocation(SpawnZone.getGroup(playerGroup).getLocation());
	}
	else
	{
	player.sendMessage(ChatColor.RED+"Something went wrong with your spawn group. Talk to a admin about it. Teleporting you to the world spawn.");
	e.setRespawnLocation(player.getWorld().getSpawnLocation());
	}
	
	}

}
