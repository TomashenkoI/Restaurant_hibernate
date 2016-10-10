<%@ page import="java.io.File" %>
<%@ page import="java.net.URL" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<input type="button" onclick="location.href='/employees';" value="< Back"/>
<p/>
<img width="100" height="100" src="<c:url value="${picturePath}"/>"/>
<p/>
<table class="table">

    <tr>
        <th>First Name</th>
        <td>${employee.firstName}</td>
    </tr>
    <tr>
        <th>Last Name</th>
        <td>${employee.lastName}</td>
    </tr>
    <tr>
        <th>Position</th>
        <td>${employee.position}</td>
    </tr>
    <tr>
        <th>Date of birth</th>
        <td>${employee.dateOfBirth}</td>
    </tr>
    <tr>
        <th>Phone Number</th>
        <td>${employee.phoneNumber}</td>
    </tr>
    <tr>
        <th>Salary</th>
        <td>${employee.salary}</td>
    </tr>

    <c:set var="employeeID" value="${employee.ID}"/>

</table>
<p/>
<input type="button" onclick="location.href='/update_EmployeeId=${employeeID}';" value="Update info"/>
<p/>
<input type="button" value="Delete" onclick="window.location.href='/delete_employeeId=${employeeID}'">
<p/>

<%--<a href="/delete_employeeId=${employeeID}">delete the employee</a>--%>
<%----%>
<%--<form action="/delete_employeeId=${employeeID}" method="get">--%>
    <%--<input type="button" value="Delete employee"/>--%>
<%--</form>--%>

</body>
</html>
