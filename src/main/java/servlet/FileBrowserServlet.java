package servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/file-browser"})
public class FileBrowserServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) == null) {
            String currentURL = request.getRequestURL().toString();
            int lastSlashIndex = currentURL.lastIndexOf("/");
            response.sendRedirect(currentURL.substring(0, lastSlashIndex) + "/login");
            return;
        }

        String login = (String)request.getSession().getAttribute("login");
        String currentDirectory;
        String pathToUserDir = "C:\\Users\\KatyaPark11\\Desktop\\Моё\\" + login;
        String pathFromRequest = request.getParameter("path");
        if (request.getParameter("path") != null) {
            if (!pathFromRequest.startsWith(pathToUserDir)) {
                currentDirectory = pathToUserDir;
            } else {
                currentDirectory = pathFromRequest;
            }
        } else {
            currentDirectory = pathToUserDir;
        }

        request.setAttribute("currentDirectory", currentDirectory);
        File directory = new File(currentDirectory);
        File[] files = directory.listFiles();
        request.setAttribute("files", files);

        String parentDirectory = new File(currentDirectory).getParent();
        if (parentDirectory == null) {
            parentDirectory = currentDirectory;
        }
        request.setAttribute("parentDirectory", parentDirectory);
        request.setAttribute("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        request.getRequestDispatcher("fileBrowser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("login");
        request.getSession().removeAttribute("password");
        String currentURL = request.getRequestURL().toString();
        int lastSlashIndex = currentURL.lastIndexOf("/");
        response.sendRedirect(currentURL.substring(0, lastSlashIndex) + "/login");
    }

    @Override
    public void destroy() {
    }
}
