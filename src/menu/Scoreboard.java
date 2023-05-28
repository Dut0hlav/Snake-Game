package menu;
import javax.imageio.IIOException;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import static utils.Constants.*;

public class Scoreboard {
    private ArrayList<Player> scores = new ArrayList<Player>();
    private File csvFile = new File(System.getProperty("user.home"), SCORES_FILE);

    public Scoreboard(){
        parseScoresFile();
    }
    private void parseScoresFile() {
        ArrayList<String> rawScores = new ArrayList<String>();
        try {
            System.getProperty("user.home");
            Scanner reader = new Scanner(csvFile);
            while (reader.hasNextLine()) {
                rawScores.add(reader.nextLine());
            }
        } catch(FileNotFoundException e) {
            System.out.println("Could not access the scores file. Creating one.");
            try {
                csvFile.createNewFile();
            } catch(java.io.IOException f) {
                System.out.println("Could not either create or access the scores file. Leaderboard functionality is broken.");
            }
            return;
        }
        for (String rawScore : rawScores) {
            String[] temp = rawScore.split(",");
            scores.add(new Player(temp[0], Integer.parseInt(temp[1])));
        }
    }

    /**
     * Adds score to the scoreboard and syncs the change with the scores file.
     * @param newPlayer
     */
    public void addScore(Player newPlayer) {
        for (Player player : scores) {
            if (newPlayer.getName().equals(player.getName())) {
                if (player.getMaxGameScore() < newPlayer.getGameScore()) {
                   player.setMaxGameScore(newPlayer.getGameScore());
                   return;
                }
            }
        }
        newPlayer.setMaxGameScore(newPlayer.getGameScore());
        scores.add(newPlayer);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(csvFile));
            for (Player player : scores) {
                out.write(player.getName() + "," + player.getMaxGameScore());
                out.newLine();
            }
            out.flush();
            out.close();
        } catch(java.io.IOException e) {
            System.out.println("Could not access the scores file. Leaderboard functionality is limited.");
        }
    }
    public ArrayList<Player> getLeaderboard() {
        if (scores.size() > 0) {
            ArrayList<Player> allScores = new ArrayList<Player>(scores);
            ArrayList<Player> leaderboard = new ArrayList<Player>();
            for (int i = 0; i < PLAYERS_IN_LEADERBOARD; i++) {
                Player bestPlayer = allScores.get(0);
                for (Player player : allScores) {
                    if (player.getMaxGameScore() > bestPlayer.getMaxGameScore()) {
                        bestPlayer = player;
                    }
                }
                allScores.remove(bestPlayer);
                leaderboard.add(bestPlayer);
                if (allScores.size() == 0) {
                    return leaderboard;
                }
            }
            return leaderboard;
        }
        return null;
    }
}
