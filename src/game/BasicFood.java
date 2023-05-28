package game;

import java.awt.*;
import java.util.ArrayList;
import static utils.Constants.*;

public class BasicFood extends Food {
    public static final int expirationMoves = 25;
    public static final int recurrenceMoves = 30;

    public BasicFood(ArrayList<Point> snakeBody, ArrayList<Food> foodObjects) {
        super(100, 1, BASIC_FOOD_COLOR, snakeBody, foodObjects);
    }
}
