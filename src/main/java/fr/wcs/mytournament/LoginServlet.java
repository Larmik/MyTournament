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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private String pseudoValue;
    private boolean isOnline = false;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Class driverClass = Class.forName("com.mysql.jdbc.Driver");
            Driver driver = (Driver) driverClass.newInstance();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myTournament", "root", "jecode4wcs");

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT pseudo, password FROM players");
            ResultSet result = preparedStatement.executeQuery();
            String pseudoValue = request.getParameter("pseudo");
            String passwordValue = request.getParameter("password");
            while (result.next()) {
                String password = result.getString("password");
                String pseudo = result.getString("pseudo");
                if (pseudoValue.contains(pseudo) && passwordValue.contains(password)) {
                    Cookie onlineCookie = new Cookie("onlineCookie", String.valueOf(true));
                    Cookie pseudoCookie = new Cookie("pseudoCookie", pseudoValue);
                    pseudoCookie.setPath("/");
                    pseudoCookie.setMaxAge(60*60*24*14);
                    response.addCookie(pseudoCookie);
                    onlineCookie.setPath("/");
                    onlineCookie.setMaxAge(60*60*24*14);
                    response.addCookie(onlineCookie);
                    response.sendRedirect("/home");
                    break;
                } else {
                    request.setAttribute("error", "Identifiants incorrects !");
                    this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }




        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
