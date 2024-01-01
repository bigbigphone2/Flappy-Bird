package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBird;

public class MenuState extends State{
    private final Texture background;
    private final Texture playBtn;
    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
//        camera.setToOrtho(false, FlappyBird.WIDTH/ 2, FlappyBird.HEIGHT/ 2);
        background = new Texture("bg.png");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()){
            gameStateManager.set(new PlayState(gameStateManager));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch spriteBatch, BitmapFont font) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        spriteBatch.draw(playBtn, ((FlappyBird.WIDTH/ 2) - ((float) playBtn.getWidth()/2)), ((FlappyBird.HEIGHT/ 2) - ((float) playBtn.getHeight()/2)));
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        System.out.println("Menu State Disposed");
    }
}
