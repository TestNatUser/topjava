<%--
  Created by IntelliJ IDEA.
  User: nfurmanova
  Date: 2/7/2022
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="ru.javawebinar.topjava.web.MealServlet" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
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
            <td><a href="?action=edit&Id=<c:out value="${meal.getId()}" />"><i class="fa fa-edit" style="font-size:24px">Update</i> </a></td>
            <td><a href="?action=delete&Id=<c:out value="${meal.getId()}"/>"><i class="fa fa-remove" style="font-size:24px">Delete</i> </a></td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<p><a href="?action=create">Add Meal</a></p>
<form method="POST" action='?action=create' name="addMeal">
    Description : <input
        type="text" name="description"
        value="<c:out value="${meal.getDescription()}" />" /> <br />
    Calories : <input
        type="text" name="calories"
        value="<c:out value="${meal.getCalories()}" />" /> <br />
    Date : <input
        type="date" name="dateTime"
        value="<fmt:formatDate pattern="dd.MM.yyyy HH:mm:ss" value="${meal.getDateTime()}" />" /> <br />

    <button type="submit" style="size:24px" value="Submit" style="vertical-align: middle">Save</button>
</form>
</body>
</html>
