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

@WebServlet(name = "AddPlayerServlet", urlPatterns = "/addplayer")
public class AddPlayerServlet extends HttpServlet {

    List<String> playerSelected = new ArrayList<>();
    private List<String> playerNames = new ArrayList<>();

    public static void sqlRequestPlayer(List<String> playerNames, List<String> playerSelected, HttpServletRequest request) {
        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT pseudo FROM players");
            ResultSet result = preparedStatement.executeQuery();

            playerNames.clear();
            while (result.next()) {
                if (!playerSelected.contains(result.getString("pseudo"))) {
                    playerNames.add(result.getString("pseudo"));
                }
            }

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
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
