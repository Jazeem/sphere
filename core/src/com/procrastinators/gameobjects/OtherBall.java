package com.procrastinators.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.procrastinators.helpers.Constants;

/**
 * Created by jazeem on 24/06/17.
 */

public class OtherBall extends Ball {
    private float ttl;
    private Constants.BallType type;
    private int level;

    public Constants.BallType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public OtherBall(int level, float theta, int type){
        this.theta = theta;
        this.level = level;
        this.r = Constants.RADIUS_STEP*level + Constants.RADIUS_MIN;
        switch (type){
            case 0:
                this.type = Constants.BallType.GREEN;
                this.color = Color.GREEN;
                break;
            case 1:
                this.type = Constants.BallType.RED;
                this.color = Color.RED;
                break;
            case 2:
                this.type = Constants.BallType.BLUE;
                this.color = Color.BLUE;
                break;
            default:
                break;
        }
        this.radius = Constants.BALL_RADIUS;
        ttl = 5f;
    }

    public float getTtl() {
        return ttl;
    }

    @Override
    public void update(float delta) {
        ttl -= delta;

        super.update(delta);
    }
}
