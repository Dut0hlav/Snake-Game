package menu;

import javax.swing.*;
import java.awt.*;
import static utils.Constants.BACKGROUND_COLOR;

public class ScoreText extends JTextField {
    private final String playerName;

    public ScoreText(String playerName) {
        this.playerName = playerName;

        // Set the properties of the score text field
        this.setColumns(20);
        this.setHorizontalAlignment(CENTER);
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setEditable(false);
        this.setBackground(BACKGROUND_COLOR);
        this.setText(playerName + ": 0");
        this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    } /**
     * Sets the score in the score text field.
     * @param score the score to be displayed
     */
    public void setScore(int score) {
        this.setText(playerName + ": " + score);
    }
    /**
     * Updates the score texRt field with a "GAME OVE" message.
     */
    public void endGame() {
        this.setText("GAME OVER");
    }

}