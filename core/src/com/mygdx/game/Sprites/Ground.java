package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Ground {
    private static final int GROUND_Y_OFFSET = -30;
    private final Texture ground;

    private Vector3 groundPos1, groundPos2;

    public Ground(float screenLeftEdgePosition){
        ground = new Texture("ground.png");
        groundPos1 = new Vector3(screenLeftEdgePosition, GROUND_Y_OFFSET, 0);
        groundPos2 = new Vector3((screenLeftEdgePosition + ground.getWidth()), GROUND_Y_OFFSET, 0);
    }

    public Texture getGround(){
        return ground;
    }

    public Vector3 getGroundPos1() {
        return groundPos1;
    }

    public Vector3 getGroundPos2() {
        return groundPos2;
    }

    public void reposition(int ground, int offset){
        switch (ground){
            case 1:
                groundPos1.add(offset, 0, 0);
                break;
            case 2:
                groundPos2.add(offset, 0, 0);
                break;
        }
    }

    public void dispose(){
        ground.dispose();
    }
}
