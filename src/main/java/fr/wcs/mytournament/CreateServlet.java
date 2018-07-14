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


@WebServlet(name = "CreateServlet", urlPatterns = "/create")
public class CreateServlet extends HttpServlet {

    private List<String> playerNames = new ArrayList<>();
    private List<String> playerSelected = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String sport = request.getParameter("sports");
        String type = request.getParameter("type");
        String mode = request.getParameter("mode");
        if (name == null || name.isEmpty()) {
            request.setAttribute("error", "Veuillez nommer votre tournoi !");
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
            request.setAttribute("success", "Tournoi créé avec succès !");
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect("/");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isConnected = (boolean) request.getSession().getAttribute("isConnected");
        if (isConnected) {
            AddPlayerServlet.sqlRequestPlayer(playerNames, playerSelected, request);
            this.getServletContext().getRequestDispatcher("/create_tournament.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }


    }



}
