<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

    <div class="wrap">
        <c:if test="${not empty dishes}">

            <table style="align-items: center;">

                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Weight</th>
                </tr>

                <c:forEach items="${dishes}" var="dish">
                    <tr style="cursor: pointer" bgcolor="#ECECEC" onMouseOver="this.style.backgroundColor='white';" onMouseOut="this.style.backgroundColor='#ECECEC'" onclick="location.href='/dishes/dishId=${dish.ID}'">
                        <td>${dish.name}</td>
                        <td>${dish.dishCategory}</td>
                        <td>${dish.price}</td>
                        <td>${dish.weight}</td>
                    </tr>
                </c:forEach>

            </table>

        </c:if>
        <c:if test="${empty dishes}">
            There is no dishes with name like '${dishesName}'
        </c:if>

        <p/>
        <input type="button" onclick="location.href='/newDish';" value="Add new dish"/>
        <p/>
        <form action="/findDishesByName">
            <input type="text" value="find by name" name="dishName">
            <button>Search</button>
        </form>
    </div>

</body>
</html>
