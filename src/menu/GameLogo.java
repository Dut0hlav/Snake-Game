package menu;

import javax.swing.*;

public class GameLogo extends JLabel {
    public GameLogo(String imagePath) {
        this.setIcon(new ImageIcon(imagePath));
    }
}