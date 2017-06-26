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

    public GameWorld(){
        prefs = Gdx.app.getPreferences("game");
        initialize();
    }

    public Ball getBall() {
        return ball;
    }

    private UserBall ball;

    public List<OtherBall> getOtherBalls() {
        return otherBalls;
    }


    private void initialize(){
        ball = new UserBall();
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
                threshhold += random.nextFloat() * 5;
                OtherBall otherBall = new OtherBall(Constants.RADIUS_STEP*random.nextInt(3) + Constants.RADIUS_MIN,
                        ball.getTheta() + 90,// + random.nextFloat() * 180,
                        random.nextBoolean());
                otherBall.update(delta);
                otherBalls.add(otherBall);
            }
            Iterator<OtherBall> i = otherBalls.iterator();
            while (i.hasNext()) {
                OtherBall b = i.next();
                if(b.isDanger()){
                    b.update(delta);
                    if(b.getTtl() < 0)
                        i.remove();
                }
            }
            ball.update(delta);
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
        Iterator<OtherBall> i = otherBalls.iterator();
        while (i.hasNext()) {
            OtherBall b = i.next();
            if(Math.abs(b.getX() - ball.getX()) <= 2 * Constants.BALL_RADIUS &&
                    Math.abs(b.getY() - ball.getY()) <= 2 * Constants.BALL_RADIUS){
                if(b.isDanger()){
                    gameOver = true;
                    if(prefs.getInteger(PREF_HIGH_SCORE, 0) < score)
                        prefs.putInteger(PREF_HIGH_SCORE, score).flush();
                }
                else {
                    i.remove();
                    score++;
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
        ball.screenTouched(x, y);
    }
}
