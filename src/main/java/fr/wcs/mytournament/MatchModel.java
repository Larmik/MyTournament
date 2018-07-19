package fr.wcs.mytournament;

public class MatchModel {

    private int id;
    private int score1;
    private int score2;
    private String player1;
    private String player2;
    private boolean hasBeenPlayed;

    public MatchModel(int id, String player1, String player2, boolean hasBeenPlayed, int score1, int score2) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.hasBeenPlayed = hasBeenPlayed;
        this.score1 = score1;
        this.score2 = score2;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public boolean isHasBeenPlayed() {
        return hasBeenPlayed;
    }

    public void setHasBeenPlayed(boolean hasBeenPlayed) {
        this.hasBeenPlayed = hasBeenPlayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }
}
