package com.procrastinators.sphere.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.procrastinators.sphere.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Sphere";
		config.width = 1080;
		config.height = 1920;
		new LwjglApplication(new MyGame(), config);
	}
}
