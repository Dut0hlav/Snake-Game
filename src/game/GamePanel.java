package game;

import menu.*;
import utils.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import static utils.Constants.*;


public class GamePanel extends JPanel {
//    JFrame snakeFrame = (JFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
    private GameTile[][] gameGrid = new GameTile[ROWS][COLS];
    SnakeFrame snakeFrame;
    private Snake snake = new Snake(new Point(ROWS / 2, COLS / 2));
    private ArrayList<Food> foodObjects = new ArrayList<Food>();
    private Player player;
    private ScoreText scoreText;
    private HeaderText bottomText;
    private Scoreboard scoreboard;
    private int allMoves;
    public GamePanel(Player player, ScoreText scoreText, HeaderText bottomText, SnakeFrame parentFrame, Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        this.player = player;
        this.scoreText = scoreText;
        this.bottomText = bottomText;
        this.snakeFrame = parentFrame;
        setLayout(new GridLayout(ROWS, COLS));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        for (int ycor = 0; ycor < ROWS; ycor++) {
            for (int xcor = 0; xcor < COLS; xcor++) {
                GameTile tile = new GameTile(TILE_COLOR);
                gameGrid[xcor][ycor] = tile;
                add(tile);
            }
        }
    }
    public GameTile getTile(int row, int col) {
        return gameGrid[col][row];
    }

    private void paintGame() {
        for (int ycor = 0; ycor < ROWS; ycor++) {
            for (int xcor = 0; xcor < COLS; xcor++){
                gameGrid[xcor][ycor].setColor(TILE_COLOR);
            }
        }
        for (Food food : foodObjects) {
            Point foodPlace = food.getPlace();
            gameGrid[foodPlace.x][foodPlace.y].setColor(food.getColor());
        }
        Point snakeHead = snake.getHead();
        for (Point snakePoint : snake.getBody()) {
            if (snakeHead.equals(snakePoint)){
                gameGrid[snakePoint.x][snakePoint.y].setColor(HEAD_COLOR);
                continue;
            }
            gameGrid[snakePoint.x][snakePoint.y].setColor(BODY_COLOR);
        }
        repaint();
        System.out.println(player.getGameScore());
    }

    public void startGame() {
        // start timers etc.
        System.out.println("Game started");
        paintGame();
        Timer moveTimer = new Timer();
        TimerTask moveSnake = new TimerTask() {
            public void run() {
                allMoves++;
                snake.move();
                resolveSnakeFoodCollision();
                createFood();
                paintGame();
                if (isSnakeBodyCollision()) {
                    moveTimer.cancel();
                    endGame();
                }
            }
        };
        moveTimer.scheduleAtFixedRate(moveSnake, 0, 200);
        // DEBUG FOOD NOW
        BasicFood basicFood = new BasicFood(snake.getBody(), foodObjects);
        foodObjects.add(basicFood);
        ExtraFood extraFood = new ExtraFood(snake.getBody(), foodObjects);
        foodObjects.add(extraFood);

    }
    public void updateSnakeDirection(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP -> snake.setNextDirection(Constants.Direction.UP);
            case KeyEvent.VK_DOWN -> snake.setNextDirection(Constants.Direction.DOWN);
            case KeyEvent.VK_RIGHT -> snake.setNextDirection(Constants.Direction.RIGHT);
            case KeyEvent.VK_LEFT -> snake.setNextDirection(Constants.Direction.LEFT);
        }
    }
    private void createFood(){
        if ((allMoves % BasicFood.expirationMoves) == 0){
            foodObjects.removeIf(food -> food instanceof BasicFood);
            if ((allMoves % BasicFood.recurrenceMoves) == 0) {
                BasicFood basicFood = new BasicFood(snake.getBody(), foodObjects);
                foodObjects.add(basicFood);
            }
        }
        if ((allMoves % ExtraFood.expirationMoves) == 0){
            foodObjects.removeIf(food -> food instanceof ExtraFood);
            if ((allMoves % ExtraFood.recurrenceMoves) == 0) {
                ExtraFood extraFood = new ExtraFood(snake.getBody(), foodObjects);
                foodObjects.add(extraFood);
            }
        }
    }
    private void resolveSnakeFoodCollision() {
        for (Food food : foodObjects) {
            if (food.getPlace().equals(snake.getHead())) {
                snake.extendBody(food.getNewBlocks());
                player.setGameScore(player.getGameScore() + food.getExpGain());
                scoreText.setScore(player.getGameScore());
                foodObjects.remove(food);
                break;
            }
        }
    }
    private boolean isSnakeBodyCollision() {
        Point head = snake.getHead();
        ArrayList<Point> body = snake.getBody();
        for (int i = 1; i < body.size(); i++){
            if (body.get(i).equals(head)) {
                return true;
            }
        }
        return false;
    }
    private void endGame() {
        scoreText.endGame();
        scoreboard.addScore(player);
        bottomText.setText("Your score is " + player.getGameScore() + ". Press ENTER to quit.");
        snakeFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    snakeFrame.removeKeyListener(this);
                    snakeFrame.getContentPane().removeAll();
                    snakeFrame.createMenu(scoreboard);
                    snakeFrame.getContentPane().revalidate();
                    snakeFrame.getContentPane().repaint();
                }
            }
        });
    }
}
