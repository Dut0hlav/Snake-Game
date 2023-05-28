package menu;

import game.GamePanel;
import game.Snake;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuPanel extends JPanel {
    private JButton startButton = new JButton("New Game");
    private SnakeFrame snakeFrame;
    private JButton exitButton = new JButton("Exit");
    private JButton leaderboardButton = new JButton("Show Leaderboard");
    private HeaderText headerText = new HeaderText("SNAKE GAME", 42);
    private GameLogo gameLogo = new GameLogo("src/resources/snake-img.png");
    private Scoreboard scoreboard;
    public MenuPanel(SnakeFrame snakeFrame, Scoreboard scoreboard) {
        this.snakeFrame = snakeFrame;
        this.scoreboard = scoreboard;
        startButton.addActionListener(e -> newGame());
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Do you really want to exit the game?") == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton exitLeaderboard = new JButton("Quit");
                exitLeaderboard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        snakeFrame.getContentPane().removeAll();
                        snakeFrame.createMenu(scoreboard);
                        snakeFrame.getContentPane().revalidate();
                        snakeFrame.getContentPane().repaint();
                    }
                });
                snakeFrame.getContentPane().removeAll();
                snakeFrame.getContentPane().add(new HeaderText("LEADERBOARD", 30));
                snakeFrame.getContentPane().add(new LeaderboardText(scoreboard.getLeaderboard()));
                snakeFrame.getContentPane().add(new HeaderText("Good Luck!", 20));
                snakeFrame.getContentPane().add(exitLeaderboard);
                snakeFrame.revalidate();
                snakeFrame.repaint();
            }
        });
        gameLogo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        add(gameLogo);
        add(headerText);
        add(startButton);
        add(leaderboardButton);
        add(exitButton);
        for (Component component : this.getComponents()) {
            if (component instanceof JLabel || component instanceof JButton || component instanceof JTextField) {
                ((JComponent) component).setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }
    }

    private void newGame() {
        String playerName;
        do {
            playerName = JOptionPane.showInputDialog("Enter player name (max 20 characters): ");
            if (playerName == null){
                return;
            }
        } while (playerName.length() > 20 || playerName.equals(""));
        Player player = new Player(playerName);
        ScoreText scoreText = new ScoreText(player.getName());
        HeaderText headerText = new HeaderText("Press ENTER to start the game", 30);
        HeaderText bottomText = new HeaderText("",20);
        GamePanel gamePanel = new GamePanel(player, scoreText, bottomText, snakeFrame, scoreboard);

        snakeFrame.getContentPane().removeAll();
        snakeFrame.getContentPane().add(headerText);
        snakeFrame.getContentPane().add(gamePanel);
        snakeFrame.repaint();
        snakeFrame.setVisible(true);
        snakeFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    snakeFrame.removeKeyListener(this);
                    snakeFrame.getContentPane().remove(headerText);
                    snakeFrame.getContentPane().add(scoreText, 0);
                    snakeFrame.getContentPane().add(bottomText);
                    snakeFrame.validate();
                    gamePanel.startGame();
                    snakeFrame.repaint();
                }
            }
        });
        snakeFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gamePanel.updateSnakeDirection(e);
            }
        });
        snakeFrame.setFocusable(true);
        snakeFrame.requestFocusInWindow();
    }
}