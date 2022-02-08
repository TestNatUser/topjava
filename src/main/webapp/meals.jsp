<%--
  Created by IntelliJ IDEA.
  User: nfurmanova
  Date: 2/7/2022
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table style="margin-top: 10px" align="center" class="table">
    <thead class="thead-dark">
    <tr align="center">
        <th scope="col">Дата/Время</th>
        <th scope="col">Описание</th>
        <th scope="col">Калории</th>
    </tr>
    </thead>
    <tbody id="mainTable">
    <c:forEach var="meal" items="${meals}">

        <tr style="color:${meal.isExcess() ? 'red' : 'green'}" align="center">
            <td>${meal.getDateTime().format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</body>
</html>
