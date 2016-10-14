<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:if test="${doesItAlreadyExist == false}">
        <form action="/dishes" method="post">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="dishName"></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="dishCategory">
                            <option>Main course</option>
                            <option>Garnish</option>
                            <option>Soup</option>
                            <option>Salad</option>
                            <option>Desert</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="dishPrice"></td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td><input type="text" name="dishWeight"></td>
                </tr>
            </table>

            <p/>
                <select multiple size="8" name="selectedIngredients">
                    <c:forEach items="${ingredients}" var="ingredientName">
                        <option>${ingredientName.name}</option>
                    </c:forEach>
                </select>
            <p/>
            <input style="margin-left: 25px" type="submit" value="Submit" name="submit">
        </form>
    </c:if>

    <c:if test="${doesItAlreadyExist == true}">
        <form action="/updatedEmployeeId=${dish.ID}" method="post">
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="dishName" value="${dish.name}"></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="dishCategory">
                            <option>Main course</option>
                            <option>Garnish</option>
                            <option>Soup</option>
                            <option>Salad</option>
                            <option>Desert</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="dishPrice" value="${dish.price}"></td>
                </tr>
                <tr>
                    <td>Weight</td>
                    <td><input type="text" name="dishWeight" value="${dish.weight}"></td>
                </tr>
            </table>

            <p/>
            <select multiple size="8" name="selectedIngredients">
                <c:forEach items="${ingredients}" var="ingredientName">
                    <option>${ingredientName.name}</option>
                </c:forEach>
            </select>
            <p/>
            <input style="margin-left: 25px" type="submit" value="Submit" name="submit">
        </form>
    </c:if>

</body>
</html>
