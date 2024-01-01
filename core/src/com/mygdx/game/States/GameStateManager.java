package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    public static GameStateManager instance;
    private Stack<State> states;

    private GameStateManager(){
        states = new Stack<>();
    }

    public static GameStateManager getInstance(){
        if (instance == null){
            instance = new GameStateManager();
        }
        return instance;
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }

    /*
     * Pop and instantly push a new state
     */
    public void set(State state){
        pop();
        push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch spriteBatch, BitmapFont font){
        states.peek().render(spriteBatch, font);
    }

}
