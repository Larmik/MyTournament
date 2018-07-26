package fr.wcs.mytournament;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MatchServlet", urlPatterns = "/match")
public class MatchServlet extends HttpServlet {

    private int id1;
    private int id2;
    private int matchId;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("winone").contains("Manches") || request.getParameter("winone").contains("Manches")) {
            request.setAttribute("invalidError", "Veuillez entrer un score valide");
            this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
        } else {
            int wins1 = Integer.parseInt(request.getParameter("winone"));
            int wins2 = Integer.parseInt(request.getParameter("wintwo"));
            try {
                PreparedStatement winUpdateStatement = TournamentServlet.instantiateSQL().prepareStatement("UPDATE players_championship SET points = points + 3, wins = wins + 1, winsets = winsets + ?, losesets = losesets + ? WHERE id_players = ? ");
                PreparedStatement loseUpdateStatement = TournamentServlet.instantiateSQL().prepareStatement("UPDATE players_championship SET loses = loses + 1, winsets = winsets + ?, losesets = losesets + ? WHERE id_players = ?");
                PreparedStatement playedStatement = TournamentServlet.instantiateSQL().prepareStatement("UPDATE matches SET hasBeenPlayed = true, score1 = ?, score2 = ? WHERE id = ?");
                playedStatement.setInt(1, wins1);
                playedStatement.setInt(2, wins2);
                playedStatement.setInt(3, matchId);
                if (wins1 == 2 && wins2 == 2) {
                    request.setAttribute("drawError", "Impossible de terminer sur une égalité");
                    this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
                } else  if (wins1 == 2) {
                    winUpdateStatement.setInt(3, id1);
                    winUpdateStatement.setInt(2, wins2);
                    winUpdateStatement.setInt(1, wins1);
                    loseUpdateStatement.setInt(3, id2);
                    loseUpdateStatement.setInt(2, wins1);
                    loseUpdateStatement.setInt(1, wins2);
                } else if (wins2 == 2) {
                    winUpdateStatement.setInt(3, id2);
                    winUpdateStatement.setInt(2, wins1);
                    winUpdateStatement.setInt(1, wins2);
                    loseUpdateStatement.setInt(3, id1);
                    loseUpdateStatement.setInt(2, wins2);
                    loseUpdateStatement.setInt(1, wins1);
                } else {
                    request.setAttribute("incompleteError", "Le match doit se finir par deux manches gagnantes");
                    this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
                }

                winUpdateStatement.executeUpdate();
                loseUpdateStatement.executeUpdate();
                playedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            TournamentModel model = (TournamentModel) request.getSession().getAttribute("tournament");
            response.sendRedirect(request.getContextPath() + "/tournament?id=" + model.getId());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        matchId = Integer.parseInt(request.getParameter("id"));
        try {
            MatchModel matchModel = getMatch(matchId);
            PreparedStatement selectStatement = TournamentServlet.instantiateSQL().prepareStatement("SELECT id_player1, id_player2 FROM matches WHERE id = ?");
            selectStatement.setInt(1,matchId);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                id1 = resultSet.getInt("id_player1");
                id2 = resultSet.getInt("id_player2");
            }
            request.getSession().setAttribute("match", matchModel);
            this.getServletContext().getRequestDispatcher("/WEB-INF/match.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MatchModel getMatch(int id) throws SQLException {
        MatchModel matchModel = null;
        PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                .prepareStatement("SELECT p1.pseudo as player1, p2.pseudo as player2, hasBeenPlayed, score1, score2 FROM matches INNER JOIN players p1 ON p1.id = matches.id_player1 INNER JOIN players p2 ON p2.id = matches.id_player2 WHERE matches.id = ?");
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String player1 = result.getString("player1");
            String player2 = result.getString("player2");
            boolean hasBeenPlayed = result.getBoolean("hasBeenPlayed");
            int score1 = result.getInt("score1");
            int score2 = result.getInt("score2");
            matchModel = new MatchModel(id, player1, player2, hasBeenPlayed, score1, score2);
        }
        return matchModel;
    }


}
