package nl.gjosse.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class OtherLocation implements Serializable {
	

	private static final long serialVersionUID = -7307347569046907362L;
	String world;
	double X;
	double Y;
	double Z;
	String name;
	
	String filepath = "plugins/SpawnZones/data/"+name+".dat";
	
	public static List<String> players = new ArrayList<String>();
	
	
	public OtherLocation(String world, double x, double y, double z, String name)
	{
		this.world = world;
		this.X = x;
		this.Y = y;
		this.Z = z;
		this.name = name;
	}
	
	public OtherLocation(Location loc, String name)
	{
		this.world = loc.getWorld().getName();
		this.X = loc.getBlockX();
		this.Y = loc.getBlockY();
		this.Z = loc.getBlockZ();
		this.name = "unknown";
	}
	
	
	public void addName(String name)
	{
	this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addPlayer(Player player)
	{
		players.add(player.getDisplayName());
		System.out.println("Added Player");
		System.out.println("Size: "+players.size());
	}
	public void addPlayerName(String player) 
	{
		players.add(player);
		System.out.println("Added Player");
		System.out.println("Size: "+players.size());
	}
	
	public List<String> getPlayers()
	{
		return players;
	}

	@Override
	public String toString()
	{
		return world+","+this.X+","+this.Y+","+this.Z;	
	}

	public Location getLocation()
	{
		Location loc = new Location(this.getWorld(), this.X, this.Y, this.Z);
		return loc;
	}
	
	public World getWorld() {
		return Bukkit.getWorld(world);
	}


	public double getX() {
		return X;
	}


	public double getY() {
		return Y;
	}


	public double getZ() {
		return Z;
	}

	public void removePlayers() 
	{
		players.clear();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(X);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((world == null) ? 0 : world.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OtherLocation other = (OtherLocation) obj;
		if (Double.doubleToLongBits(X) != Double.doubleToLongBits(other.X))
			return false;
		if (Double.doubleToLongBits(Y) != Double.doubleToLongBits(other.Y))
			return false;
		if (Double.doubleToLongBits(Z) != Double.doubleToLongBits(other.Z))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (world == null) {
			if (other.world != null)
				return false;
		} else if (!world.equals(other.world))
			return false;
		return true;
	}

	



}
