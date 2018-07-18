package fr.wcs.mytournament;

public class PlayerModel {
    private int position, id;
    private String pseudo;
    private int points;
    private int wins;
    private int loses;

    public PlayerModel(int position, int id, String pseudo, int points, int wins, int loses) {
        this.position = position;
        this.id = id;
        this.pseudo = pseudo;
        this.points = points;
        this.wins = wins;
        this.loses = loses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }
}
