package menu;

import menu.MenuPanel;
import static utils.Constants.*;
import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    Scoreboard scoreboard = new Scoreboard();
    public SnakeFrame() {
        super("Snake Game in Java");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setMaximumSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMenu(scoreboard);
    }
    public void createMenu(Scoreboard scoreboard){
        MenuPanel menuPanel = new MenuPanel(this, scoreboard);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        this.add(menuPanel);
    }
}
