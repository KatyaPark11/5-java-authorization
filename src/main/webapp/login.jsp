<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
    </head>
    <body>
        <h1>Войти в существующий аккаунт</h1>
        <form action="login" method="POST">
            <div class="form-item">
                <label for="login">Login:</label>
                <input type="text" name="login" id="login"/>
            </div>
            <div class="form-item">
                <label for="password">Password:</label>
                <input type="password" name="password" id="password"/>
            </div>
            <input type="submit" value="Войти">
        </form>
        <a href="registration">Зарегистрироваться</a>
    </body>
</html>