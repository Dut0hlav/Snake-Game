package menu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import static utils.Constants.BACKGROUND_COLOR;


public class LeaderboardText extends JTextArea {

    public LeaderboardText(ArrayList<Player> leaderboard) {
        this.setFont(new Font("Arial", Font.PLAIN, 15));
        this.setBackground(BACKGROUND_COLOR);

        // Generate the text for the leaderboard
        if (leaderboard != null) {
            StringBuilder leaderboardText = new StringBuilder();
            for (int i = 0; i < leaderboard.size(); i++) {
                Player player = leaderboard.get(i);
                leaderboardText.append((i + 1)).append(". ").append(player.getName()).append(" - ").append(player.getMaxGameScore()).append("\n");
            }
            setText(leaderboardText.toString());
        } else {
            setText("No scores, yet!");
        }
        setEditable(false);
    }
}
