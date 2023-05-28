package game;

import java.util.ArrayList;
import java.awt.Point;
import static utils.Constants.*;

/**
 * A class that represents the snake object in the game. Essentially an object with an array list with points, directions of movement and relevant methods to manipulate these two.
 */
public class Snake {
    private Direction currentDirection;
    private Direction nextDirection;
    private ArrayList<Point> body = new ArrayList<>();



    public Snake(Point headPosition) {
        // Constructor for the Snake class
        body.add(headPosition);
        body.add(new Point(headPosition.x - 1, headPosition.y));
        body.add(new Point(headPosition.x - 2, headPosition.y));
        this.currentDirection = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
    }

    /**
     * This method creates a new head for the snake at the next point in the direction of the snake and removes the last point in the body of the snake, thus in effect moving the snake in its direction.
     */
    public void move() {
        this.currentDirection = this.nextDirection;
        Point head = floorPoint(getNewHead());
        body.add(0, head);
        body.remove(body.size() - 1);
        //System.out.println("POINTS: " + getBody().toString());
    }

    /**
     * This method extends the body of the snake by a given amount of blocks in the direction of the last two blocks (change in either x or y direction)
     *
     * @param newBlocks quantity of blocks by which the snake will be extended
     */
    public void extendBody(int newBlocks) {
        for (int i = 0; i < newBlocks; i++) {
            Point lastPoint = body.get(body.size() - 1);
            Point secondLastPoint = body.get(body.size() - 2);
            Point newPoint = new Point(lastPoint);
            if (lastPoint.x != secondLastPoint.x) {
                newPoint.x = (lastPoint.x + (lastPoint.x - secondLastPoint.x));
            } else {
                newPoint.y = (lastPoint.y + (lastPoint.y - secondLastPoint.y));
            }
            body.add(floorPoint(newPoint));
        }
    }

    /**
     * This method translates a point one point outside the game grid into the grid. Makes sure the whole snake is always in the grid.
     *
     * @param point A point to be translated into the grid.
     * @return A point in the grid.
     */
    private Point floorPoint(Point point) {
        switch (point.x) {
            case ROWS -> point.x = 0;
            case -1 -> point.x = ROWS - 1;
        }
        switch (point.y) {
            case COLS -> point.y = 0;
            case -1 -> point.y = COLS - 1;
        }
        return point;
    }
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }
    // Returns the current direction of the snake
    public Direction getNextDirection() {
        return this.nextDirection;
    }
    // Returns the next direction of the snake
    public boolean setNextDirection(Direction inputDirection) {
        // Sets the next direction of the snake if it is a valid direction
        if ((inputDirection.ordinal() % 2) != (this.currentDirection.ordinal() % 2)) {
            this.nextDirection = inputDirection;
            return true;
        }
        else {
            return false;
        }
    }
    private Point getNewHead() {
        // Returns the position of the new head of the snake based on its current direction
        Point head = new Point(getHead());
        return modifyPoint(head, currentDirection);
    }
    private Point modifyPoint(Point point, Direction direction){
        // Modifies the given point based on the specified direction
        switch (direction) {
            case UP -> point.y--;
            case DOWN -> point.y++;
            case LEFT -> point.x--;
            case RIGHT -> point.x++;
        }
        return point;
    }
    public Point getHead() {
        return body.get(0);
    }
    // Returns the position of the snake's head
    public ArrayList<Point> getBody() {
        // Returns the array list representing the snake's body
        return body;
    }
}