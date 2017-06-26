package com.procrastinators.gameobjects;

import com.procrastinators.helpers.Constants;

/**
 * Created by jazeem on 24/06/17.
 */

public class UserBall extends Ball {
    private float angV;
    private int tX;

    public UserBall(){
        r = 300;
        theta = 0;
        angV = 1;
    }

    public void screenTouched(int x, int y){
        if(x > Constants.CENTER_X){
            if(r < Constants.RADIUS_MAX)
                r += Constants.RADIUS_STEP;
            else
                r = Constants.RADIUS_MAX;
        }
        else{
            if(r > Constants.RADIUS_MIN)
                r -= Constants.RADIUS_STEP;
            else
                r = Constants.RADIUS_MIN;
        }
    }

    public float[] moveTowards(int tX, int tY){
        if(tX == x){
            if(tY == y)
                return new float[]{0f, 0f};
            else
                return new float[]{500f, 0f};
        }
        float slope = Math.abs((tY - y)/(tX - x));
        int mX = 1, mY = 1;
        float retX, retY;
        retX = (float) (500 / (Math.sqrt(1 + slope * slope)));
        retY = slope * retX;
        if(tY < y)
            mY = -1;
        if(tX < x)
            mX = -1;
        retX *= (float) mX;
        retY *= (float) mY;
        return new float[]{retX, retY};
    }

    @Override
    public void update(float delta) {
        if(angV <= 2.5f)
            angV += delta * 0.01;
        theta += angV*delta;
        if(theta > 360)
            theta -= 360;
        super.update(delta);
    }
}
