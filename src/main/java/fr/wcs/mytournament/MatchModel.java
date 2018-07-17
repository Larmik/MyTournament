package fr.wcs.mytournament;

public class MatchModel {

    private int journey;
    private String player1;
    private String player2;

    public MatchModel(int journey, String player1, String player2) {
        this.journey = journey;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getJourney() {
        return journey;
    }

    public void setJourney(int journey) {
        this.journey = journey;
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
