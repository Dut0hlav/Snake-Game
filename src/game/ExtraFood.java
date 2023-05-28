package game;

import java.awt.*;
import java.util.ArrayList;
import static utils.Constants.*;

public class ExtraFood extends Food {
    public static final int expirationMoves = 40;
    public static final int recurrenceMoves = 5;
    public ExtraFood(ArrayList<Point> snakeBody, ArrayList<Food> foodObjects) {
        super(250, 4, EXTRA_FOOD_COLOR, snakeBody, foodObjects);
    }
}