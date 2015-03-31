package com.gandalf1209.game;

import java.util.ArrayList;
import java.util.List;

import com.gandalf1209.client.Client;
import com.gandalf1209.yge2.engine.Application;
import com.gandalf1209.yge2.engine.Game;
import com.gandalf1209.yge2.graphics.Display;
import com.gandalf1209.yge2.graphics.GraphicsX;

public class MainGame implements Game {

	private Display d;
	public int STATE = 0;
	
	public static List<Player> players = new ArrayList<Player>();
	
	public void init() {
		Application.start();
		d = new Display("Tactics - " + Application.VERSION, 800, 600, this);
		Texture.init();
		
		d.getWindow().setResizable(false);
		d.getWindow().setVisible(true);
		
		d.start(30);
	}

	@Override
	public void render(GraphicsX g) {
		if (STATE == 0) {
			g.setBGImage(Texture.get("TitleBG"));
			
			g.setColor(g.hex("#C43B42"));
			for (int i = 0; i < Client.user.players.size(); i++) {
				Player p = Client.user.players.get(i);
				g.fillRect(p.getX(), p.getY(), 50, 50);
			}
		}
	}

	@Override
	public void update() {
		Client.sendData("/upd/~" + Client.username);
	}
	
}