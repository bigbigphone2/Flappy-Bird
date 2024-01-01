package com.mygdx.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gameStateManager;

    public State(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    public abstract void handleInput();
    public abstract void update(float dt);

    /**
     * Batch update to the GPU, and let it render the screen.
     * @param spriteBatch batch graphic information
     */
    public abstract void render(SpriteBatch spriteBatch, BitmapFont font);

    /**
     * Avoid memory leak
     */
    public abstract void dispose();
}
