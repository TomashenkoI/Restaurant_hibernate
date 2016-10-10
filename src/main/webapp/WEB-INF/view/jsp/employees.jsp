<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:if test="${not empty employees}">

        <table style="align-items: center;">

            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Position</th>
                <th>Salary</th>
            </tr>

            <c:forEach items="${employees}" var="employee">
                <tr bgcolor="#ECECEC" onMouseOver="this.style.backgroundColor='white';" onMouseOut="this.style.backgroundColor='#ECECEC'" onclick="location.href='/employee/employeeId=${employee.ID}'">
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.position}</td>
                    <td>${employee.salary}</td>
                </tr>
            </c:forEach>

        </table>

    </c:if>
    <c:if test="${empty employees}">
        There is no employees with name like '${employeeName}'
    </c:if>

    <p/>
    <input type="button" onclick="location.href='/newEmployee';" value="Add new employee"/>
    <p/>
    <form action="/findEmployeeByName">
        <input type="text" value="find by name" name="employeeName">
        <button>Search</button>
    </form>

</body>
</html>
