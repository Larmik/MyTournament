package fr.wcs.mytournament;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/home"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pseudoValue = request.getParameter("pseudo");
        String passwordValue = request.getParameter("password");
        boolean isConnected = false;

        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT pseudo, password FROM players");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                String password = result.getString("password");
                String pseudo = result.getString("pseudo");
                if (pseudoValue.contains(pseudo) && passwordValue.contains(password)) {
                    isConnected = true;
                    break;
                }
            }

            if (isConnected) {
                request.setAttribute("isConnected", true);
                request.setAttribute("pseudo", pseudoValue);
                this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                response.sendRedirect("/index.jsp");
            } else {
                request.setAttribute("error", "Identifiants incorrects !");
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            }


        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
