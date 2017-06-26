package com.procrastinators.gameobjects;


import com.badlogic.gdx.graphics.Color;
import com.procrastinators.helpers.Constants;

/**
 * Created by jazeem on 18/06/17.
 */

public class Ball {
    protected float x;
    protected float y;
    protected float radius;
    protected Color color;
    protected float r;
    protected float theta;


    public float getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public Ball(){
        x = Constants.CENTER_X;
        y = Constants.CENTER_Y;
        radius = Constants.BALL_RADIUS;
        color = Color.WHITE;
    }


    public float getTheta() {
        return theta;
    }

    public void update(float delta){
        x = r * (float) Math.sin(theta);
        y = r * (float) Math.cos(theta);
        x += Constants.CENTER_X;
        y += Constants.CENTER_Y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
