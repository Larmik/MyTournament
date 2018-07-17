package fr.wcs.mytournament;

public class TournamentModel {

    private String name;
    private String sport;
    private String type;
    private String mode;

    public TournamentModel(String name, String sport, String type, String mode) {
        this.name = name;
        this.sport = sport;
        this.type = type;
        this.mode = mode;
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
}
