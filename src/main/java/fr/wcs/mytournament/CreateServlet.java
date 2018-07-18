package fr.wcs.mytournament;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "CreateServlet", urlPatterns = "/create")
public class CreateServlet extends HttpServlet {
    private boolean isConnected = false;
    private List<Integer> playerId = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String sport = request.getParameter("sports");
        String type = request.getParameter("type");
        String mode = request.getParameter("mode");
        List<String> playerSelected = (List<String>) request.getSession().getAttribute("playerSelected");
        if (playerSelected != null && playerSelected.size() > 1) {
            if (name == null || name.isEmpty()) {
                request.setAttribute("error", "Veuillez nommer votre tournoi !");
                this.getServletContext().getRequestDispatcher("/create_tournament.jsp").forward(request, response);
            } else {
                try {
                    Class driverClass = Class.forName("com.mysql.jdbc.Driver");

                    Driver driver = (Driver) driverClass.newInstance();
                    DriverManager.registerDriver(driver);
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");
                    PreparedStatement preparedStatement = connection
                            .prepareStatement("INSERT INTO championship VALUES(null, ?, ?, ?, ?);");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, sport);
                    preparedStatement.setString(3, type);
                    preparedStatement.setString(4, mode);
                    preparedStatement.executeUpdate();

                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                    e.printStackTrace();
                }

                for (String player : playerSelected) {
                    playerId.add(getPlayerId(player));
                }
                for (int currentPlayerId : playerId) {
                    playerToTournament(name, currentPlayerId);
                }
                request.setAttribute("success", "Tournoi créé avec succès !");
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("emptyError", "Veuillez choisir au moin deux joueurs");
            this.getServletContext().getRequestDispatcher("/create_tournament.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> playerNames = new ArrayList<>();
        List<String> playerSelected = new ArrayList<>();
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
            this.getServletContext().getRequestDispatcher("/create_tournament.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    public int getPlayerId(String pseudo) {
        int id = 0;
        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT id FROM players WHERE pseudo = ?");
            preparedStatement.setString(1, pseudo);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                id = result.getInt("id");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void playerToTournament(String championshipName, int playerId) {
        int id = 0;
        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement requestStatement = connection.prepareStatement("SELECT id FROM championship WHERE name = ?");
            requestStatement.setString(1, championshipName);
            ResultSet result = requestStatement.executeQuery();
            while (result.next()) {
                id = result.getInt("id");
            }

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO players_championship VALUES(?, ?, 0, 0)");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, playerId);
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }


}
