package fr.wcs.mytournament;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TournamentServlet", urlPatterns = "/tournament")
public class TournamentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TournamentModel tournamentModel = null;
        List<PlayerModel> playerList = new ArrayList<>();
        List<MatchModel> matchList = new ArrayList<>();
        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM championship WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String name = result.getString("name");
                String sport = result.getString("sport");
                String type = result.getString("type");
                String mode = result.getString("mode");
                tournamentModel = new TournamentModel(id, name, sport, type, mode, 0);
            }

            PreparedStatement playerStatement = connection.prepareStatement("SELECT players.pseudo, players.id FROM players JOIN players_championship ON players.id=players_championship.id_players WHERE players_championship.id = ?");
            playerStatement.setInt(1, id);
            ResultSet playerResult = playerStatement.executeQuery();
            while (playerResult.next()) {
                String pseudo = playerResult.getString("pseudo");
                int playerId = playerResult.getInt("id");
                playerList.add(new PlayerModel(1,playerId, pseudo, 0, 0, 0));
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < playerList.size(); i++) {
            for (int j = 0; j < playerList.size(); j++) {
                if (i != j) {
                    matchToTournament(playerList.get(i).getId(), playerList.get(j).getId(), id);
                }
            }
        }

        if (tournamentModel != null) {
            request.getSession().setAttribute("tournament", tournamentModel);
            request.getSession().setAttribute("players", playerList);
            request.getSession().setAttribute("matches", matchList);
            this.getServletContext().getRequestDispatcher("/consult_tournament.jsp").forward(request, response);
        }
    }

    public void matchToTournament(int player1, int player2, int championshipId) {

        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");


            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO matches VALUES(null, 0, ?, ?,null, ?)");
            preparedStatement.setInt(1, player1);
            preparedStatement.setInt(2, player2);
            preparedStatement.setInt(3, championshipId);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

}
