package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.GameStateManager;
import com.mygdx.game.States.MenuState;

public class FlappyBird extends ApplicationAdapter {
	public static final float WIDTH = 400;
	public static final float HEIGHT = 800;
	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	private BitmapFont font;
	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		initialMusic();
		gameStateManager = GameStateManager.getInstance();
		gameStateManager.push(new MenuState(gameStateManager));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch, font);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
		font.dispose();
	}

	private void initialMusic(){
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.02F);
		music.play();
	}

	private void initialFont(){
		font.setColor(0,0,0,1);
//		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
	}
}
