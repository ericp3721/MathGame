public class Player {

    private String name;
    private int score;
    private int winningSteak;

    public Player(String name) {
        this.name = name;
        score = 0;
    }

    public int getScore() {
        return score;
    }
    public int getWinningSteak() {
        return winningSteak;
    }

    public String getName() {
        return name;
    }

    public void incrementScore() {
        score++;
    }
    public void incrementWinningStreak() {
        winningSteak++;
    }

    public void reset() {
        score = 0;
    }
    public void resetWin() {
        winningSteak = 0;
    }
}