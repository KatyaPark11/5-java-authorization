package servlet;

import model.UserProfile;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class SessionServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Отсутствует логин или пароль");
            return;
        }

        UserProfile profile = UserService.getUserByLogin(login);
        if (profile == null || !profile.getPass().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Неверный логин или пароль");
            return;
        }

        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("password", password);

        String currentURL = request.getRequestURL().toString();
        int lastSlashIndex = currentURL.lastIndexOf("/");
        response.sendRedirect(currentURL.substring(0, lastSlashIndex) + "/file-browser");
    }
}
