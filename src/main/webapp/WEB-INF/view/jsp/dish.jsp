<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<input type="button" onclick="location.href='/dishes';" value="< Back"/>
<p/>
<img width="300" height="300" src="<c:url value="${picturePath}"/>"/>
<p/>
<table class="table">

    <tr>
        <th>Name</th>
        <td>${dish.name}</td>
    </tr>
    <tr>
        <th>Category</th>
        <td>${dish.dishCategory}</td>
    </tr>
    <tr>
        <th>Price</th>
        <td>${dish.price}</td>
    </tr>
    <tr>
        <th>Weight</th>
        <td>${dish.weight}</td>
    </tr>

</table>
<table>
    <tr>
        <th>Список ингредиентов :</th>
    </tr>
    <c:forEach items="${ingredientsToDish}" var="ingredient">
        <tr>
            <td>- ${ingredient.name}</td>
        </tr>
    </c:forEach>
</table>
<c:set var="dishID" value="${dish.ID}"/>
<p/>
<input type="button" onclick="location.href='/update_dishId=${dishID}';" value="Update info"/>
<p/>
<input type="button" onclick="location.href='/delete_dishId=${dishID}';" value="Delete the dish"/>

</body>
</html>
