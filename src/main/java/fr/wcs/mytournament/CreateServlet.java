package fr.wcs.mytournament;


import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CreateServlet", urlPatterns = "/create")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            boolean isConnected = (boolean) request.getSession().getAttribute("isConnected");


            if (isConnected) {
                this.getServletContext().getRequestDispatcher("/create_tournament.jsp").forward(request, response);
            } else {
                response.sendRedirect("/");
            }


    }
}
