package com.procrastinators.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.procrastinators.gameobjects.Ball;
import com.procrastinators.gameobjects.OtherBall;
import com.procrastinators.gameobjects.UserBall;
import com.procrastinators.helpers.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by jazeem on 18/06/17.
 */

public class GameWorld {

    private static final String PREF_HIGH_SCORE = "highscore";

    private float gameTime;
    private Random random;
    private float threshhold;
    private List<OtherBall> otherBalls;
    private boolean gameOver;
    private int score;
    private Preferences prefs;
    private UserBall balls[];

    public GameWorld(){
        prefs = Gdx.app.getPreferences("game");
        initialize();
    }

    public UserBall[] getBall() {
        return balls;
    }

    public List<OtherBall> getOtherBalls() {
        return otherBalls;
    }


    private void initialize(){
        balls = new UserBall[2];
        balls[0] = new UserBall();
        balls[1] = new UserBall((float) Math.PI);
        gameTime = 0f;
        random = new Random();
        threshhold = 0f;
        gameOver = false;
        otherBalls = new ArrayList<OtherBall>();
        score = 0;
    }

    public void update(float delta){
        if(!gameOver){
            gameTime += delta;
            if(gameTime > threshhold){
                threshhold += 1 + random.nextFloat() * 1;
                OtherBall otherBall = new OtherBall(random.nextInt(3),
                        balls[0].getTheta() + (float) Math.PI / 2,// + random.nextFloat() * 180,
                        1 + random.nextInt(2));
                otherBall.update(delta);
                otherBalls.add(otherBall);
            }
            Iterator<OtherBall> i = otherBalls.iterator();
            while (i.hasNext()) {
                OtherBall b = i.next();
                //if(b.isDanger()){
                    b.update(delta);
                    if(b.getTtl() < 0)
                        i.remove();
                //}
            }
            for (UserBall ball:
                 balls) {
                ball.update(delta);
            }
            checkCollisions();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    private void checkCollisions(){
        for (UserBall ball:
             balls) {
            Iterator<OtherBall> i = otherBalls.iterator();
            while (i.hasNext()) {
                OtherBall b = i.next();
                if(Math.abs(b.getX() - ball.getX()) <= 2 * Constants.BALL_RADIUS &&
                        Math.abs(b.getY() - ball.getY()) <= 2 * Constants.BALL_RADIUS){
                    if(b.getType() == Constants.BallType.RED){
                        gameOver = true;
                        if(prefs.getInteger(PREF_HIGH_SCORE, 0) < score)
                            prefs.putInteger(PREF_HIGH_SCORE, score).flush();
                    }
                    else if(b.getType() == Constants.BallType.BLUE){
                        i.remove();
                        for (UserBall ub:
                             balls) {
                            ub.invertDirection();
                        }
                        score += b.getLevel() + 2;
                    }
                    else {
                        i.remove();
                        score += b.getLevel() + 1;
                    }

                }
            }
        }
    }

    public int getHighScore(){
        return prefs.getInteger(PREF_HIGH_SCORE, 0);
    }

    public void screenTouched(int x, int y){
        if(gameOver)
            initialize();
        for (UserBall ball:
             balls) {
            ball.screenTouched(x, y);
        }
    }
}
