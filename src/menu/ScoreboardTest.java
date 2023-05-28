package menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ScoreboardTest {
    private static final String TEST_SCORES_FILE = "test_scores.csv";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a test scores file
        FileWriter writer = new FileWriter(TEST_SCORES_FILE);
        writer.write("Alice,100\n");
        writer.write("Bob,200\n");
        writer.close();
    }

    @Test
    public void testParseScoresFile() {
        // Test parsing the scores file and populating the scoreboard
        Scoreboard scoreboard = new Scoreboard();
        ArrayList<Player> scores = scoreboard.getLeaderboard();
        Assertions.assertEquals(2, scores.size());

        // Check the values of the parsed players
        Player player1 = scores.get(0);
        Assertions.assertEquals("Bob", player1.getName());
        Assertions.assertEquals(200, player1.getMaxGameScore());

        Player player2 = scores.get(1);
        Assertions.assertEquals("Alice", player2.getName());
        Assertions.assertEquals(100, player2.getMaxGameScore());
    }

    @Test
    public void testAddScore() {
        // Test adding a new score to the scoreboard
        Scoreboard scoreboard = new Scoreboard();
        Player player = new Player("Charlie", 150);
        scoreboard.addScore(player);

        // Retrieve the leaderboard and check if the new score is added correctly
        ArrayList<Player> scores = scoreboard.getLeaderboard();
        Assertions.assertEquals(3, scores.size());

        Player newPlayer = scores.get(0);
        Assertions.assertEquals("Bob", newPlayer.getName());
        Assertions.assertEquals(200, newPlayer.getMaxGameScore());
    }

    @Test
    public void testGetLeaderboard() {
        // Test retrieving the leaderboard from the scoreboard
        Scoreboard scoreboard = new Scoreboard();
        ArrayList<Player> leaderboard = scoreboard.getLeaderboard();

        // Check the order and values of the leaderboard
        Assertions.assertEquals(2, leaderboard.size());
        Player player1 = leaderboard.get(0);
        Assertions.assertEquals("Bob", player1.getName());
        Assertions.assertEquals(200, player1.getMaxGameScore());

        Player player2 = leaderboard.get(1);
        Assertions.assertEquals("Alice", player2.getName());
        Assertions.assertEquals(100, player2.getMaxGameScore());
    }

    @Test
    public void testEmptyLeaderboard() throws IOException {
        // Delete the scores file to simulate an empty leaderboard
        File scoresFile = new File(TEST_SCORES_FILE);
        scoresFile.delete();

        // Create a scoreboard with no scores
        Scoreboard scoreboard = new Scoreboard();
        ArrayList<Player> leaderboard = scoreboard.getLeaderboard();

        // Check that the leaderboard is null when there are no scores
        Assertions.assertNull(leaderboard);
    }
}