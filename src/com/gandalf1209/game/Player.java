package com.gandalf1209.game;

public class Player {

	private int id;
	private int x;
	private int y;
	
	public Player(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public static String playerToData(Player p) {
		String data = p.getID() + "&" + p.getX() + "&" + p.getY();
		return data;
	}
	
	public static Player dataToPlayer(String data) {
		String[] s = data.split("&");
		
		int id = Integer.parseInt(s[0]);
		int x = Integer.parseInt(s[1]);
		int y = Integer.parseInt(s[2]);
		return new Player(id, x, y);
	}
	
	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public int getID() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}