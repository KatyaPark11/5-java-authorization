<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <link href="css/registration.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Форма регистрации</h1>
        <form action="registration" method="POST">
            <div class="form-item">
                <label for="email">Email:</label>
                <input type="text" name="email" id="email"/>
            </div>
            <div class="form-item">
                <label for="login">Login:</label>
                <input type="text" name="login" id="login"/>
            </div>
            <div class="form-item">
                <label for="password">Password:</label>
                <input type="password" name="password" id="password"/>
            </div>
            <input type="submit" value="Зарегистрироваться">
        </form>
        <a href="login">Войти в существующий аккаунт</a>
    </body>
</html>