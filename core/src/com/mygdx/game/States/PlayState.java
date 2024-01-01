package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBird;
import com.mygdx.game.Sprites.Bird;
import com.mygdx.game.Sprites.Ground;
import com.mygdx.game.Sprites.Tube;



public class PlayState extends State{
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private Bird bird;
    private Array<Tube> tubes;
    private Ground ground;

    private final Texture background;
    private final Texture gameOver;
    private boolean isGameOver = false;
    private int score = 0;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        camera.setToOrtho(false, FlappyBird.WIDTH/ 2, FlappyBird.HEIGHT/ 2);
        bird = new Bird(50, 200);
        ground = new Ground(camera.position.x - (camera.viewportWidth/2));
        tubes = new Array<>();
        for (int i=1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        background = new Texture("bg.png");
        gameOver = new Texture("gameOver.png");
    }

    @Override
    public void handleInput() {
        if (! isGameOver){
            if (Gdx.input.justTouched()){
                bird.jump();
            }
        }else{
            if (Gdx.input.justTouched()){
                gameStateManager.set(new PlayState((gameStateManager)));
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

        if (!isGameOver){
            camera.position.x = bird.getPosition().x + 80;
            bird.update(dt);
        }

        float screenLeftEdgePosition = (camera.position.x - (camera.viewportWidth/2));
        for (Tube tube: tubes){
            if ((camera.position.x - (camera.viewportWidth/2)) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING)) * TUBE_COUNT);
                score ++;
            }
            if (tube.collides((bird.getBounds()))){
                isGameOver = true;
                break;
            }
        }

        if (screenLeftEdgePosition > ground.getGroundPos1().x + ground.getGround().getWidth()){
            ground.reposition(1, ground.getGround().getWidth() * 2);
        }else if (screenLeftEdgePosition > ground.getGroundPos2().x + ground.getGround().getWidth()){
            ground.reposition(2, ground.getGround().getWidth() * 2);
        }


        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch, BitmapFont font) {
        // spriteBatch only draw the camera area
        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth/ 2), 0);
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for(Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        spriteBatch.draw(ground.getGround(), ground.getGroundPos1().x, ground.getGroundPos1().y);
        spriteBatch.draw(ground.getGround(),ground.getGroundPos2().x, ground.getGroundPos2().y);
//        font.draw(spriteBatch, String.valueOf(score) , bird.getPosition().x, camera.position.y);
        if (isGameOver){
            spriteBatch.draw(gameOver, camera.position.x - gameOver.getWidth()/2, camera.position.y);
        }

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        background.dispose();
        ground.dispose();
        for (Tube tube: tubes){
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }
}
