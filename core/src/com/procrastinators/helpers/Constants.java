package com.procrastinators.helpers;

/**
 * Created by jazeem on 24/06/17.
 */

public class Constants {
    public static final int RADIUS_MAX = 450;
    public static final int RADIUS_MIN = 200;
    public static final int RADIUS_STEP = 125;
    public static final int BALL_RADIUS = 36;
    public static final int CENTER_X = 540;
    public static final int CENTER_Y = 960;
    public static final int LETTER_WIDTH = 18;
    public static final int LETTER_HEIGHT = 25;

    public enum BallType { GREEN, RED, BLUE }
    public enum GameState { ALIVE, DYING, DEAD }
}
