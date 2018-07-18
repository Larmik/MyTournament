package fr.wcs.mytournament;

public class TournamentModel {

    private int id;
    private String name;
    private String sport;
    private String type;
    private String mode;
    private int players;


    public TournamentModel(int id, String name, String sport, String type, String mode, int players) {
        this.id = id;
        this.name = name;
        this.sport = sport;
        this.type = type;
        this.mode = mode;
        this.players = players;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }
}
