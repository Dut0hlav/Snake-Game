package game;

import static utils.Constants.*;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @org.junit.jupiter.api.Test
    void move() {
        Snake snake = new Snake(new Point(10,5));
        snake.move();
        assertEquals(snake.getHead(), new Point(11,5));
    }

    @org.junit.jupiter.api.Test
    void extendBody() {
        Snake snake = new Snake(new Point(10,5));
        snake.extendBody(2);
        int size = snake.getBody().size();
        assertEquals(snake.getBody().get(size - 1), new Point(6, 5));
    }

    @org.junit.jupiter.api.Test
    void setNextDirection() {
        Snake snake = new Snake(new Point(2,5));
        Direction inputDirection = Direction.LEFT;
        assertFalse(snake.setNextDirection(inputDirection));
    }

    @org.junit.jupiter.api.Test
    void getHead() {
        Point head = new Point(2, 5);
        Snake snake = new Snake(head);
        snake.extendBody(3);
        assertEquals(head, snake.getHead());
    }

    @org.junit.jupiter.api.Test
    void getBody() {
        Point head = new Point(2, 5);
        Snake snake = new Snake(head);
        snake.extendBody(2);
        ArrayList<Point> body = new ArrayList<Point>();
        body.add(snake.getHead());
        ArrayList<Point> snakeBody = new ArrayList<Point>(snake.getBody());
        snakeBody.remove(0);
        body.addAll(snakeBody);
        assertEquals(snake.getBody(), body);
    }
}