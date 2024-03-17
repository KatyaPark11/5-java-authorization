package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserProfile;
import service.UserService;

import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (email.isEmpty() || login.isEmpty() || password.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Все поля должны быть заполнены!");
            return;
        }

        UserProfile profile = new UserProfile(login, password, email);
        if (UserService.getUserByLogin(login) == null) {
            UserService.addNewUser(profile);

            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);

            File folder = new File("C:\\Users\\KatyaPark11\\Desktop\\Моё\\" + login);
            boolean isCreationSuccess = folder.mkdir();
            if (!isCreationSuccess) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("Возникла ошибка при создании нового пользователя, попробуйте ещё раз");
                return;
            }

            String currentURL = request.getRequestURL().toString();
            int lastSlashIndex = currentURL.lastIndexOf("/");
            response.sendRedirect(currentURL.substring(0, lastSlashIndex) + "/file-browser");
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Пользователь с таким логином уже существует");
        }
    }
}
