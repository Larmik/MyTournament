package fr.wcs.mytournament;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("onlineCookie")) {
                    boolean isOnline = Boolean.parseBoolean(cookie.getValue());
                    request.getSession().setAttribute("isOnline", isOnline);
                    this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);

                }
            }
        } else {
            response.sendRedirect("/index.jsp");
        }
    }
}
