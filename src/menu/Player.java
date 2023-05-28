package menu;

public class Player {
    private String name;
    private int gameScore;
    private int maxGameScore;

    public Player(String name){
        this.name = name;
    }
    public Player(String name, int maxGameScore) {
        this.name = name;
        this.maxGameScore = maxGameScore;
    }

    // Getter for the player's name
    public String getName(){
        return name;
    }
    // Getter for the player's current game score
    public int getGameScore() {
        return this.gameScore;
    }
    // Getter for the player's maximum game score
    public int getMaxGameScore() {
        return this.maxGameScore;
    }
    // Setter for the player's maximum game score
    public void setMaxGameScore(int newMax) {
        maxGameScore = newMax;
    }
    // Setter for the player's current game score
    public void setGameScore(int newExperience){
        // Ensure the new game score is non-negative
        if (newExperience >= 0){
               this.gameScore = newExperience;
        }
    }
}