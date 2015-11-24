package com.mygdx.desertcommander;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


public class MainGame extends ApplicationAdapter{
	SpriteBatch batch;
	boolean GameStarted;
	MainLevel MAINLEVEL;

	@Override
	public void create () {
		GameStarted = false;
		batch = new SpriteBatch();

		MAINLEVEL = new MainLevel();
		MAINLEVEL.generateGame();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1.0f, 0.843137f, 0.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		MAINLEVEL.draw(batch);

	}

	@Override
	public void resume() {
		MAINLEVEL.paused = false;
	}

	@Override
	public void pause() {
		MAINLEVEL.paused = true;
	}






}
