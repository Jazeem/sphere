package com.procrastinators.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.procrastinators.helpers.Constants;

/**
 * Created by jazeem on 24/06/17.
 */

public class OtherBall extends Ball {
    private float ttl;
    private boolean danger;

    public boolean isDanger() {
        return danger;
    }

    public OtherBall(float r, float theta, Boolean danger){
        this.r = r;
        this.theta = theta;
        this.danger = danger;
        if(this.danger)
            this.color = Color.RED;
        else
            this.color = Color.GREEN;

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
