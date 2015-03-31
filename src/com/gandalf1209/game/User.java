package com.gandalf1209.game;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name;
	
	public List<Player> players = new ArrayList<Player>();
	
	public User(String name) {
		this.name = name;
	}
	
	public void initPlayers(int it) {
		for (int i = 0; i < it; i++) {
			players.add(new Player(i, 15, (i * 60) + 15));
		}
	}
	
	public void updatePlayer(Player player) {
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			if (p.getID() == player.getID()) {
				players.set(i, player);
				break;
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
}