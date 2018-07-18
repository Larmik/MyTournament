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

@WebServlet(name = "MatchServlet", urlPatterns = "/match")
public class MatchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            MatchModel matchModel = getMatch(id);
            request.getSession().setAttribute("match", matchModel);
            this.getServletContext().getRequestDispatcher("/match.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MatchModel getMatch(int id) throws SQLException {
        MatchModel matchModel = null;
        PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                .prepareStatement("SELECT p1.pseudo as player1, p2.pseudo as player2 FROM matches INNER JOIN players p1 ON p1.id = matches.id_player1 INNER JOIN players p2 ON p2.id = matches.id_player2 WHERE matches.id = ?");
        preparedStatement.setInt(1, id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()) {
            String player1 = result.getString("player1");
            String player2 = result.getString("player2");
            matchModel = new MatchModel(id, player1, player2);
        }
        return matchModel;
    }
}
