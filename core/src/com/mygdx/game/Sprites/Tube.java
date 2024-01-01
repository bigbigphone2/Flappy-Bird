package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topTube, bottomTube;
    private Vector3 posTopTube, posBottomTube;
    //For collision
    private Rectangle boundsTop, boundsBottom;
    private Random random;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector3(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING, 0);
        posBottomTube = new Vector3(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight(), 0);

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector3 getPosTopTube() {
        return posTopTube;
    }

    public Vector3 getPosBottomTube() {
        return posBottomTube;
    }

    public void reposition(float x){
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING, 0);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight(), 0);

        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public void dispose(){
        topTube.dispose();
        bottomTube.dispose();
    }
}
