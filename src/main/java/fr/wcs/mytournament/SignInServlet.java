package fr.wcs.mytournament;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "SignInServlet", urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("password_confirm");
        if (password.equals(passwordConfirm)) {
            if (password.length() < 6) {
                request.setAttribute("shortError", "Le mot de passe est trop court.");
                this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
            } else {
                try {
                    signIn(pseudo, email, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Cookie pseudoCookie = new Cookie("pseudoCookie", pseudo);
                Cookie onlineCookie = new Cookie("onlineCookie", String.valueOf(true));
                cookieConfig(pseudoCookie, response);
                cookieConfig(onlineCookie, response);
                request.getSession().setAttribute("online", true);
                request.getSession().setAttribute("pseudo", pseudo);
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            request.setAttribute("noMatchError", "Mots de passes différents.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/signin.jsp").forward(request,response);

    }

    private void cookieConfig(Cookie cookie, HttpServletResponse response) {
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 14);
        response.addCookie(cookie);
    }

    private void signIn(String pseudo, String email, String password) throws SQLException {
        PreparedStatement preparedStatement = TournamentServlet.instantiateSQL()
                .prepareStatement("INSERT INTO players VALUES(null, ?, ?, ?);");
        preparedStatement.setString(1, pseudo);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
    }
}
