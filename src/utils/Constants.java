package utils;

import javax.swing.*;
import java.awt.*;

public final class Constants {
    public final static Color BACKGROUND_COLOR =  UIManager.getColor ( "Panel.background" );
    public final static int FRAME_WIDTH = 650;
    public final static int FRAME_HEIGHT = 650;
    public final static int ROWS = 30;
    public final static int COLS = 30;
    public static final int TILE_SIZE = 15;
    public static final Color TILE_COLOR = Color.BLUE;
    public static final Color HEAD_COLOR = Color.RED;
    public static final Color BODY_COLOR = Color.GREEN;
    public static final Color BASIC_FOOD_COLOR = Color.YELLOW;
    public static final Color EXTRA_FOOD_COLOR = Color.MAGENTA;
    public static final int PLAYERS_IN_LEADERBOARD = 3;
    public static final String SCORES_FILE = "scores.csv";
    public static enum Direction {
        UP, LEFT, DOWN, RIGHT;
    }
}