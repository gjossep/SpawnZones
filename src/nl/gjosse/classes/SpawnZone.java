package nl.gjosse.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnZone {

	public static List<OtherLocation> locations1 = new ArrayList<OtherLocation>();
	public static String filePath1 = "plugins/SpawnZones/spawnZone1.dat";
	
	
	
	@SuppressWarnings("unchecked")
	public static void start() {
		createFile();
		try {
		locations1 = (List<OtherLocation>) FileIO.load(filePath1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(OtherLocation loc: locations1)
		{
			loadfiles(loc);
		}
	}

	private static void createFile() {
		File dir = new File("plugins/SpawnZones");
		if(!dir.exists())
		{
			dir.mkdir();
		}
		
		File save = new File(filePath1);
		if(!save.exists())
		{
			try {
				save.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public static void stop() {
		
		for(OtherLocation loc : locations1)
		{
			saveFiles(loc);
		}
		try {
			FileIO.save(locations1, filePath1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public static void addLocation(String name, Location loc) 
	{
	OtherLocation spawnLoc = new OtherLocation(loc,name);
	spawnLoc.addName(name);
	locations1.add(spawnLoc);
	}

	public static void showZones(CommandSender sender) 
	{
	sender.sendMessage(ChatColor.GOLD+"--------List of Zones-------");
	boolean zone = false;
	for(OtherLocation i : locations1)
	{
		zone = true;
		sender.sendMessage(ChatColor.AQUA+"-"+ChatColor.WHITE+i.getName());
	}
	if(zone==false)
	{
		sender.sendMessage(ChatColor.RED+"No Zones.");
	}
	sender.sendMessage(ChatColor.GOLD+"--------List of Zones-------");

	}

	public static boolean tpLoc(String name, Player sender) {
		for(OtherLocation i : locations1)
		{
		if(i.getName().equalsIgnoreCase(name))
		{
		sender.teleport(i.getLocation());
		return true;	
		}
		}
		return false;
	}

	public static boolean remove(String string) {
		for(int i = 0;i<locations1.size(); i++)
		{
			Iterator<OtherLocation> it = locations1.iterator();
			while(it.hasNext())
			{
				OtherLocation next = it.next();
				if(next.getName().equalsIgnoreCase(string))
				{
				next.removePlayers();
				File playerlist = new File("plugins/SpawnZones/data/"+string+".dat");
				if(playerlist.exists())
				{
					playerlist.delete();
				}
				it.remove();
				return true;	
				}
				
			}
		}
		return false;
		
		
	}
	
	public static String getPlayerGroup(Player player)
	{
		String result = null;
		for(OtherLocation loc : locations1)
		{
			for(String i : loc.getPlayers())
			{
				if(player.getDisplayName().equals(i))
				{
					result = loc.getName();
				}
			}
			
		}
	return result;
	}

	public static OtherLocation getLessJoined() 
	{
		int lessSize = 999999999;
		OtherLocation otherloc = null;
		for(OtherLocation loc : locations1)
		{
			int size = loc.getPlayers().size();
			if(size<=lessSize)
			{
				lessSize=size;
				otherloc = loc;
			}
		}
		return otherloc;
		
		
	}

	public static OtherLocation getGroup(String group) 
	{
	OtherLocation re = null;
	for(OtherLocation i : locations1)
	{
		if(i.getName().equalsIgnoreCase(group))
		{
			re = i;
		}
	}
	return re;
	
	}
	
	private static void saveFiles(OtherLocation loc) {
		List<String> players = loc.getPlayers();
		String name = loc.getName();
		
		File test = new File("plugins/SpawnZones/data");
		if(!test.exists())
		{
			test.mkdir();
		}
		File data = new File("plugins/SpawnZones/data/"+name+".dat");
		if(!data.exists())
		{
			try {
				data.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			FileIO.save(players, "plugins/SpawnZones/data/"+name+".dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	private static void loadfiles(OtherLocation loc) {
		List<String> players = new ArrayList<String>();
		String name = loc.getName();
		
		File test = new File("plugins/SpawnZones/data");
		if(!test.exists())
		{
			test.mkdir();
		}
		File data = new File("plugins/SpawnZones/data/"+name+".dat");
		if(!data.exists())
		{
			try {
				data.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			players = (List<String>) FileIO.load("plugins/SpawnZones/data/"+name+".dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(String player : players)
		{
			loc.addPlayerName(player);
		}
		
		
	}
	

}
