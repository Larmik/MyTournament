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

@WebServlet(name = "AddPlayerServlet", urlPatterns = "/addplayer")
public class AddPlayerServlet extends HttpServlet {
    static List<String> playerSelected = new ArrayList<>();
    List<String> playerNames = new ArrayList<>();

    public static void sqlRequestPlayer(List<String> playerNames, List<String> playerSelected, HttpServletRequest request) {
        try {
            PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                    .prepareStatement("SELECT pseudo FROM players");
            ResultSet result = preparedStatement.executeQuery();
            playerNames.clear();
            while (result.next()) {
                if (!playerSelected.contains(result.getString("pseudo"))) {
                    playerNames.add(result.getString("pseudo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getSession().setAttribute("playerNames", playerNames);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String player = request.getParameter("players");
        if (!playerSelected.contains(player)) {
            playerSelected.add(player);
        }
        sqlRequestPlayer(playerNames, playerSelected, request);
        request.getSession().setAttribute("playerSelected", playerSelected);
        this.getServletContext().getRequestDispatcher("/create_tournament.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
