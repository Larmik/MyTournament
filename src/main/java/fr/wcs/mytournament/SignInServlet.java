package fr.wcs.mytournament;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "SignInServlet", urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO players VALUES(null, ?, ?, ?);");
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            request.getSession().setAttribute("isConnected", true);
            request.getSession().setAttribute("pseudo", pseudo);
            response.sendRedirect("/index.jsp");

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
