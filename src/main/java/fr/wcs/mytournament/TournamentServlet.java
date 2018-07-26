package fr.wcs.mytournament;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TournamentServlet", urlPatterns = "/tournament")
public class TournamentServlet extends HttpServlet {

    private static Connection sConnection = null;

    static Connection instantiateSQL() {
        if (sConnection == null) {
            try {
                Class driverClass = Class.forName("com.mysql.jdbc.Driver");
                Driver driver = (Driver) driverClass.newInstance();
                DriverManager.registerDriver(driver);
                sConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "wcs", "RrPp4zq?XtT=Ferh");

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                e.printStackTrace();
            }
        }
        return sConnection;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TournamentModel tournamentModel = null;
        List<PlayerModel> playerList = new ArrayList<>();
        List<MatchModel> matchList = new ArrayList<>();
        try {
            tournamentModel = writePlayers(id, playerList, tournamentModel);
            readMatches(matchList, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (tournamentModel != null) {
            request.getSession().setAttribute("tournament", tournamentModel);
            request.getSession().setAttribute("players", playerList);
            request.getSession().setAttribute("matches", matchList);
            this.getServletContext().getRequestDispatcher("/WEB-INF/consult_tournament.jsp").forward(request, response);
        }
    }

    private TournamentModel writePlayers(int id, List<PlayerModel> playerList, TournamentModel tournament) throws SQLException {
        PreparedStatement preparedStatement = instantiateSQL()
                .prepareStatement("SELECT * FROM championship WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String name = result.getString("name");
            String sport = result.getString("sport");
            String type = result.getString("type");
            String mode = result.getString("mode");
            tournament = new TournamentModel(id, name, sport, type, mode, 0);
        }
        int position = 1;
        PreparedStatement playerStatement = instantiateSQL().prepareStatement("SELECT players.pseudo, players.id, pc.points, pc.position, pc.wins, pc.loses, pc.winsets, pc.losesets FROM players JOIN players_championship pc ON players.id=pc.id_players WHERE pc.id = ? ORDER BY pc.points DESC, pc.wins DESC, pc.winsets-pc.losesets DESC");
        playerStatement.setInt(1, id);
        ResultSet playerResult = playerStatement.executeQuery();
        while (playerResult.next()) {
            String pseudo = playerResult.getString("pseudo");
            int playerId = playerResult.getInt("id");
            int points = playerResult.getInt("points");
            int wins = playerResult.getInt("wins");
            int loses = playerResult.getInt("loses");
            int winsets = playerResult.getInt("winsets");
            int losesets = playerResult.getInt("losesets");
            playerList.add(new PlayerModel(position, playerId, pseudo, points, wins, loses, winsets, losesets));
            position++;
        }
        return tournament;
    }

    private void readMatches(List<MatchModel> matchList, int id) throws SQLException {
        PreparedStatement requestStatement = instantiateSQL().prepareStatement("SELECT matches.id AS id, p1.pseudo as player1, p2.pseudo as player2, hasBeenPlayed, score1, score2 FROM matches INNER JOIN players p1 ON p1.id = matches.id_player1 INNER JOIN players p2 ON p2.id = matches.id_player2 WHERE matches.id_championship = ?");
        requestStatement.setInt(1, id);
        ResultSet requestResult = requestStatement.executeQuery();
        while (requestResult.next()) {
            int matchId = requestResult.getInt("id");
            String pseudo1 = requestResult.getString("player1");
            String pseudo2 = requestResult.getString("player2");
            boolean hasBeenPlayed = requestResult.getBoolean("hasBeenPlayed");
            int score1 = requestResult.getInt("score1");
            int score2 = requestResult.getInt("score2");
            matchList.add(new MatchModel(matchId, pseudo1, pseudo2, hasBeenPlayed, score1, score2));
        }
    }

}
