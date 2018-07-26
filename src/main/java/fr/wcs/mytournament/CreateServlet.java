package fr.wcs.mytournament;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "CreateServlet", urlPatterns = "/create")
public class CreateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<Integer> playerId = new ArrayList<>();
        int id = 0;
        String name = request.getParameter("name");
        String sport = request.getParameter("sports");
        String type = request.getParameter("type");
        String mode = request.getParameter("mode");
        List<String> pseudoSelected = (List<String>) request.getSession().getAttribute("playerSelected");

        if (pseudoSelected != null && pseudoSelected.size() > 1) {
            if (name == null || name.isEmpty()) {
                request.setAttribute("error", "Veuillez nommer votre tournoi !");
                this.getServletContext().getRequestDispatcher("/WEB-INF/create_tournament.jsp").forward(request, response);
            } else {
                try {
                    id = writeTournament(name, sport, type, mode, id);
                    for (String player : pseudoSelected) {
                        playerId.add(getPlayerId(player));
                    }
                    for (int currentPlayerId : playerId) {
                        playerToTournament(name, currentPlayerId);
                    }
                    for (int i = 0; i < playerId.size(); i++) {
                        for (int j = 0; j < playerId.size(); j++) {
                            if (i != j) {
                                writeMatches(playerId.get(i), playerId.get(j), id);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                AddPlayerServlet.playerSelected.clear();
                response.sendRedirect("/show");
            }
        } else {
            request.setAttribute("emptyError", "Veuillez choisir au moins deux joueurs");
            this.getServletContext().getRequestDispatcher("/WEB-INF/create_tournament.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> playerNames = new ArrayList<>();
        List<String> playerSelected = new ArrayList<>();
        AddPlayerServlet.playerSelected.clear();
        boolean isConnected = false;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("onlineCookie")) {
                    isConnected = Boolean.parseBoolean(cookie.getValue());
                }
            }
        }
        if (isConnected) {
            AddPlayerServlet.sqlRequestPlayer(playerNames, playerSelected, request);
            this.getServletContext().getRequestDispatcher("/WEB-INF/create_tournament.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    private int getPlayerId(String pseudo) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                    .prepareStatement("SELECT id FROM players WHERE pseudo = ?");
            preparedStatement.setString(1, pseudo);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private void playerToTournament(String championshipName, int playerId) {
        int id = 0;
        try {
            PreparedStatement requestStatement = TournamentServlet.instantiateSQL().prepareStatement("SELECT id FROM championship WHERE name = ?");
            requestStatement.setString(1, championshipName);
            ResultSet result = requestStatement.executeQuery();
            while (result.next()) {
                id = result.getInt("id");
            }
            PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                    .prepareStatement("INSERT INTO players_championship VALUES(?, ?, 0, 0, 0, 0, 0, 0)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, playerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void writeMatches(int player1, int player2, int championshipId) throws SQLException {
        PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                .prepareStatement("INSERT INTO matches VALUES(null, 0, ?, ?,false,0, 0, ?)");
        preparedStatement.setInt(1, player1);
        preparedStatement.setInt(2, player2);
        preparedStatement.setInt(3, championshipId);
        preparedStatement.executeUpdate();
    }

    private int writeTournament(String name, String sport, String type, String mode, int id) throws SQLException {
        PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                .prepareStatement("INSERT INTO championship VALUES(null, ?, ?, ?, ?);");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, sport);
        preparedStatement.setString(3, type);
        preparedStatement.setString(4, mode);
        preparedStatement.executeUpdate();

        PreparedStatement requestStatement = TournamentServlet.instantiateSQL().prepareStatement("SELECT id FROM championship WHERE name = ?");
        requestStatement.setString(1, name);
        ResultSet result = requestStatement.executeQuery();
        while (result.next()) {
            id = result.getInt("id");
        }
        return id;
    }

}
