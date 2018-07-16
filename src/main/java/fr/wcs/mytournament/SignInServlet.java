package fr.wcs.mytournament;

import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        String passwordConfirm = request.getParameter("password_confirm");
        if (password.equals(passwordConfirm)) {
            if (password.length() < 6) {
                request.setAttribute("shortError", "Le mot de passe est trop court.");
                this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
            } else {
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
                    Cookie pseudoCookie = new Cookie("pseudoCookie", pseudo);
                    Cookie onlineCookie = new Cookie("onlineCookie", String.valueOf(true));
                    pseudoCookie.setPath("/");
                    pseudoCookie.setMaxAge(60*60*24*14);
                    onlineCookie.setPath("/");
                    onlineCookie.setMaxAge(60*60*24*14);
                    response.addCookie(pseudoCookie);
                    response.addCookie(onlineCookie);
                    request.getSession().setAttribute("online", true);
                    request.getSession().setAttribute("pseudo", pseudo);
                    response.sendRedirect("/home");

                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            request.setAttribute("noMatchError", "Mots de passes différents.");
            this.getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
