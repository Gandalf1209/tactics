package com.gandalf1209.client;

import com.gandalf1209.game.MainGame;
import com.gandalf1209.yge2.util.Util;

public class Login {

	public static void start() {
		String username = Util.getInput("Enter Name:");
		Client.setInfo(username);
		new MainGame().init();
	}
	
}