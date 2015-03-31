package com.gandalf1209.game;

import com.gandalf1209.client.Client;
import com.gandalf1209.yge2.engine.Application;
import com.gandalf1209.yge2.engine.Game;
import com.gandalf1209.yge2.graphics.Display;
import com.gandalf1209.yge2.graphics.GraphicsX;

public class MainGame implements Game {

	private Display d;
	public int STATE = 0;
	
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
		}
	}

	@Override
	public void update() {
		Client.sendData("/upd/~" + Client.username);
	}
	
}