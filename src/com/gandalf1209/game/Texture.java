package com.gandalf1209.game;

import java.awt.image.BufferedImage;

import com.gandalf1209.yge2.graphics.GraphicsLoader;

public class Texture {

	private static GraphicsLoader gl;
	
	public static void init() {
		gl = new GraphicsLoader();
		gl.setDefaultLoadingDirectory("/textures/");
	}
	
	public static BufferedImage get(String name) {
		return gl.loadGraphic(name + ".png");
	}
	
}