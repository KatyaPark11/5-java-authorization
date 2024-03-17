<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>File Browser</title>
        <link rel="stylesheet" href="css/fileBrowser.css">
    </head>
    <body>
        <form action="file-browser" method="POST">
            <input type="submit" value="Выйти" id="logout">
        </form>
        <p><%= request.getAttribute("timestamp") %></p>
        <h2><%= request.getAttribute("currentDirectory") %></h2>
        <a href="file-browser?path=<%= request.getAttribute("parentDirectory") %>">Вверх</a><br><br>
        <ul>
            <%
                java.io.File[] files = (java.io.File[]) request.getAttribute("files");
                if (files != null) {
                    for (java.io.File file : files) {
            %>
            <li>
                <% if (file.isDirectory()) { %>
                &#128194;
                <% } else { %>
                &#128190;
                <% } %>
                <% if (file.isDirectory()) { %>
                <a href="file-browser?path=<%= file.getPath() %>"><%= file.getName() %></a>
                <% } %>
                <% if (!file.isDirectory()) { %>
                <a href="download?file=<%= file.getPath() %>"><%= file.getName() %></a>
                <% } %>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </body>
</html>