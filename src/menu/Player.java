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
    public String getName(){
        return name;
    }
    public int getGameScore() {
        return this.gameScore;
    }
    public int getMaxGameScore() {
        return this.maxGameScore;
    }
    public void setMaxGameScore(int newMax) {
        maxGameScore = newMax;
    }
    public void setGameScore(int newExperience){
        if (newExperience >= 0){
               this.gameScore = newExperience;
        }
    }
}