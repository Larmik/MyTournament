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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShowServlet", urlPatterns = "/show")
public class ShowServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<TournamentModel> tournamentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                    .prepareStatement("SELECT * FROM championship");
            ResultSet result = preparedStatement.executeQuery();
            PreparedStatement playerStatement = TournamentServlet.instantiateSQL().prepareStatement("SELECT id_players FROM players_championship WHERE id = ?");

            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String sport = result.getString("sport");
                String type = result.getString("type");
                String mode = result.getString("mode");
                playerStatement.setInt(1, id);
                ResultSet playerResult = playerStatement.executeQuery();
                int numPlayers = 0;
                while (playerResult.next()) {
                    numPlayers++;
                }
                tournamentList.add(new TournamentModel(id, name, sport, type, mode, numPlayers));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("tournamentList", tournamentList);
        this.getServletContext().getRequestDispatcher("/WEB-INF/show_tournaments.jsp").forward(request, response);
    }
}
