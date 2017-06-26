package com.procrastinators.sphere;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.procrastinators.helpers.AssetLoader;
import com.procrastinators.screens.GameScreen;

public class MyGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("MyGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		AssetLoader.dispose();
		super.dispose();
	}
}
