package com.procrastinators.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.procrastinators.gameobjects.Ball;
import com.procrastinators.gameobjects.UserBall;
import com.procrastinators.helpers.AssetLoader;
import com.procrastinators.helpers.Constants;

/**
 * Created by jazeem on 18/06/17.
 */

public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;

    public GameRenderer(GameWorld world){
        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 1080, 1920);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
    }

    public void render(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(myWorld.getGameState() != Constants.GameState.DEAD){
            UserBall[] balls = myWorld.getBall();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            for (int i = Constants.RADIUS_MIN; i <= Constants.RADIUS_MAX; i += Constants.RADIUS_STEP)
                shapeRenderer.circle(Constants.CENTER_X, Constants.CENTER_Y, i);
            shapeRenderer.end();

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            if(myWorld.getGameState() == Constants.GameState.ALIVE || (int)(myWorld.getGameTime() * 2) % 2 == 0 )
                for (UserBall ball:
                     balls) {
                    drawCircle(ball);
                }
            //shapeRenderer.line(540, 960, ball.getX(), ball.getY());

            for (Ball b:
                    myWorld.getOtherBalls()) {
                drawCircle(b);
            }
            shapeRenderer.end();
            batcher.begin();
            batcher.disableBlending();
            drawText(String.valueOf(myWorld.getScore()),Constants.CENTER_X, Constants.CENTER_Y - 600, 3);
//            String speed = String.valueOf(ball.getAngV() * 100);
//            if(speed.split("\\.").length > 1)
//                speed = speed.split("\\.")[0];
//            drawText(speed, Constants.CENTER_X, Constants.CENTER_Y - 700, 3);
            batcher.end();
        }
        else{
            batcher.begin();
            batcher.disableBlending();
            batcher.draw(AssetLoader.texture, Constants.CENTER_X - 50, Constants.CENTER_Y - 50, 100, 100);
            drawText("gameover",Constants.CENTER_X, Constants.CENTER_Y - 300, 3);
            drawText(String.format("highscore%d", myWorld.getHighScore()), Constants.CENTER_X, Constants.CENTER_Y - 400, 3);
            drawText(String.format("score%d", myWorld.getScore()), Constants.CENTER_X, Constants.CENTER_Y - 500, 3);
            batcher.end();
        }
    }

    private void drawText(String text, int x, int y, int scale){
        int len = text.length();
        int start = x - (len * Constants.LETTER_WIDTH * scale / 2);
        int offset;
        for(int i = 0; i < len; i++){
            char c = text.toLowerCase().charAt(i);
            if(c >= 97)
                offset = -80;
            else
                offset = -48;
            batcher.draw(AssetLoader.letters[c + offset],
                    start + i * Constants.LETTER_WIDTH * scale,
                    y - Constants.LETTER_HEIGHT / 2,
                    Constants.LETTER_WIDTH * scale,
                    Constants.LETTER_HEIGHT * scale);
        }
    }

    public void drawCircle(Ball ball){
        shapeRenderer.setColor(ball.getColor());
        shapeRenderer.circle(ball.getX(), ball.getY(), ball.getRadius());
    }
}
