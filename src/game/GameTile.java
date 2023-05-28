package game;

import javax.swing.*;
import java.awt.*;
import static utils.Constants.*;

public class GameTile extends JPanel {
    private Color tileColor;
    public GameTile(Color color) {
        // Constructor for the game tile
        tileColor = color;
        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        setBackground(tileColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
    // Returns the size of the game tile
    public void setColor(Color color) {
        setBackground(color);
    }
    // Sets the background color of the game tile
    public Color getColor() {
        return tileColor;
    }
    // Returns the current color of the game tile
}
