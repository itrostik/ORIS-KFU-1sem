package ru.kpfu.itis.sergeev;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/")
public class LoginServlet extends HttpServlet {
    public static final String LOGIN = "rostik";
    public static final String PASSWORD = "52";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login.equals(LOGIN) && password.equals(PASSWORD)) {
            resp.sendRedirect("/task3_war_exploded/getWeather");
        } else {
            resp.sendRedirect("/task3_war_exploded/error");
        }
    }
}
