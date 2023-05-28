package game;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static utils.Constants.COLS;
import static utils.Constants.ROWS;

public abstract class Food {
    private final int expGain;  // The amount of experience gain when consumed by the snake
    private final int newBlocks;  // The number of new blocks added to the snake's body
    private final Color color;  // The color of the food
    public static int expirationMoves;  // The number of moves before the food expires
    public static int recurrenceMoves;  // The number of moves between the appearance of new food
    private Point place;  // The position of the food on the grid

    public Food(int expGain, int newBlocks, Color color, ArrayList<Point> snakeBody, ArrayList<Food> foodObjects) {
        this.expGain = expGain;
        this.newBlocks = newBlocks;
        this.color = color;
        setRandomPlace(snakeBody, foodObjects);
    }
    public int getExpGain() {
        return this.expGain;
    }
    public int getNewBlocks() {
        return this.newBlocks;
    }
    public Color getColor() {
        return this.color;
    }
    public Point getPlace(){
        return place;
    }
    public void setRandomPlace(ArrayList<Point> snakeBody, ArrayList<Food> foodObjects) {
        // Sets a random position for the food on the grid, avoiding collision with the snake's body and other food objects
        while(true) {
            Random randomCol = new Random();
            Random randomRow = new Random();
            Point tempPoint = new Point( randomCol.nextInt(COLS), randomRow.nextInt(ROWS));
            if (!snakeBody.contains(tempPoint)) {
                this.place = tempPoint;
                break;
            }
            for (Food food : foodObjects) {
                if (!tempPoint.equals(food.getPlace())) {
                    this.place = tempPoint;
                    break;
                }
            }
        }
    }
}
