<%@ page import="ru.appline.logic.Model"%>
<%--
  Created by IntelliJ IDEA.
  User: angel
  Date: 19.10.2020
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
  <h1>Домашняя страница по работе с пользователями</h1>
  Введите ID пользователя, для вывода всего списка введите - 0.
  <br>
  Доступно:
  <%
  Model model = Model.getINSTANCE();
  out.print(model.getFromList().size());
  %>
  <form method="get" action="list">
    <label>ID:
      <input type="text" name="id">
    </label>
    <button type="submit">Поиск</button>
  </form>
  <br>
  <a href="addUser.html">Создать нового пользователя</a>
  </body>
</html>
