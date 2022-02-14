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

<form method="POST" action='?action=edit' name="addMeal">
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
