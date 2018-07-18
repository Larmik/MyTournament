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

    static Connection instantiateSQL() {
        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return null;
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
            this.getServletContext().getRequestDispatcher("/consult_tournament.jsp").forward(request, response);
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

        PreparedStatement playerStatement = instantiateSQL().prepareStatement("SELECT players.pseudo, players.id FROM players JOIN players_championship ON players.id=players_championship.id_players WHERE players_championship.id = ?");
        playerStatement.setInt(1, id);
        ResultSet playerResult = playerStatement.executeQuery();
        while (playerResult.next()) {
            String pseudo = playerResult.getString("pseudo");
            int playerId = playerResult.getInt("id");
            playerList.add(new PlayerModel(1, playerId, pseudo, 0, 0, 0));
        }
        return tournament;
    }

    private void readMatches(List<MatchModel> matchList, int id) throws SQLException {
        PreparedStatement requestStatement = instantiateSQL().prepareStatement("SELECT p1.pseudo as player1, p2.pseudo as player2 FROM matches INNER JOIN players p1 ON p1.id = matches.id_player1 INNER JOIN players p2 ON p2.id = matches.id_player2 WHERE matches.id_championship = ?");
        requestStatement.setInt(1, id);
        ResultSet requestResult = requestStatement.executeQuery();
        while (requestResult.next()) {
            String pseudo1 = requestResult.getString("player1");
            String pseudo2 = requestResult.getString("player2");
            matchList.add(new MatchModel(0, pseudo1, pseudo2));
        }
    }

}
