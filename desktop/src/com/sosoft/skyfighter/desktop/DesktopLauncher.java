package com.sosoft.skyfighter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sosoft.skyfighter.skyFighter;

import java.awt.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		config.width = 1280;
		config.height = 720;
		//config.fullscreen = true;
		new LwjglApplication(new skyFighter(), config);
	}
}