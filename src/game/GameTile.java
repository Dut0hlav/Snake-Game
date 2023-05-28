package game;

import javax.swing.*;
import java.awt.*;
import static utils.Constants.*;

public class GameTile extends JPanel {
    private Color tileColor;
    public GameTile(Color color) {
        tileColor = color;
        setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
        setBackground(tileColor);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
    public void setColor(Color color) {
        setBackground(color);
    }
    public Color getColor() {
        return tileColor;
    }
}
