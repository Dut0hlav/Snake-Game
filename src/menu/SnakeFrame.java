package menu;

import menu.MenuPanel;
import static utils.Constants.*;
import javax.swing.*;
import java.awt.*;

public class SnakeFrame extends JFrame {
    Scoreboard scoreboard = new Scoreboard();
    public SnakeFrame() {
        super("Snake Game in Java");

        // Set the size and layout of the frame
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setMaximumSize(new Dimension(FRAME_WIDTH,FRAME_HEIGHT));
        setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the menu panel and initialize the scoreboard
        createMenu(scoreboard);
    }

    /**
     * Creates the menu panel and adds it to the frame.
     * @param scoreboard the scoreboard object to be passed to the menu panel
     */
    public void createMenu(Scoreboard scoreboard){
        MenuPanel menuPanel = new MenuPanel(this, scoreboard);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        this.add(menuPanel);
    }
}
